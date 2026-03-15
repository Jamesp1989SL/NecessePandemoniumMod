package pandemonium.Patches;
import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.entity.mobs.friendly.human.humanShop.BuyingShopItem;
import necesse.entity.mobs.friendly.human.humanShop.HunterHumanMob;
import net.bytebuddy.asm.Advice;

@ModConstructorPatch(target = HunterHumanMob.class, arguments = {})
public class HunterShopPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This HunterHumanMob hunter) {
        hunter.shop.addBuyingItem("feralwolffur", new BuyingShopItem()).setPriceBasedOnHappiness(20, 10, 3);
    }
}
