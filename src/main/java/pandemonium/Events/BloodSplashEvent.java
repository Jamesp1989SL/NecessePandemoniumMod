package pandemonium.Events;

import necesse.engine.util.GameMath;
import necesse.engine.util.GameRandom;
import necesse.entity.levelEvent.explosionEvent.splashEvent.SplashEvent;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.particle.Particle;
import necesse.gfx.GameResources;
import necesse.gfx.ThemeColorRegistry;

import java.awt.*;
import java.awt.geom.Point2D;

public class BloodSplashEvent extends SplashEvent {

    public BloodSplashEvent() {
        this(0.0F, 0.0F, 50, new GameDamage(0.0F), false, 0.0F, (Mob)null);
    }

    public BloodSplashEvent(float x, float y, int range, GameDamage damage, boolean destructive, float toolTier, Mob owner) {
        super(x, y, range, damage, destructive, toolTier, owner);
        this.knockback = 0;
        this.isLiquid = false;
    }

    public void spawnExplosionParticle(float x, float y, float dirX, float dirY, int lifeTime, float range) {
        this.level.entityManager.addParticle(x, y, Particle.GType.CRITICAL).movesConstant(dirX * 0.1F, dirY * 0.1F).sprite(GameResources.liquidBlobParticle.sprite(GameRandom.globalRandom.nextInt(5), 0, 12)).color(this.getInnerSplashColor()).height(10.0F).sizeFades(12, 18).givesLight(250.0F, 0.3F).onProgress(0.1F, (p) -> {
            Point2D.Float norm = GameMath.normalize(dirX, dirY);
            this.level.entityManager.addParticle(p.x + norm.x * 20.0F, p.y + norm.y * 20.0F, Particle.GType.IMPORTANT_COSMETIC).movesConstant(dirX, dirY).sprite(GameResources.liquidBlobParticle.sprite(0, 0, 12)).color(this.getOuterSplashColor()).sizeFades(26, 32).heightMoves(10.0F, 30.0F).lifeTime(lifeTime);
        }).lifeTime((int)((float)lifeTime * 1.4F));

    }

    protected void playExplosionEffects() {
    }

    protected boolean canHitMob(Mob target) {
        return false;
    }

    protected Color getInnerSplashColor() {
        return ThemeColorRegistry.BLOOD.getRandomColor();
    }
}
