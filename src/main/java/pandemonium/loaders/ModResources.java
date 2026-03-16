package pandemonium.loaders;

import necesse.engine.sound.gameSound.GameSound;
import necesse.gfx.gameTexture.GameTexture;
import pandemonium.Mobs.FeralWolfMob;
import pandemonium.Projectiles.EngineersWrenchProjectile;

import static necesse.gfx.GameResources.particlesTextureGenerator;

public class ModResources {
    public static GameSound WrenchHit;
    public static void load() {

        WrenchHit = GameSound.fromFile("pandemonium/wrenchhit");
        FeralWolfMob.texture = GameTexture.fromFile("mobs/feralwolf");
        EngineersWrenchProjectile.engineerswrenchparticletexture = particlesTextureGenerator.addTexture(GameTexture.fromFile("projectiles/engineerswrench"));
    }

}

