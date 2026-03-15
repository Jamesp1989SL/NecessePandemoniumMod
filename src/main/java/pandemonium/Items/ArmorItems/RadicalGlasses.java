package pandemonium.Items.ArmorItems;

import necesse.entity.mobs.gameDamageType.DamageType;
import necesse.inventory.item.armorItem.SetHelmetArmorItem;
import necesse.inventory.lootTable.presets.CosmeticArmorLootTable;
import necesse.inventory.lootTable.presets.CosmeticSetArmorLootTable;

public class RadicalGlasses extends SetHelmetArmorItem {
    public RadicalGlasses() {
        super(0, (DamageType)null, 0, CosmeticArmorLootTable.cosmeticArmor, CosmeticSetArmorLootTable.cosmeticSetArmor, Rarity.UNCOMMON, "radicalglasses", "labapron", "labboots", (String)null);
        this.hairDrawOptions = HairDrawMode.OVER_HAIR;
        this.facialFeatureDrawOptions = FacialFeatureDrawMode.OVER_FACIAL_FEATURE;
    }
}
