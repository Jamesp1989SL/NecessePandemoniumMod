package pandemonium.Buffs.cloak;

import pandemonium.Buffs.cloak.base.BaseCloakAbilityTrinketBuff;
import pandemonium.loaders.ModBuffs;

public class FurCloakTrinketBuff extends BaseCloakAbilityTrinketBuff {
    @Override
    protected String getActiveBuffStringID() {
        return ModBuffs.FUR_CLOAK_ACTIVE;
    }

    @Override
    protected String getCooldownBuffStringID() {
        return ModBuffs.FUR_CLOAK_COOLDOWN;
    }

    @Override
    protected float getRechargeSeconds() {
        return 12.0F;
    }

}
