package pandemonium;

import necesse.engine.modLoader.annotations.ModEntry;

import necesse.level.maps.biomes.Biome;
import pandemonium.loaders.*;


@ModEntry
public class Main {

    public void init() {
        ModItems.load();
        ModProjectiles.load();
        ModBuffs.load();
        ModMobs.load();
        ModEvents.load();
    }


    public void initResources() {
        ModResources.load();
    }

    public void postInit() {
        ModRecipes.registerRecipes();
        ModLootTables.registerLoot();
        Biome.defaultSurfaceMobs.add(60, "feralwolf");
    }
}
