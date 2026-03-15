package pandemonium.Buffs;

import necesse.engine.util.GameRandom;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;
import necesse.entity.particle.Particle;
import necesse.entity.particle.ParticleOption;
import necesse.gfx.GameResources;
import necesse.gfx.ThemeColorRegistry;
import necesse.gfx.drawOptions.texture.SharedTextureDrawOptions;

import java.awt.*;

public class AthameBuff extends Buff {
    public AthameBuff() {
        this.canCancel = true;
        this.isImportant = true;
    }

    public void init(ActiveBuff buff, BuffEventSubscriber eventSubscriber) {
        buff.setModifier(BuffModifiers.ALL_DAMAGE,0.35F);
    }

    public void clientTick(ActiveBuff buff) {
        super.clientTick(buff);
        if (buff.owner.isVisible() && GameRandom.globalRandom.getChance((double) 0.5F)) {
            Mob owner = buff.owner;
            owner.getLevel().entityManager.addParticle(owner.x + (float) (GameRandom.globalRandom.nextGaussian() * (double) 5.0F), owner.y + 23.0F + (float) (GameRandom.globalRandom.nextGaussian() * (double) 8.0F), Particle.GType.IMPORTANT_COSMETIC).sprite(GameResources.powerupParticle.sprite(0, 0, 8)).color(new Color(123, 15, 197)).dontRotate().height(32.0F).movesConstant(0.0F, -4.0F).fadesAlphaTimeToCustomAlpha(200, 200, 0.9F).size(new ParticleOption.DrawModifier() {
                public void modify(SharedTextureDrawOptions.Wrapper options, int lifeTime, int timeAlive, float lifePercent) {
                    options.size(12, 12);
                }
            }).lifeTime(1000);
        }

    }
}
