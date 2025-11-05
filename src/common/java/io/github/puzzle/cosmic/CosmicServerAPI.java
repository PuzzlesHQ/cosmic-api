package io.github.puzzle.cosmic;

import dev.puzzleshq.puzzleloader.cosmic.game.GameRegistries;
import dev.puzzleshq.puzzleloader.cosmic.game.events.net.EventRegisterPacket;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.ModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PostModInit;
import finalforeach.cosmicreach.items.Item;
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
    }

    @Override
    public void onInit() {
        RecipeUtil.registerCrusherRecipe(Item.getItem("base:medkit"), Item.getItem("base:medkit_gold"), 100);
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
