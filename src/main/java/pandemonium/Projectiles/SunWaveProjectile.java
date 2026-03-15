package pandemonium.Projectiles;

import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.particle.ParticleOption;
import necesse.entity.particle.fireworks.FireworksExplosion;
import necesse.entity.particle.fireworks.FireworksPath;
import necesse.entity.projectile.Projectile;
import necesse.entity.trails.Trail;
import necesse.gfx.GameResources;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawOptions.texture.TextureDrawOptions;
import necesse.gfx.drawables.EntityDrawable;
import necesse.gfx.drawables.LevelSortedDrawable;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.level.maps.Level;
import necesse.level.maps.LevelObjectHit;
import necesse.level.maps.light.GameLight;

import java.awt.*;
import java.util.List;

public class SunWaveProjectile extends Projectile {
    public static FireworksExplosion sunwavePopExplosion;

    public SunWaveProjectile() {
    }

    public SunWaveProjectile(Level level, float x, float y, float targetX, float targetY, float speed, int distance, GameDamage damage, Mob owner) {
        this.setLevel(level);
        this.x = x;
        this.y = y;
        this.setTarget(targetX, targetY);
        this.speed = speed;
        this.setDamage(damage);
        this.setOwner(owner);
        this.setDistance(distance);
    }

    public void init() {
        super.init();
        this.height = 16.0F;
        this.setWidth(30.0F, true);
        this.isSolid = true;
        this.givesLight = true;
        this.particleRandomOffset = 14.0F;
    }

    public Color getParticleColor() {
        return new Color(230, 200, 75);
    }

    public Trail getTrail() {
        return null;
    }

    public void addDrawables(List<LevelSortedDrawable> list, OrderableDrawables tileList, OrderableDrawables topList, OrderableDrawables overlayList, Level level, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
        if (!this.removed()) {
            GameLight light = level.getLightLevel(this);
            int drawX = camera.getDrawX(this.x) - this.texture.getWidth() / 2;
            int drawY = camera.getDrawY(this.y - this.getHeight()) - this.texture.getHeight() / 2;
            final TextureDrawOptions options = this.texture.initDraw().light(light.minLevelCopy(Math.min(light.getLevel() + 100.0F, 150.0F))).rotate(this.getAngle() - 135.0F, this.texture.getWidth() / 2, this.texture.getHeight() / 2).pos(drawX, drawY);
            list.add(new EntityDrawable(this) {
                public void draw(TickManager tickManager) {
                    options.draw();
                }
            });
        }
    }

    public void doHitLogic(Mob mob, LevelObjectHit object, float x, float y) {
        super.doHitLogic(mob, object, x, y);
        float targetY;
        float targetX;
        if (mob != null) {
            targetX = mob.x;
            targetY = mob.y;
        } else {
            targetX = x;
            targetY = y;
        }

        int radius = 60;
        if (!this.isServer()) {
            FireworksExplosion explosion = new FireworksExplosion(FireworksPath.sphere((float)radius));
            explosion.colorGetter = (particle, progress, random) -> ParticleOption.randomizeColor(50.0F, 0.80F, 0.90F, 4.0F, 0.0F, 0.0F);
            explosion.trailChance = 0.5F;
            explosion.particles = 40;
            explosion.lifetime = 400;
            explosion.popOptions = sunwavePopExplosion;
            explosion.particleLightHue = 50.0F;
            explosion.explosionSound = (pos, height, random) -> SoundManager.playSound(GameResources.explosionLight, SoundEffect.effect(pos.x, pos.y).pitch((Float)random.getOneOf(new Float[]{0.95F, 1.0F, 1.05F})).volume(0.6F).falloffDistance(1500));
            explosion.spawnExplosion(this.getLevel(), targetX, targetY, this.getHeight(), GameRandom.globalRandom);
        }
        if (!this.isClient()) {
            Rectangle targetBox = new Rectangle((int)targetX - radius, (int)targetY - radius, radius * 2, radius * 2);
            this.streamTargets(this.getOwner(), targetBox).filter((m) -> this.canHit(m) && m.getDistance(targetX, targetY) <= (float)radius).forEach((m) -> m.isServerHit(this.getDamage(), m.x - x, m.y - y, (float)this.knockback, this));
        }
    }
    public void applyDamage(Mob mob, float x, float y) {
    }
    static {
        sunwavePopExplosion = new FireworksExplosion(FireworksExplosion.popPath);
        sunwavePopExplosion.particles = 1;
        sunwavePopExplosion.lifetime = 200;
        sunwavePopExplosion.minSize = 12;
        sunwavePopExplosion.maxSize = 20;
        sunwavePopExplosion.trailChance = 0.5F;
        sunwavePopExplosion.popChance = 0.5F;
        sunwavePopExplosion.colorGetter = (particle, progress, random) -> ParticleOption.randomizeColor(60.0F, 0.50F, 0.90F, 10.0F, 0.05F, 0.20F);
        sunwavePopExplosion.explosionSound = null;
    }
}
