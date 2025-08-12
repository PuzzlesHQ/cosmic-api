package io.github.puzzle.cosmic;

import dev.puzzleshq.puzzleloader.cosmic.core.modInitialises.ClientModInit;
import dev.puzzleshq.puzzleloader.cosmic.core.modInitialises.ClientPostModInit;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.Threads;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.item.ITickingItem;
import io.github.puzzle.cosmic.impl.client.item.ItemShader;

public class CosmicClientAPI implements ClientPostModInit, ClientModInit {

    public CosmicClientAPI() {
//        LoaderConstants.CORE_EVENT_BUS.register(this);
    }

    @Override
    public void onClientPostInit() {
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
    public void onClientInit() {
        Threads.runOnMainThread(ItemShader::initItemShader);
    }

//    @SubscribeEvent
//    public void onEvent(OnRegisterEvent event) {
//        if (event.obj instanceof Item item) {
//            if (!CosmicItemModel.hasItemModel(item))
//                CosmicItemModel.registerItemModel(item);
//        }
//    }
}
