package io.github.puzzle.cosmic;

import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.cosmic.game.events.net.EventRegisterPacket;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientPostModInit;
import finalforeach.cosmicreach.Threads;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.singletons.GameSingletons;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.item.ITickingItem;
import io.github.puzzle.cosmic.impl.client.item.ItemShader;
import io.github.puzzle.cosmic.impl.network.item.ItemUsePacket;
import net.neoforged.bus.api.SubscribeEvent;

public class CosmicClientAPI implements ClientPostModInit, ClientModInit {

    public CosmicClientAPI() {
        GameRegistries.NETWORK_EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void register(EventRegisterPacket event) {
        event.registerPacket("item-use", 9003, ItemUsePacket.class);
    }

    @Override
    public void onClientPostInit() {
        GameSingletons.updateObservers.add(fixedUpdateTimeStep -> {
            if (InGame.getLocalPlayer() != null && UI.hotbar.getContainer() != null) {

                for (int ic = 0; ic < UI.openContainers.size; ic++) {
                    for (int i = 0; i < UI.openContainers.get(ic).getNumSlots(); i++) {
                        ItemSlot slot = UI.openContainers.get(ic).getSlot(i);

                        if (slot != null && slot.getItemStack() != null) {
                            if (slot.getItemStack().getItem() instanceof ITickingItem item) {
                                if (UI.openContainers.get(ic) == UI.hotbar.getContainer()){
                                    item.tickStack(fixedUpdateTimeStep, slot.getItemStack(), UI.hotbar.getSelectedSlot() == slot);
                                } else {
                                    item.tickStack(fixedUpdateTimeStep, slot.getItemStack(), false);
                                }
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
