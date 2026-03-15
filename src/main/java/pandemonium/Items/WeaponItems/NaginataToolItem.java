package pandemonium.Items.WeaponItems;

import necesse.inventory.item.toolItem.spearToolItem.SpearToolItem;
import necesse.inventory.lootTable.presets.SpearWeaponsLootTable;

public class NaginataToolItem extends SpearToolItem {
    public NaginataToolItem() {
        super(200, SpearWeaponsLootTable.spearWeapons);
        this.rarity = Rarity.UNCOMMON;
        this.attackAnimTime.setBaseValue(450);
        this.attackDamage.setBaseValue(28.0F).setUpgradedValue(1.0F, 77.00002F);
        this.attackRange.setBaseValue(135);
        this.knockback.setBaseValue(25);
        this.canBeUsedForRaids = true;
    }
}
