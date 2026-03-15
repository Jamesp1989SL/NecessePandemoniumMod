package pandemonium.Items.WeaponItems;

import pandemonium.Projectiles.SunWaveProjectile;
import necesse.engine.localization.Localization;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.util.GameBlackboard;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.itemAttacker.ItemAttackSlot;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.entity.projectile.Projectile;
import necesse.entity.projectile.modifiers.ResilienceOnHitProjectileModifier;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.enchants.ToolItemModifiers;
import necesse.inventory.item.toolItem.swordToolItem.SwordToolItem;
import necesse.inventory.item.upgradeUtils.FloatUpgradeValue;
import necesse.inventory.lootTable.presets.CloseRangeWeaponsLootTable;
import necesse.level.maps.Level;

public class AnubisBladeToolItem extends SwordToolItem {
    protected FloatUpgradeValue projectileResilienceGain = new FloatUpgradeValue(0.0F, 0.0F);

    public AnubisBladeToolItem() {
        super(1250, CloseRangeWeaponsLootTable.closeRangeWeapons);
        this.rarity = Rarity.RARE;
        this.attackAnimTime.setBaseValue(500);
        this.attackDamage.setBaseValue(58.0F).setUpgradedValue(1.0F, 101.50003F);
        this.attackRange.setBaseValue(55);
        this.knockback.setBaseValue(75);
        this.resilienceGain.setBaseValue(2.0F).setUpgradedValue(1.0F, 2.0F);
        this.projectileResilienceGain.setBaseValue(1.0F);
        this.canBeUsedForRaids = true;
    }

    @Override
    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
        tooltips.add(Localization.translate("itemtooltip", "anubisbladetip"));
        return tooltips;
    }

    public InventoryItem onAttack(Level level, int x, int y, ItemAttackerMob attackerMob, int attackHeight, InventoryItem item, ItemAttackSlot slot, int animAttack, int seed, GNDItemMap mapContent) {
        item = super.onAttack(level, x, y, attackerMob, attackHeight, item, slot, animAttack, seed, mapContent);
        float rangeMod = 3.5F;
        float velocity = 150.0F;
        float finalVelocity = (float)Math.round((Float)this.getEnchantment(item).applyModifierLimited(ToolItemModifiers.VELOCITY, (Float)ToolItemModifiers.VELOCITY.defaultBuffManagerValue) * velocity * (Float)attackerMob.buffManager.getModifier(BuffModifiers.PROJECTILE_VELOCITY));
        Projectile projectile = new SunWaveProjectile(level, attackerMob.x, attackerMob.y, (float)x, (float)y, finalVelocity, (int)((float)this.getAttackRange(item) * rangeMod), new GameDamage(this.getAttackDamage(item).damage * 0.7F), attackerMob);
        projectile.setModifier(new ResilienceOnHitProjectileModifier(this.getResilienceGain(item, this.projectileResilienceGain)));
        projectile.resetUniqueID(new GameRandom((long)seed));
        attackerMob.addAndSendAttackerProjectile(projectile, 20);
        return item;
    }
}
