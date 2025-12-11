package io.github.puzzle.cosmic.impl.mixin.singletons;

import dev.puzzleshq.mod.api.IModContainer;
import dev.puzzleshq.mod.util.EntrypointPair;
import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.loader.util.ModFinder;
import dev.puzzleshq.puzzleloader.loader.util.PuzzleEntrypointUtil;
import finalforeach.cosmicreach.singletons.GameSingletons;
import io.github.puzzle.cosmic.impl.event.EventLoadingQueue;
import io.github.puzzle.cosmic.impl.util.ModPreloadUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static finalforeach.cosmicreach.singletons.GameSingletons.loadingQueue;

@Mixin(GameSingletons.class)
public class GameSingletonMixin {

    @Inject(method = "postCreate", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/items/recipes/CraftingRecipes;loadCraftingRecipes(Lcom/badlogic/gdx/utils/Queue;)V", shift = At.Shift.AFTER))
    private static void postCreate(CallbackInfo ci) {
        ModPreloadUtil.preloadAllMods();

        EventLoadingQueue event = new EventLoadingQueue(loadingQueue);
        GameRegistries.COSMIC_EVENT_BUS.post(event);
        System.out.println("Firing Event");
    }
}
