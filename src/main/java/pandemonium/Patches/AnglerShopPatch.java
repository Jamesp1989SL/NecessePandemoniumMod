package pandemonium.Patches;

import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.entity.mobs.friendly.human.humanShop.AnglerHumanMob;
import necesse.entity.mobs.friendly.human.humanShop.SellingShopItem;
import net.bytebuddy.asm.Advice;

@ModConstructorPatch(target = AnglerHumanMob.class, arguments = {})
public class AnglerShopPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This AnglerHumanMob angler) {
        angler.shop.addSellingItem("baitearring", new SellingShopItem()).setStaticPriceBasedOnHappiness(700, 900, 50);
    }
}
