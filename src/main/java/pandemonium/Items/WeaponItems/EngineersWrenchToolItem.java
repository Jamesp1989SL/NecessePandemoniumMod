package pandemonium.Items.WeaponItems;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.projectileToolItem.throwToolItem.boomerangToolItem.BoomerangToolItem;
import necesse.inventory.lootTable.presets.ThrowWeaponsLootTable;

public class EngineersWrenchToolItem extends BoomerangToolItem {
    public EngineersWrenchToolItem() {
        super(1300, ThrowWeaponsLootTable.throwWeapons, "engineerswrench");
        this.rarity = Rarity.UNCOMMON;
        this.attackAnimTime.setBaseValue(150);
        this.attackCooldownTime.setBaseValue(250);
        this.attackDamage.setBaseValue(26.0F).setUpgradedValue(1.0F, 98.0F);
        this.attackRange.setBaseValue(360);
        this.velocity.setBaseValue(375);
        this.resilienceGain.setBaseValue(0.75F);
        this.knockback.setBaseValue(50);
        this.itemAttackerProjectileCanHitWidth = 22.0F;
    }

    @Override
    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
        tooltips.add(Localization.translate("itemtooltip", "engineerswrenchtip"));
        return tooltips;
    }
}
