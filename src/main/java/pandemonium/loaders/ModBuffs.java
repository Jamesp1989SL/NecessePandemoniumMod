package pandemonium.loaders;

import necesse.engine.registries.BuffRegistry;
import pandemonium.Buffs.*;
import pandemonium.Buffs.cloak.FurCloakActiveBuff;
import pandemonium.Buffs.cloak.FurCloakCooldownBuff;
import pandemonium.Buffs.cloak.FurCloakTrinketBuff;

public class ModBuffs {

    public static final String FUR_CLOAK_TRINKET_BUFF = "furcloaktrinket";
    public static final String FUR_CLOAK_ACTIVE = "furcloakactive";
    public static final String FUR_CLOAK_COOLDOWN = "furcloakcooldown";

    public static void load(){

        BuffRegistry.registerBuff("athametrinketbuff", new AthameTrinketBuff());
        BuffRegistry.registerBuff("athamebuff", new AthameBuff());
        BuffRegistry.registerBuff("baitearringtrinketbuff", new BaitEarringTrinketBuff());
        BuffRegistry.registerBuff("flametalismancooldownbuff", new FlameTalismanCooldownBuff());
        BuffRegistry.registerBuff("flametalismantrinketbuff", new FlameTalismanTrinketBuff());

        // Cloak
        BuffRegistry.registerBuff(FUR_CLOAK_TRINKET_BUFF, new FurCloakTrinketBuff());
        BuffRegistry.registerBuff(FUR_CLOAK_ACTIVE, new FurCloakActiveBuff());
        BuffRegistry.registerBuff(FUR_CLOAK_COOLDOWN, new FurCloakCooldownBuff());

    }

}
