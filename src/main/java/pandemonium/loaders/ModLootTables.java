package pandemonium.loaders;

import necesse.entity.mobs.hostile.JackalMob;
import necesse.inventory.lootTable.lootItem.ChanceLootItem;
import necesse.inventory.lootTable.lootItem.LootItem;
import necesse.inventory.lootTable.presets.CaveCryptLootTable;
import necesse.inventory.lootTable.presets.DeepCaveChestLootTable;
import necesse.inventory.lootTable.presets.DungeonChestLootTable;
import necesse.inventory.lootTable.presets.PirateDisplayStandLootTable;

public class ModLootTables {

    public static void registerLoot() {

        CaveCryptLootTable.uniqueItems.items.add (
                new LootItem("gravebuster")
        );
        DeepCaveChestLootTable.basicMainItems.items.add (
                new LootItem("flametalisman")
        );
        DungeonChestLootTable.mainItems.items.add (
                new LootItem("athame")
        );
        PirateDisplayStandLootTable.items.items.add (
                new LootItem("blunderbuss")
        );
        JackalMob.lootTable.items.add (
                new ChanceLootItem(0.01F, "anubisblade")
        );
    }

}
