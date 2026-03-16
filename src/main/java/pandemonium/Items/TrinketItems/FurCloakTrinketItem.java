package pandemonium.Items.TrinketItems;

import necesse.engine.localization.Localization;
import necesse.engine.registries.BuffRegistry;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.TrinketBuff;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.Item;
import necesse.inventory.item.trinketItem.TrinketItem;
import pandemonium.loaders.ModBuffs;

public class FurCloakTrinketItem extends TrinketItem {
    public FurCloakTrinketItem() {
        super(Item.Rarity.EPIC, 500, null);
    }

    @Override
    public TrinketBuff[] getBuffs(InventoryItem item) {
        return new TrinketBuff[] {
                (TrinketBuff) BuffRegistry.getBuff(ModBuffs.FUR_CLOAK_TRINKET_BUFF)
        };
    }

    @Override
    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
        tooltips.add(Localization.translate("itemtooltip", "furcloaktip"));
        return tooltips;
    }
}
