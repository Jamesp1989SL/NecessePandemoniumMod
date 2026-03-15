package pandemonium.Patches;

import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.entity.mobs.friendly.human.humanShop.PirateHumanMob;
import necesse.entity.mobs.friendly.human.humanShop.SellingShopItem;
import net.bytebuddy.asm.Advice;

@ModConstructorPatch(target = PirateHumanMob.class, arguments = {})
public class PirateShopPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This PirateHumanMob pirate) {
        pirate.shop.addSellingItem("blunderbuss", new SellingShopItem()).setStaticPriceBasedOnHappiness(600, 1000, 100);
    }
}
