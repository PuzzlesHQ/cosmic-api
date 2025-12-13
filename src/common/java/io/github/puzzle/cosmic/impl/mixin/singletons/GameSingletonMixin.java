package io.github.puzzle.cosmic.impl.mixin.singletons;

import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import finalforeach.cosmicreach.singletons.GameSingletons;
import io.github.puzzle.cosmic.impl.event.EventLoadingQueue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static finalforeach.cosmicreach.singletons.GameSingletons.loadingQueue;

@Mixin(GameSingletons.class)
public class GameSingletonMixin {

    @Inject(method = "postCreate", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/items/recipes/CraftingRecipes;loadCraftingRecipes(Lcom/badlogic/gdx/utils/Queue;)V", shift = At.Shift.AFTER))
    private static void postCreate(CallbackInfo ci) {
        EventLoadingQueue event = new EventLoadingQueue(loadingQueue);
        GameRegistries.COSMIC_EVENT_BUS.post(event);
    }
}
