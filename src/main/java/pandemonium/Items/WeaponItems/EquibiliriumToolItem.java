package pandemonium.Items.WeaponItems;

import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.util.GameRandom;
import necesse.entity.levelEvent.GlaiveShowAttackEvent;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.entity.particle.Particle;
import necesse.gfx.GameResources;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.glaiveToolItem.GlaiveToolItem;
import necesse.inventory.lootTable.presets.GlaiveWeaponsLootTable;
import necesse.level.maps.Level;

import java.awt.*;
import java.awt.geom.Point2D;

public class EquibiliriumToolItem extends GlaiveToolItem {
    public EquibiliriumToolItem() {
        super(1500, GlaiveWeaponsLootTable.glaiveWeapons);
        this.rarity = Rarity.RARE;
        this.attackAnimTime.setBaseValue(500);
        this.attackDamage.setBaseValue(40.0F).setUpgradedValue(1.0F, 75.83335F);
        this.attackRange.setBaseValue(160);
        this.knockback.setBaseValue(100);
        this.width = 20.0F;
        this.attackXOffset = 64;
        this.attackYOffset = 64;
        this.canBeUsedForRaids = true;
        this.raidTicketsModifier = 0.5F;
        this.useForRaidsOnlyIfObtained = true;
    }

    @Override
    public void showAttack(Level level, int x, int y, ItemAttackerMob attackerMob, int attackHeight, InventoryItem item, int animAttack, int seed, GNDItemMap mapContent) {
        super.showAttack(level, x, y, attackerMob, attackHeight, item, animAttack, seed, mapContent);
        if (level.isClient()) {
            if (attackerMob.getDir() == 1) {
                level.entityManager.events.addHidden(new GlaiveShowAttackEvent(attackerMob, x, y, seed, 10.0F) {
                    public void tick(float angle) {
                        Point2D.Float angleDir = this.getAngleDir(angle);
                        this.level.entityManager.addParticle(this.attackMob.x + angleDir.x * 75.0F + (float) this.attackMob.getCurrentAttackDrawXOffset(), this.attackMob.y + angleDir.y * 75.0F + (float) this.attackMob.getCurrentAttackDrawYOffset(), Particle.GType.COSMETIC).color(new Color(8, 8, 8)).minDrawLight(150).givesLight(240.0F, 14.0F).sprite(GameResources.magicSparkParticles.sprite(GameRandom.globalRandom.nextInt(4), 0, 22)).lifeTime(400);
                        this.level.entityManager.addParticle(this.attackMob.x - angleDir.x * 75.0F + (float) this.attackMob.getCurrentAttackDrawXOffset(), this.attackMob.y - angleDir.y * 75.0F + (float) this.attackMob.getCurrentAttackDrawYOffset(), Particle.GType.COSMETIC).color(new Color(250, 255, 255)).minDrawLight(150).givesLight(240.0F, 0.0F).sprite(GameResources.magicSparkParticles.sprite(GameRandom.globalRandom.nextInt(4), 0, 22)).lifeTime(400);
                    }
                });
            }
            else
                level.entityManager.events.addHidden(new GlaiveShowAttackEvent(attackerMob, x, y, seed, 10.0F) {
                    public void tick(float angle) {
                        Point2D.Float angleDir = this.getAngleDir(angle);
                        this.level.entityManager.addParticle(this.attackMob.x + angleDir.x * 75.0F + (float) this.attackMob.getCurrentAttackDrawXOffset(), this.attackMob.y + angleDir.y * 75.0F + (float) this.attackMob.getCurrentAttackDrawYOffset(), Particle.GType.COSMETIC).color(new Color(250, 255, 255)).minDrawLight(150).givesLight(240.0F, 0.0F).sprite(GameResources.magicSparkParticles.sprite(GameRandom.globalRandom.nextInt(4), 0, 22)).lifeTime(400);
                        this.level.entityManager.addParticle(this.attackMob.x - angleDir.x * 75.0F + (float) this.attackMob.getCurrentAttackDrawXOffset(), this.attackMob.y - angleDir.y * 75.0F + (float) this.attackMob.getCurrentAttackDrawYOffset(), Particle.GType.COSMETIC).color(new Color(8, 8, 8)).minDrawLight(150).givesLight(240.0F, 14.0F).sprite(GameResources.magicSparkParticles.sprite(GameRandom.globalRandom.nextInt(4), 0, 22)).lifeTime(400);
                    }
                });
        }

    }
}
