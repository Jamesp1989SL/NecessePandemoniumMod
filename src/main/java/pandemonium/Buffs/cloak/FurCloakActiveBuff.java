package pandemonium.Buffs.cloak;


import pandemonium.Buffs.cloak.base.BaseCloakActiveBuff;
import pandemonium.loaders.ModBuffs;

public class FurCloakActiveBuff extends BaseCloakActiveBuff {

    @Override
    protected String getActiveBuffStringID() {
        return ModBuffs.FUR_CLOAK_ACTIVE;
    }

    @Override
    protected String getCooldownBuffStringID() {
        return ModBuffs.FUR_CLOAK_COOLDOWN;
    }

    @Override
    protected float getDefaultCooldownSeconds() {
        return 12.0F;
    }

    @Override
    protected float getAllDamageBonus() {
        return 5.0F;
    }

    @Override
    protected boolean cancelOnDamage() {
        return true;
    }

}