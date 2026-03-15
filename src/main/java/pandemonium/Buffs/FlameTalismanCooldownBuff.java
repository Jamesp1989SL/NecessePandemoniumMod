package pandemonium.Buffs;

import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.staticBuffs.Buff;
import necesse.gfx.GameResources;
import necesse.gfx.gameTooltips.ListGameTooltips;

public class FlameTalismanCooldownBuff extends Buff {
    public FlameTalismanCooldownBuff() {
        this.canCancel = false;
        this.isImportant = true;
    }

    public void init(ActiveBuff buff, BuffEventSubscriber eventSubscriber) {
    }

    public ListGameTooltips getTooltip(ActiveBuff ab, GameBlackboard blackboard) {
        return super.getTooltip(ab, blackboard);
    }

    public void onRemoved(ActiveBuff buff) {
        if (buff.owner.isClient()) {
            SoundManager.playSound(GameResources.fireShot, SoundEffect.effect(buff.owner.x, buff.owner.y).volume(0.6F).pitch(1.0F));
        }
    }
}
