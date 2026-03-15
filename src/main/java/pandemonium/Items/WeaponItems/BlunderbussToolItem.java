package pandemonium.Items.WeaponItems;

import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.sound.SoundSettings;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.entity.projectile.Projectile;
import necesse.entity.projectile.modifiers.ResilienceOnHitProjectileModifier;
import necesse.gfx.GameResources;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.bulletItem.BulletItem;
import necesse.inventory.item.toolItem.projectileToolItem.gunProjectileToolItem.GunProjectileToolItem;
import necesse.inventory.lootTable.presets.GunWeaponsLootTable;
import necesse.level.maps.Level;

import java.awt.*;

public class BlunderbussToolItem extends GunProjectileToolItem {
    public BlunderbussToolItem() {
        super(NORMAL_AMMO_TYPES, 1150, GunWeaponsLootTable.gunWeapons);
        this.rarity = Rarity.RARE;
        this.attackAnimTime.setBaseValue(900);
        this.attackDamage.setBaseValue(23.0F).setUpgradedValue(1.0F, 44.333344F);
        this.attackXOffset = 31;
        this.attackYOffset = 15;
        this.attackRange.setBaseValue(600);
        this.velocity.setBaseValue(350);
        this.moveDist = 20;
        this.resilienceGain.setBaseValue(0.15F);
        this.addGlobalIngredient(new String[]{"bulletuser"});
        this.canBeUsedForRaids = true;
        this.useForRaidsOnlyIfObtained = true;
    }

    @Override
    public Projectile getProjectile(InventoryItem item, BulletItem bulletItem, float x, float y, float targetX, float targetY, int range, ItemAttackerMob attackerMob) {
        return super.getProjectile(item, bulletItem, x, y + 12.0F, targetX, targetY + 12.0F, range, attackerMob);
    }

    @Override
    protected void fireProjectiles(Level level, int x, int y, ItemAttackerMob attackerMob, InventoryItem item, int seed, BulletItem bullet, boolean dropItem, GNDItemMap mapContent) {
        GameRandom random = new GameRandom((long)seed);
        GameRandom spreadRandom = new GameRandom((long)(seed + 10));
        int range;
        if (this.controlledRange) {
            Point newTarget = this.controlledRangePosition(spreadRandom, attackerMob, x, y, item, this.controlledMinRange, this.controlledInaccuracy);
            x = newTarget.x;
            y = newTarget.y;
            range = (int)attackerMob.getDistance((float)x, (float)y);
        } else {
            range = this.getAttackRange(item);
        }

        for(int i = 0; i <= 5; ++i) {
            Projectile projectile = this.getProjectile(item, bullet, attackerMob.x, attackerMob.y, (float)x, (float)y, range, attackerMob);
            projectile.setModifier(new ResilienceOnHitProjectileModifier(this.getResilienceGain(item)));
            projectile.dropItem = dropItem;
            projectile.getUniqueID(random);
            attackerMob.addAndSendAttackerProjectile(projectile, this.moveDist, (spreadRandom.nextFloat() - 0.5F) * 25.0F);
        }

    }

    @Override
    protected SoundSettings getAttackSound() {
        return new SoundSettings(GameResources.shotgun);
    }
}
