package pandemonium.loaders;

import necesse.engine.registries.ItemRegistry;
import necesse.inventory.item.Item;
import necesse.inventory.item.trinketItem.SimpleTrinketItem;
import necesse.inventory.lootTable.presets.TrinketsLootTable;
import pandemonium.Items.ArmorItems.RadicalGlasses;
import pandemonium.Items.TrinketItems.FurCloakTrinketItem;
import pandemonium.Items.WeaponItems.*;

public class ModItems {

    public static void load() {

        // Weapons
        ItemRegistry.registerItem("gravebuster", new GraveBusterToolItem(), 115, true);
        ItemRegistry.registerItem("alicegrimoire", new AliceGrimoire(), 100, true);
        ItemRegistry.registerItem("corrosiveflask", new CorrosiveFlaskToolItem(), 100, true);
        ItemRegistry.registerItem("engineerswrench", new EngineersWrenchToolItem(), 55, true);
        ItemRegistry.registerItem("woodenclub", new WoodenClubToolItem(), 16, true);
        ItemRegistry.registerItem("anubisblade", new AnubisBladeToolItem(), 400, true);
        ItemRegistry.registerItem("blunderbuss", new BlunderbussToolItem(), 500, true);
        ItemRegistry.registerItem("equibilirium", new EquibiliriumToolItem(), 500, true);
        ItemRegistry.registerItem("naginata", new NaginataToolItem(), 500, true);

        // Fishing Rod
        ItemRegistry.registerItem("crystalcatcher", new CrystalCatcherRodItem(), 375, true);

        // Trinkets
        ItemRegistry.registerItem("athame", new SimpleTrinketItem(Item.Rarity.UNCOMMON, "athametrinketbuff", 600, TrinketsLootTable.trinkets), 200.0F, true);
        ItemRegistry.registerItem("baitearring", new SimpleTrinketItem(Item.Rarity.UNCOMMON, "baitearringtrinketbuff", 500, TrinketsLootTable.trinkets), 400.0F, true);
        ItemRegistry.registerItem("flametalisman", new SimpleTrinketItem(Item.Rarity.UNCOMMON, "flametalismantrinketbuff", 500, TrinketsLootTable.trinkets), 400.0F, true);
        ItemRegistry.registerItem("furcloaktrinket", new FurCloakTrinketItem(), 800.0F, true);

        // Materials
        ItemRegistry.registerItem("feralwolffur", new FeralWolfFurMaterialItem(), 8, true);

        // Cosmetics
        ItemRegistry.registerItem("radicalglasses", new RadicalGlasses(), 50, true);
    }

}
