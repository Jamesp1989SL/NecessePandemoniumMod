package pandemonium.Patches;

import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.entity.mobs.friendly.human.humanShop.SellingShopItem;
import necesse.entity.mobs.friendly.human.humanShop.StylistHumanMob;
import net.bytebuddy.asm.Advice;

@ModConstructorPatch(target = StylistHumanMob.class, arguments = {})
public class StylistShopPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This StylistHumanMob stylist) {
        stylist.shop.addSellingItem("radicalglasses", new SellingShopItem()).setStaticPriceBasedOnHappiness(75, 150, 20);
    }
}
