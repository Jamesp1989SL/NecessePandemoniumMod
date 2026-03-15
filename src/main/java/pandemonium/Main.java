package pandemonium;

import necesse.inventory.lootTable.presets.*;
import pandemonium.Buffs.*;
import pandemonium.Events.BloodSplashEvent;
import pandemonium.Events.CorrosiveGasCloudEvent;
import pandemonium.Events.FlameTalismanExplosionEvent;
import pandemonium.Items.ArmorItems.*;
import pandemonium.Items.WeaponItems.*;
import pandemonium.Mobs.*;
import pandemonium.Projectiles.*;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.*;
import necesse.engine.sound.gameSound.GameSound;
import necesse.entity.mobs.hostile.JackalMob;
import necesse.gfx.gameTexture.GameTexture;
import necesse.inventory.item.Item;
import necesse.inventory.item.trinketItem.SimpleTrinketItem;
import necesse.inventory.lootTable.lootItem.ChanceLootItem;
import necesse.inventory.lootTable.lootItem.LootItem;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import necesse.level.maps.biomes.Biome;

import static necesse.gfx.GameResources.particlesTextureGenerator;

@ModEntry
public class Main {

    public void init() {
        ItemRegistry.registerItem("gravebuster", new GraveBusterToolItem(), 115, true);
        ItemRegistry.registerItem("alicegrimoire", new AliceGrimoire(), 100, true);
        ItemRegistry.registerItem("corrosiveflask", new CorrosiveFlaskToolItem(), 100, true);
        ItemRegistry.registerItem("engineerswrench", new EngineersWrenchToolItem(), 55, true);
        ItemRegistry.registerItem("woodenclub", new WoodenClubToolItem(), 16, true);
        ItemRegistry.registerItem("anubisblade", new AnubisBladeToolItem(), 400, true);
        ItemRegistry.registerItem("blunderbuss", new BlunderbussToolItem(), 500, true);
        ItemRegistry.registerItem("equibilirium", new EquibiliriumToolItem(), 500, true);
        ItemRegistry.registerItem("naginata", new NaginataToolItem(), 500, true);

        ItemRegistry.registerItem("crystalcatcher", new CrystalCatcherRodItem(), 375, true);

        ItemRegistry.registerItem("athame", new SimpleTrinketItem(Item.Rarity.UNCOMMON, "athametrinketbuff", 600, TrinketsLootTable.trinkets), 200.0F, true);
        ItemRegistry.registerItem("baitearring", new SimpleTrinketItem(Item.Rarity.UNCOMMON, "baitearringtrinketbuff", 500, TrinketsLootTable.trinkets), 400.0F, true);
        ItemRegistry.registerItem("flametalisman", new SimpleTrinketItem(Item.Rarity.UNCOMMON, "flametalismantrinketbuff", 500, TrinketsLootTable.trinkets), 400.0F, true);

        ItemRegistry.registerItem("feralwolffur", new FeralWolfFurMaterialItem(), 8, true);

        ItemRegistry.registerItem("radicalglasses", new RadicalGlasses(), 50, true);

        ProjectileRegistry.registerProjectile("alicegrimoireprojectile", AliceGrimoireProjectile.class, "", "");
        ProjectileRegistry.registerProjectile("engineerswrench", EngineersWrenchProjectile.class, "engineerswrench", "engineerswrench_shadow");
        ProjectileRegistry.registerProjectile("sunwaveprojectile", SunWaveProjectile.class, "sunwave", "");
        ProjectileRegistry.registerProjectile("corrosiveflaskprojectile", CorrosiveFlaskProjectile.class, "corrosiveflask", "corrosiveflask_shadow");

        BuffRegistry.registerBuff("athametrinketbuff", new AthameTrinketBuff());
        BuffRegistry.registerBuff("athamebuff", new AthameBuff());
        BuffRegistry.registerBuff("baitearringtrinketbuff", new BaitEarringTrinketBuff());
        BuffRegistry.registerBuff("flametalismancooldownbuff", new FlameTalismanCooldownBuff());
        BuffRegistry.registerBuff("flametalismantrinketbuff", new FlameTalismanTrinketBuff());

        MobRegistry.registerMob("feralwolf", FeralWolfMob.class, true);

        LevelEventRegistry.registerEvent("flametalismanexplosion", FlameTalismanExplosionEvent.class);
        LevelEventRegistry.registerEvent("bloodsplash", BloodSplashEvent.class);
        LevelEventRegistry.registerEvent("corrosivegascloud", CorrosiveGasCloudEvent.class);

    }

    public static GameSound WrenchHit;
    public void initResources() {
        WrenchHit = GameSound.fromFile("pandemonium/wrenchhit");
        FeralWolfMob.texture = GameTexture.fromFile("mobs/feralwolf");
        EngineersWrenchProjectile.engineerswrenchparticletexture = particlesTextureGenerator.addTexture(GameTexture.fromFile("projectiles/engineerswrench"));
    }

    public void postInit() {
        Recipes.registerModRecipe(new Recipe(
                "engineerswrench",
                1,
                RecipeTechRegistry.DEMONIC_ANVIL,
                new Ingredient[]{
                        new Ingredient("wrench", 1),
                        new Ingredient("demonicbar", 6)
                }
        ).showAfter("demonicspear"));
        Recipes.registerModRecipe(new Recipe(
                "crystalcatcher",
                1,
                RecipeTechRegistry.TUNGSTEN_WORKSTATION,
                new Ingredient[]{
                        new Ingredient("amethyst", 6),
                        new Ingredient("sapphire", 6),
                        new Ingredient("emerald", 6),
                        new Ingredient("ruby", 6),
                        new Ingredient("topaz", 6)
                }
        ).showAfter("woodfishingrod"));
        Recipes.registerModRecipe(new Recipe(
                "gravebuster",
                1,
                RecipeTechRegistry.DEMONIC_ANVIL,
                new Ingredient[]{
                        new Ingredient("irongreatsword", 1),
                        new Ingredient("batwing", 10),
                        new Ingredient("demonicbar", 7)
                }
        ).showAfter("engineerswrench"));
        Recipes.registerModRecipe(new Recipe(
                "woodenclub",
                1,
                RecipeTechRegistry.WORKSTATION,
                new Ingredient[]{
                        new Ingredient("anylog", 16)
                }
        ).showAfter("woodsword"));

        CaveCryptLootTable.uniqueItems.items.add (
                new LootItem("gravebuster")
        );
        DeepCaveChestLootTable.basicMainItems.items.add (
                new LootItem("flametalisman")
        );
        DungeonChestLootTable.mainItems.items.add (
                new LootItem("athame")
        );
        PirateDisplayStandLootTable.items.items.add (
                new LootItem("blunderbuss")
        );
        JackalMob.lootTable.items.add (
                new ChanceLootItem(0.01F, "anubisblade")
        );
        Biome.defaultSurfaceMobs.add(60, "feralwolf");

    }
}
