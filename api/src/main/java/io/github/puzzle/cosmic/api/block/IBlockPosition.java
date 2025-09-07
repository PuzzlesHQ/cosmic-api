package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.util.constants.Direction;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import org.jetbrains.annotations.Nullable;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IBlockPosition {

      /**
     * Gets the chunk of the blockPosition.
     * @return a {@link Chunk}
     */
    @Nullable
    default Chunk getChunk() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    };



    /**
     * Directly set the blockEntity of the blockPosition.
     * @param blockState blockState of the blockEntity.
     * @param blockEntity the blockEntity to set.
     */
    default void setBlockEntityDirect(PuzzleBlockState blockState, IBlockEntity blockEntity)  {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Checks if the blockPosition has a blockEntity.
     */
    default boolean hasBlockEntity() {
        return ((BlockPosition)(Object)this).getBlockEntity() != null;
    }

    /**
     * Sets the global positions.
     * @param zone new zone.
     * @param x new X.
     * @param y new Y.
     * @param z new Z.
     */
    default void setGlobal(Zone zone, float x, float y, float z)  {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Updates the neighbouring blockEntity.
     * @param event event to update the blockEntity with.
     * @see BlockUpdateEvent
     */
    default void updateNeighbors(BlockUpdateEvent event)  {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Updates the neighbouring blockEntity in a direction.
     * @param direction the direction to update in.
     * @param event event to update the blockEntity with.
     * @see Direction
     * @see BlockUpdateEvent
     */
    default void updateNeighborInDirection(BlockUpdateEvent event, Direction direction)  {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

}
