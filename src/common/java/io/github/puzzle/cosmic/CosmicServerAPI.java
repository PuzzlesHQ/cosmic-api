package io.github.puzzle.cosmic;

import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.cosmic.game.events.net.EventRegisterPacket;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.ModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PostModInit;
import finalforeach.cosmicreach.items.Item;
import io.github.puzzle.cosmic.impl.event.EventLoadingQueue;
import io.github.puzzle.cosmic.impl.network.item.ItemUsePacket;
import io.github.puzzle.cosmic.impl.util.RecipeUtil;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.BlockWrench;
import io.github.puzzle.cosmic.item.CheckBoard;
import io.github.puzzle.cosmic.item.NullStick;
import net.neoforged.bus.api.SubscribeEvent;

public class CosmicServerAPI implements ModInit, PostModInit {

    public CosmicServerAPI() {
        GameRegistries.NETWORK_EVENT_BUS.register(this);
        GameRegistries.COSMIC_EVENT_BUS.register(this);
        System.out.println("Event Init");
    }

    @Override
    public void onInit() {
    }

    @SubscribeEvent
    public void onEvent(EventLoadingQueue loadingQueue) {
        System.out.println("Event Registered");
        loadingQueue.registerToQueue(() -> {
            System.out.println("Event Ran!");
        });
    }

    @SubscribeEvent
    public void register(EventRegisterPacket event) {
        event.registerReservedPacket("item-use", 9003, ItemUsePacket.class);
    }

    @Override
    public void onPostInit() {
        AbstractCosmicItem.register(new CheckBoard());
        AbstractCosmicItem.register(new NullStick());
        AbstractCosmicItem.register(new BlockWrench());
    }
}
