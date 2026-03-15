package pandemonium.Events;

import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.engine.util.GameRandom;
import necesse.entity.ParticleTypeSwitcher;
import necesse.entity.levelEvent.explosionEvent.ExplosionEvent;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.particle.Particle;
import necesse.gfx.GameResources;
import necesse.gfx.ThemeColorRegistry;

public class FlameTalismanExplosionEvent extends ExplosionEvent implements Attacker {
    protected ParticleTypeSwitcher explosionTypeSwitcher;
    public FlameTalismanExplosionEvent() {
        this(0.0F, 0.0F, 40, new GameDamage(400.0F), 0.0F, (Mob)null);
    }
    public FlameTalismanExplosionEvent(float x, float y, int range, GameDamage damage, float toolTier, Mob owner) {
        super(x, y, 80, damage, false, 0.0F, owner);
        this.sendCustomData = false;
        this.sendOwnerData = true;
        this.knockback = 900;
        this.explosionTypeSwitcher = new ParticleTypeSwitcher(new Particle.GType[]{Particle.GType.IMPORTANT_COSMETIC, Particle.GType.COSMETIC, Particle.GType.CRITICAL});
    }

    protected void playExplosionEffects() {
        SoundManager.playSound(GameResources.explosionLight, SoundEffect.effect(this.x, this.y).volume(1.5F).pitch(1.3F));
        SoundManager.playSound(GameResources.campfireSizzle, SoundEffect.effect(this.x, this.y).volume(1.5F).pitch(1.3F));
    }

    protected float getDistanceMod(float targetDistance) {
        return 1.0F;
    }

    public float getParticleCount(float currentRange, float lastRange) {
        return super.getParticleCount(currentRange, lastRange) * 1.5F;
    }

    public void spawnExplosionParticle(float x, float y, float dirX, float dirY, int lifeTime, float range) {
        if (range <= (float)Math.max(this.range - 50, 25) && GameRandom.globalRandom.getChance(0.8F)) {
            float dx = dirX * (float)GameRandom.globalRandom.getIntBetween(20, 70);
            float dy = dirY * (float)GameRandom.globalRandom.getIntBetween(10, 60) * 0.8F;
            this.getLevel().entityManager.addParticle(x, y, this.explosionTypeSwitcher.next()).sprite(GameResources.puffParticles.sprite(GameRandom.globalRandom.getIntBetween(0, 4), 0, 12)).sizeFades(20, 40).movesFriction(dx * 0.05F, dy * 0.05F, 0.8F).color(ThemeColorRegistry.EMBERGLOW.getRandomColor().darker().darker()).heightMoves(0.0F, 70.0F).lifeTime(lifeTime * 4);
        }

        if (range <= (float)Math.max(this.range - 50, 25) && GameRandom.globalRandom.getChance(0.8F)) {
            float dx = dirX * (float)GameRandom.globalRandom.getIntBetween(140, 150);
            float dy = dirY * (float)GameRandom.globalRandom.getIntBetween(130, 140) * 0.8F;
            this.getLevel().entityManager.addParticle(x, y, this.explosionTypeSwitcher.next()).sprite(GameResources.magicSparkParticles.sprite(GameRandom.globalRandom.getIntBetween(0, 4), 0, 22)).sizeFades(16, 30).movesFriction(dx * 0.05F, dy * 0.05F, 2.0F).color(ThemeColorRegistry.FIRE.getRandomColor()).heightMoves(0.0F, 10.0F).lifeTime(lifeTime * 3);
        }

    }
}
