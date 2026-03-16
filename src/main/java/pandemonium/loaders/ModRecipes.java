package pandemonium.loaders;

import necesse.engine.registries.RecipeTechRegistry;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;

/**
 * Here is where we will register our recipes into the game.
 * There is potentially quite a few of them so this will allow us to maintain cleaner code
*/
public class ModRecipes {

    //Put your recipe registrations in here
    public static void registerRecipes(){

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

        Recipes.registerModRecipe(new Recipe(
                "furcloaktrinket",
                1,
                RecipeTechRegistry.WORKSTATION,
                new Ingredient[]{
                        new Ingredient("feralwolffur", 32)
                }
        ).showAfter("claygauntlet"));

    }
}
