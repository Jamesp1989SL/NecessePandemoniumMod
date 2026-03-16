package pandemonium.loaders;

import necesse.engine.registries.LevelEventRegistry;
import pandemonium.Events.BloodSplashEvent;
import pandemonium.Events.CorrosiveGasCloudEvent;
import pandemonium.Events.FlameTalismanExplosionEvent;

public class ModEvents {

    public static void load() {

        LevelEventRegistry.registerEvent("flametalismanexplosion", FlameTalismanExplosionEvent.class);
        LevelEventRegistry.registerEvent("bloodsplash", BloodSplashEvent.class);
        LevelEventRegistry.registerEvent("corrosivegascloud", CorrosiveGasCloudEvent.class);
    }
}



