package io.github.puzzle.cosmic;

import com.github.puzzle.core.Constants;
import com.github.puzzle.core.loader.launch.provider.mod.entrypoint.impls.ClientModInitializer;
import com.github.puzzle.core.loader.launch.provider.mod.entrypoint.impls.ClientPostModInitializer;
import com.github.puzzle.game.events.OnRegisterEvent;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.Threads;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.item.ITickingItem;
import io.github.puzzle.cosmic.impl.client.item.CosmicItemModel;
import io.github.puzzle.cosmic.impl.client.item.ItemShader;
import meteordevelopment.orbit.EventHandler;

public class CosmicClientAPI implements ClientPostModInitializer, ClientModInitializer {

    public CosmicClientAPI() {
        Constants.EVENT_BUS.subscribe(this);
    }

    @Override
    public void onPostInit() {
        GameSingletons.updateObservers.add(fixedUpdateTimeStep -> {
            if (InGame.getLocalPlayer() != null && UI.hotbar.getContainer() != null) {
                for (int i = 0; i < UI.hotbar.getContainer().getNumSlots(); i++) {
                    ItemSlot slot = UI.hotbar.getContainer().getSlot(i);

                    if (slot != null) {
                        if (slot.getItemStack() != null && slot.getItemStack().getItem() instanceof ITickingItem item) {
                            item.tickStack(fixedUpdateTimeStep, (IItemStack) slot.getItemStack(), UI.hotbar.getSelectedSlot() == slot);
                        }
                    }
                }

                for (int ic = 0; ic < UI.openContainers.size; ic++) {
                    for (int i = 0; i < UI.openContainers.get(ic).getNumSlots(); i++) {
                        ItemSlot slot = UI.openContainers.get(ic).getSlot(i);

                        if (slot != null && slot.getItemStack() != null) {
                            if (slot.getItemStack().getItem() instanceof ITickingItem item) {
                                item.tickStack(fixedUpdateTimeStep, (IItemStack) slot.getItemStack(), false);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onInit() {
        Threads.runOnMainThread(ItemShader::initItemShader);
    }

    @EventHandler
    public void onEvent(OnRegisterEvent event) {
        if (event.obj instanceof Item item) {
            if (!CosmicItemModel.hasItemModel(item))
                CosmicItemModel.registerItemModel(item);
        }
    }
}
