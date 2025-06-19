package io.github.puzzle.cosmic.api.entity.player;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.world.Zone;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IPlayer {

    /**
     * Gets the zone the player is in.
     * @return a {@link Zone}
     */
    Zone getZone();

    /**
     * Gets the player position as a blockPosition.
     * @return a {@link BlockPosition}
     */
    BlockPosition getBlockPosition();


    /**
     * Checks if the player is loading.
     */
    boolean isLoading();

}
