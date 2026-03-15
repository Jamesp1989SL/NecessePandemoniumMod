package pandemonium.Projectiles;

import necesse.engine.registries.MobRegistry;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.buffs.NecroticPoisonBuff;
import necesse.entity.particle.FleshParticle;
import necesse.entity.particle.Particle;
import necesse.entity.projectile.FlaskProjectile;
import necesse.level.maps.Level;
import pandemonium.Events.CorrosiveGasCloudEvent;

import java.awt.*;

public class CorrosiveFlaskProjectile extends FlaskProjectile {
    public CorrosiveFlaskProjectile() {
    }

    public CorrosiveFlaskProjectile(Level level, Mob owner, float targetX, float targetY, float speed, int distance, GameDamage damage, int knockback) {
        this(level, owner, owner.x, owner.y, targetX, targetY, speed, distance, damage, knockback);
    }

    public CorrosiveFlaskProjectile(Level level, Mob owner, float x, float y, float targetX, float targetY, float speed, int distance, GameDamage damage, int knockback) {
        super(level, owner, x, y, targetX, targetY, speed, distance, damage, knockback);
    }

    public Color getParticleColor() {
        return NecroticPoisonBuff.getNecroticParticleColor();
    }

    protected void spawnSplashEvent() {
        CorrosiveGasCloudEvent event = new CorrosiveGasCloudEvent(this.getOwner(), (int)x, (int)y, GameRandom.globalRandom, this.getDamage());
        this.getLevel().entityManager.events.add(event);
    }

    protected void spawnDeathParticles() {
        for(int i = 0; i < 4; ++i) {
            this.getLevel().entityManager.addParticle(new FleshParticle(this.getLevel(), MobRegistry.Textures.necroticflaskdebris, i, 0, 32, this.x, this.y, this.height, this.dx * 5.0F, this.dy * 5.0F), Particle.GType.IMPORTANT_COSMETIC);
        }

    }
}
