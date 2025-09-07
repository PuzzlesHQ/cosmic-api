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
    default Zone getZone() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Gets the player position as a blockPosition.
     * @return a {@link BlockPosition}
     */
    default BlockPosition getBlockPosition() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }


    /**
     * Checks if the player is loading.
     */
    default boolean isLoading() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

}
