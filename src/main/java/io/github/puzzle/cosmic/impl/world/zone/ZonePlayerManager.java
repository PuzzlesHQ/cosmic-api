package io.github.puzzle.cosmic.impl.world.zone;

import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IZone;

import java.util.function.Consumer;

public class ZonePlayerManager implements IZone.IPlayerManager {

    Zone puzzleLoader$zone;

    public ZonePlayerManager(Zone zone) {
        this.puzzleLoader$zone = zone;
    }

    @Override
    public void addPlayer(Player player) {
        puzzleLoader$zone.addPlayer(player);
    }

    @Override
    public void removePlayer(Player player) {
        puzzleLoader$zone.removePlayer(player);
    }

    @Override
    public void forEachPlayer(Consumer<Player> consumer) {
        puzzleLoader$zone.forEachPlayer(consumer);
    }

    @Override
    public Array<Player> getPlayers() {
        return puzzleLoader$zone.getPlayers();
    }

}
