package io.github.puzzle.cosmic.impl.util;

import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.recipes.CraftingRecipe;
import finalforeach.cosmicreach.items.recipes.CraftingRecipes;
import finalforeach.cosmicreach.items.recipes.PairRecipe;
import finalforeach.cosmicreach.items.recipes.PairRecipeRegistry;
import finalforeach.cosmicreach.util.Identifier;

public class RecipeUtil {

    public static void registerFurnaceRecipe(Item input, Item output) {
        PairRecipeRegistry pairreciperegistry = PairRecipe.allPairRecipeRegisteries.get("furnace");
        pairreciperegistry.registerPairRecipe(input, output, 1);
    }

    public static void registerCrusherRecipe(Item input, Item output, int outputAmount) {
        PairRecipeRegistry pairreciperegistry = PairRecipe.allPairRecipeRegisteries.get("crusher");
        pairreciperegistry.registerPairRecipe(input, output, outputAmount);
    }

    public static void registerRecipe(Identifier identifier, CraftingRecipe recipe) {
        CraftingRecipes.registerRecipe(identifier, recipe);
    }
}
