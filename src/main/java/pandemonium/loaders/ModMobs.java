package pandemonium.loaders;

import necesse.engine.registries.MobRegistry;
import pandemonium.Mobs.FeralWolfMob;

public class ModMobs {

    public static void load() {

        MobRegistry.registerMob("feralwolf", FeralWolfMob.class, true);
    }

}
