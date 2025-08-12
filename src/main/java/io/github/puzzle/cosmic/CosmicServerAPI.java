package io.github.puzzle.cosmic;

import dev.puzzleshq.puzzleloader.cosmic.core.modInitialises.PostModInit;
import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.cosmic.game.events.net.EventRegisterPacket;
import io.github.puzzle.cosmic.impl.network.item.ItemUsePacket;
import io.github.puzzle.cosmic.item.AbstractCosmicItem;
import io.github.puzzle.cosmic.item.BlockWrench;
import io.github.puzzle.cosmic.item.CheckBoard;
import io.github.puzzle.cosmic.item.NullStick;
import net.neoforged.bus.api.SubscribeEvent;

public class CosmicServerAPI implements PostModInit {

    public CosmicServerAPI() {
        GameRegistries.COSMIC_EVENT_BUS.register(this);
    }

    @Override
    public void onPostInit() {
        AbstractCosmicItem.register(new CheckBoard());
        AbstractCosmicItem.register(new NullStick());
        AbstractCosmicItem.register(new BlockWrench());
    }

    @SubscribeEvent
    public void register(EventRegisterPacket event) {
        event.registerReservedPacket("item-use", 9003, ItemUsePacket.class);
    }
}
