package pandemonium.Events;

import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.engine.sound.SoundSettings;
import necesse.engine.util.GameRandom;
import necesse.entity.levelEvent.mobAbilityLevelEvent.GroundEffectEvent;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.MobHitCooldowns;
import necesse.entity.particle.Particle;
import necesse.gfx.GameResources;
import necesse.gfx.ThemeColorRegistry;
import necesse.level.maps.LevelObjectHit;

import java.awt.*;

public class CorrosiveGasCloudEvent extends GroundEffectEvent {
    protected int tickCounter;
    protected MobHitCooldowns hitCooldowns;
    protected GameDamage damage;

    public CorrosiveGasCloudEvent() {
    }

    public CorrosiveGasCloudEvent(Mob owner, int x, int y, GameRandom uniqueIDRandom, GameDamage damage) {
        super(owner, x, y, uniqueIDRandom);
        this.damage = damage;
    }

    public void init() {
        super.init();
        this.tickCounter = 0;
        this.hitCooldowns = new MobHitCooldowns();
        if (this.isServer()) {
            SoundManager.playSound(GameResources.splash, SoundEffect.effect(this.x, this.y).falloffDistance(1200).volume(0.1F).pitch(GameRandom.globalRandom.getFloatBetween(1.15F, 1.25F)));
            SoundManager.playSound(GameResources.shatter1, SoundEffect.effect(this.x, this.y).volume(0.5F).pitch(GameRandom.globalRandom.getFloatBetween(1.15F, 1.25F)));
        }
    }

    public Shape getHitBox() {
        int width = 126;
        int height = 126;
        return new Rectangle(this.x - width / 2, this.y - height / 2, width, height);
    }

    public void clientHit(Mob target) {
        target.startHitCooldown();
        this.hitCooldowns.startCooldown(target);
    }

    public void serverHit(Mob target, boolean clientSubmitted) {
        if (clientSubmitted || this.hitCooldowns.canHit(target)) {
            target.isServerHit(this.damage, 0.0F, 0.0F, 0.0F, this.owner);
            this.hitCooldowns.startCooldown(target);
        }
    }

    public void hitObject(LevelObjectHit hit) {
        hit.getLevelObject().attackThrough(this.damage, this.owner);
    }

    public boolean canHit(Mob mob) {
        return super.canHit(mob) && this.hitCooldowns.canHit(mob);
    }

    public void clientTick() {
        ++this.tickCounter;
        for (int i = 0; i < 5; ++i) {
            this.level.entityManager.addParticle((float)this.x + GameRandom.globalRandom.getFloatBetween(-55.0F, 55.0F), (float)this.y + GameRandom.globalRandom.getFloatBetween(-55.0F, 55.0F), Particle.GType.CRITICAL).sprite(GameResources.puffParticles.sprite(GameRandom.globalRandom.nextInt(4), 0, 12)).color(ThemeColorRegistry.WEAKNESS.getRandomColor()).fadesAlphaTimeToCustomAlpha(20, 250, 0.75F).sizeFades(36, 42);
        }
        if (this.tickCounter > 200) {
            this.over();
        } else {
            super.clientTick();
        }

    }

    public void serverTick() {
        ++this.tickCounter;
        if (this.tickCounter > 300) {
            this.over();
        } else {
            super.serverTick();
        }

    }
}
