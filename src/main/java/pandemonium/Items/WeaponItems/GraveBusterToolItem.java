package pandemonium.Items.WeaponItems;

import necesse.entity.mobs.GameDamage;
import pandemonium.Events.BloodSplashEvent;
import pandemonium.Items.WeaponItems.AttackHandlers.GraveBusterAttackHandler;
import necesse.engine.localization.Localization;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.util.GameBlackboard;
import necesse.engine.util.GameRandom;
import necesse.entity.levelEvent.LevelEvent;
import necesse.entity.levelEvent.mobAbilityLevelEvent.MobHealthChangeEvent;
import necesse.entity.levelEvent.mobAbilityLevelEvent.ToolItemMobAbilityEvent;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.itemAttacker.ItemAttackSlot;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.swordToolItem.greatswordToolItem.GreatswordToolItem;
import necesse.level.maps.Level;

public class GraveBusterToolItem extends GreatswordToolItem{
    public GraveBusterToolItem() {
        super(400, null, getThreeChargeLevels(500, 600, 700));
        this.rarity = Rarity.UNCOMMON;
        this.attackDamage.setBaseValue(80.0F).setUpgradedValue(1.0F, 185.5F);
        this.attackRange.setBaseValue(115);
        this.knockback.setBaseValue(150);
        this.canBeUsedForRaids = true;
    }
    protected int lifestealrandom;

    @Override
    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
        tooltips.add(Localization.translate("itemtooltip", "gravebustertip"));
        return tooltips;
    }

    @Override
    public void hitMob(InventoryItem item, ToolItemMobAbilityEvent event, Level level, Mob target, Mob attacker) {
        super.hitMob(item, event, level, target, attacker);
        lifestealrandom = GameRandom.globalRandom.getIntBetween(-1, 1);
        if (attacker != null && attacker.isServer()) {
            if (target.isHostile) {
                LevelEvent lifestealevent = new MobHealthChangeEvent(attacker, (int) ((this.getAttackDamageValue(item, attacker) * 0.05) + lifestealrandom));
                level.entityManager.events.add(lifestealevent);
                BloodSplashEvent splashevent = new BloodSplashEvent(target.x, target.y, 50, new GameDamage(0.0F), false, 0.0F, attacker);
                level.entityManager.events.add(splashevent);
            }
        }
    }

    @Override
    public InventoryItem onAttack(Level level, int x, int y, ItemAttackerMob attackerMob, int attackHeight, InventoryItem item, ItemAttackSlot slot, int animAttack, int seed, GNDItemMap mapContent) {
        attackerMob.startAttackHandler(new GraveBusterAttackHandler(attackerMob, slot, item, this, seed, x, y, this.chargeLevels));
        return item;
    }
}
