package pandemonium.loaders;

import necesse.engine.registries.ProjectileRegistry;
import pandemonium.Projectiles.AliceGrimoireProjectile;
import pandemonium.Projectiles.CorrosiveFlaskProjectile;
import pandemonium.Projectiles.EngineersWrenchProjectile;
import pandemonium.Projectiles.SunWaveProjectile;

public class ModProjectiles {

    public static void load() {

        ProjectileRegistry.registerProjectile("alicegrimoireprojectile", AliceGrimoireProjectile.class, "", "");
        ProjectileRegistry.registerProjectile("engineerswrench", EngineersWrenchProjectile.class, "engineerswrench", "engineerswrench_shadow");
        ProjectileRegistry.registerProjectile("sunwaveprojectile", SunWaveProjectile.class, "sunwave", "");
        ProjectileRegistry.registerProjectile("corrosiveflaskprojectile", CorrosiveFlaskProjectile.class, "corrosiveflask", "corrosiveflask_shadow");
    }

}
