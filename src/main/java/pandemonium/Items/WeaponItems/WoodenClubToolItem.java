package pandemonium.Items.WeaponItems;

import necesse.engine.localization.Localization;
import necesse.engine.sound.SoundSettings;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.GameResources;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.swordToolItem.SwordToolItem;
import necesse.inventory.lootTable.presets.CloseRangeWeaponsLootTable;

public class WoodenClubToolItem extends SwordToolItem {
    public WoodenClubToolItem() {
        super(100, CloseRangeWeaponsLootTable.closeRangeWeapons);
        this.rarity = Rarity.NORMAL;
        this.attackAnimTime.setBaseValue(550);
        this.attackDamage.setBaseValue(24.0F).setUpgradedValue(1.0F, 140.83337F);
        this.attackRange.setBaseValue(95);
        this.knockback.setBaseValue(260);
        this.canBeUsedForRaids = true;
    }

    @Override
    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
        tooltips.add(Localization.translate("itemtooltip", "woodenclubtip"));
        return tooltips;
    }

    @Override
    protected SoundSettings getAttackSound() {
        return (new SoundSettings(GameResources.woodSword)).volume(0.8F);
    }
}
