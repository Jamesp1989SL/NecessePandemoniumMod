package pandemonium.Items.WeaponItems;

import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.registries.ProjectileRegistry;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.itemAttacker.ItemAttackSlot;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.entity.projectile.Projectile;
import necesse.entity.projectile.modifiers.ResilienceOnHitProjectileModifier;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.Item;
import necesse.inventory.item.toolItem.projectileToolItem.magicProjectileToolItem.MagicProjectileToolItem;
import necesse.inventory.lootTable.presets.MagicWeaponsLootTable;
import necesse.level.maps.Level;

public class AliceGrimoire extends MagicProjectileToolItem {
    public AliceGrimoire() {
        super(100, MagicWeaponsLootTable.magicWeapons);
        this.rarity = Item.Rarity.RARE;
        this.attackAnimTime.setBaseValue(75);
        this.attackDamage.setBaseValue(38.0F).setUpgradedValue(1.0F, 245.00008F);
        this.velocity.setBaseValue(175);
        this.attackXOffset = 20;
        this.attackYOffset = 20;
        this.attackRange.setBaseValue(700);
        this.knockback.setBaseValue(5);
        this.manaCost.setBaseValue(0.7F).setUpgradedValue(1.0F, 2.0F);
        this.itemAttackerProjectileCanHitWidth = 5.0F;
        this.itemAttackerPredictionDistanceOffset = -20.0F;
        this.canBeUsedForRaids = true;
    }
    public InventoryItem onAttack(Level level, int x, int y, ItemAttackerMob attackerMob, int attackHeight, InventoryItem item, ItemAttackSlot slot, int animAttack, int seed, GNDItemMap mapContent) {
        float randomspread = GameRandom.globalRandom.getFloatBetween(-7.0F, 7.0F);
        Projectile projectile = ProjectileRegistry.getProjectile("alicegrimoireprojectile", level, attackerMob.x, attackerMob.y, (float)x, (float)y, (float)this.getProjectileVelocity(item, attackerMob), this.getAttackRange(item), this.getAttackDamage(item), this.getKnockback(item, attackerMob), attackerMob);
        projectile.setModifier(new ResilienceOnHitProjectileModifier(this.getResilienceGain(item)));
        GameRandom random = new GameRandom((long) seed);
        projectile.resetUniqueID(random);
        attackerMob.addAndSendAttackerProjectile(projectile, 20, randomspread);
        this.consumeMana(attackerMob, item);
        return item;
    }
}
