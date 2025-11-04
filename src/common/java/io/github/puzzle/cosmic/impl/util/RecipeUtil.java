package io.github.puzzle.cosmic.impl.util;

import dev.puzzleshq.puzzleloader.loader.util.ReflectionUtil;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.recipes.CraftingRecipe;
import finalforeach.cosmicreach.items.recipes.CraftingRecipes;
import finalforeach.cosmicreach.items.recipes.PairRecipeRegistry;
import finalforeach.cosmicreach.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RecipeUtil {

    public static void registerFurnaceRecipe(Item input, Item output) {
        Method m = null;
        try {
            m = ReflectionUtil.getMethod(FurnaceRecipe.class, "registerFurnaceRecipe", Item.class, Item.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            m.invoke(null, input, output);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerRecipe(Identifier identifier, CraftingRecipe recipe) {
        Method m = null;
        try {
            m = ReflectionUtil.getMethod(CraftingRecipes.class, "registerRecipe", Identifier.class, CraftingRecipe.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            m.invoke(null, identifier, recipe);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
