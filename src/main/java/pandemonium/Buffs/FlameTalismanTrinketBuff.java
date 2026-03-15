package pandemonium.Buffs;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameMath;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.*;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.TrinketBuff;
import necesse.entity.particle.Particle;
import necesse.gfx.GameResources;
import necesse.gfx.ThemeColorRegistry;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.trinketItem.TrinketItem;
import necesse.level.maps.Level;
import pandemonium.Events.FlameTalismanExplosionEvent;

import java.util.concurrent.atomic.AtomicReference;

public class FlameTalismanTrinketBuff extends TrinketBuff {

    public void init(ActiveBuff buff, BuffEventSubscriber eventSubscriber) {
    }

    public void onBeforeHit(ActiveBuff buff, MobBeforeHitEvent event) {
        super.onBeforeHit(buff, event);
        if (!buff.owner.buffManager.hasBuff("flametalismancooldownbuff")) {
            Level level = buff.owner.getLevel();
            buff.owner.buffManager.addBuff(new ActiveBuff("flametalismancooldownbuff", buff.owner, 45.0F, (Attacker) null), true);
            FlameTalismanExplosionEvent explosionevent = new FlameTalismanExplosionEvent(buff.owner.getX(), buff.owner.getY(), 40, new GameDamage(400.0F), 0.0F, buff.owner);
            level.entityManager.events.add(explosionevent);
            event.prevent();
        }
    }

    public void clientTick(ActiveBuff buff) {
        super.clientTick(buff);
        if (!buff.owner.buffManager.hasBuff("flametalismancooldownbuff")) {
            if (buff.owner.isVisible()) {
                Mob owner = buff.owner;
                AtomicReference<Float> currentAngle = new AtomicReference(GameRandom.globalRandom.nextFloat() * 360.0F);
                float distance = 20.0F;
                owner.getLevel().entityManager.addParticle(owner.x + GameMath.sin((Float)currentAngle.get()) * distance, owner.y + GameMath.cos((Float)currentAngle.get()) * distance * 0.75F, Particle.GType.CRITICAL).color(ThemeColorRegistry.FIRE.getRandomColor()).height(0.0F).moves((pos, delta, lifeTime, timeAlive, lifePercent) -> {
                    float angle = (Float)currentAngle.accumulateAndGet(delta * 150.0F / 250.0F, Float::sum);
                    float distY = distance * 0.75F;
                    pos.x = owner.x + GameMath.sin(angle) * distance;
                    pos.y = owner.y + GameMath.cos(angle) * distY * 0.75F;
                }).lifeTime(1300).sizeFades(16, 24);
            }
            if (buff.owner.isVisible() && GameRandom.globalRandom.getChance((double)0.5F)) {
                Mob owner = buff.owner;
                owner.getLevel().entityManager.addParticle(owner.x + (float)(GameRandom.globalRandom.nextGaussian() * (double)12.0F), owner.y + 23.0F + (float)(GameRandom.globalRandom.nextGaussian() * (double)8.0F), Particle.GType.IMPORTANT_COSMETIC).sprite(GameResources.puffParticles.sprite(GameRandom.globalRandom.nextInt(4), 0, 8)).color(ThemeColorRegistry.EMBERGLOW.getRandomColor()).dontRotate().height(32.0F).movesConstant(0.0F, -4.0F).fadesAlphaTimeToCustomAlpha(100, 100, 0.8F).sizeFades(12, 22).lifeTime(1000);
            }
        }
    }

    public ListGameTooltips getTrinketTooltip(TrinketItem trinketItem, InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTrinketTooltip(trinketItem, item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "flametalismantip1"));
        tooltips.add(Localization.translate("itemtooltip", "flametalismantip2"));
        tooltips.add(Localization.translate("itemtooltip", "flametalismantip3"));
        return tooltips;
    }
}
