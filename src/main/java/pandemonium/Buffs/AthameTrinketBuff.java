package pandemonium.Buffs;

import necesse.engine.localization.Localization;
import necesse.entity.mobs.MobWasKilledEvent;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.TrinketBuff;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.trinketItem.TrinketItem;

public class AthameTrinketBuff extends TrinketBuff {

    @Override
    public ListGameTooltips getTrinketTooltip(TrinketItem trinketItem, InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTrinketTooltip(trinketItem, item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "athametip"));
        return tooltips;
    }

    public void init(ActiveBuff buff, BuffEventSubscriber eventSubscriber) {
    }

    @Override
    public void onHasKilledTarget(ActiveBuff buff, MobWasKilledEvent event) {
        super.onHasKilledTarget(buff, event);
        if (event.target.getMaxHealth() >= 30) {
            event.attacker.getAttackOwner().buffManager.addBuff(new ActiveBuff("athamebuff", event.target, 3.0F, event.attacker), event.target.isServer());
        }
    }
}
