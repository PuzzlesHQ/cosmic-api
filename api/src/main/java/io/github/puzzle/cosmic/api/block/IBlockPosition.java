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
    Chunk getChunk();



    /**
     * Directly set the blockEntity of the blockPosition.
     * @param blockState blockState of the blockEntity.
     * @param blockEntity the blockEntity to set.
     */
    void setBlockEntityDirect(PuzzleBlockState blockState, IBlockEntity blockEntity);

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
    void setGlobal(Zone zone, float x, float y, float z);

    /**
     * Updates the neighbouring blockEntity.
     * @param event event to update the blockEntity with.
     * @see BlockUpdateEvent
     */
    void updateNeighbors(BlockUpdateEvent event);

    /**
     * Updates the neighbouring blockEntity in a direction.
     * @param direction the direction to update in.
     * @param event event to update the blockEntity with.
     * @see Direction
     * @see BlockUpdateEvent
     */
    void updateNeighborInDirection(BlockUpdateEvent event, Direction direction);
}
