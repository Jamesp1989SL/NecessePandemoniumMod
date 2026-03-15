package pandemonium.Items.WeaponItems;

import necesse.engine.util.GameRandom;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.projectile.Projectile;
import necesse.inventory.item.Item;
import necesse.inventory.item.toolItem.projectileToolItem.magicProjectileToolItem.FlaskProjectileToolItem;
import necesse.inventory.lootTable.presets.MagicWeaponsLootTable;
import necesse.level.maps.Level;
import pandemonium.Projectiles.CorrosiveFlaskProjectile;

public class CorrosiveFlaskToolItem extends FlaskProjectileToolItem {
    public CorrosiveFlaskToolItem() {
        super(850, MagicWeaponsLootTable.magicWeapons);
        this.rarity = Item.Rarity.RARE;
        this.attackAnimTime.setBaseValue(500);
        this.attackDamage.setBaseValue(45.0F).setUpgradedValue(1.0F, 98.00003F);
        this.velocity.setBaseValue(800);
        this.attackXOffset = 8;
        this.attackYOffset = 10;
        this.attackCooldownTime.setBaseValue(500);
        this.attackRange.setBaseValue(300);
        this.manaCost.setBaseValue(1.25F).setUpgradedValue(1.0F, 2.0F);
        this.resilienceGain.setBaseValue(0.0F);
        this.itemAttackerProjectileCanHitWidth = 5.0F;
        this.canBeUsedForRaids = false;
    }

    protected Projectile getProjectile(Level level, Mob owner, float x, float y, float targetX, float targetY, float speed, int distance, GameDamage damage, int knockback, GameRandom random) {
        return new CorrosiveFlaskProjectile(level, owner, x, y, targetX, targetY, speed, distance, damage, knockback);
    }
}
