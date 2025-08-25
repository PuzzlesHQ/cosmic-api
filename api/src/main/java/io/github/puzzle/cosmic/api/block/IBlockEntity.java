package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import finalforeach.cosmicreach.util.constants.Direction;
import finalforeach.cosmicreach.world.Chunk;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;


/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockEntity")
public interface IBlockEntity extends ICRBinSerializable {

    /**
     * Gets the Local X of the blockEntity.
     */
    default int getLocalX() {
        return Integer.MAX_VALUE;
    }

    /**
     * Gets the Local X of the blockEntity.
     */
    default int getLocalY() {
        return Integer.MAX_VALUE;
    }

    /**
     * Gets the Local X of the blockEntity.
     */
    default int getLocalZ() {
        return Integer.MAX_VALUE;
    }

    /**
     * Gets the blockPosition of the blockEntity.
     *
     * @return a {@link BlockPosition}
     */
    default BlockPosition getBlockPosition() {
        return null;
    }

    /**
     * Gets the chunk of the blockEntity.
     * @return a {@link Chunk}
     */
    default Chunk getChunk() {
        return null;
    }


    /**
     * Updates the neighbouring blockEntity.
     * @param event event to update the blockEntity with.
     * @see BlockUpdateEvent
     */
    default void updateNeighbors(BlockUpdateEvent event) {
    }

    /**
     * Updates the neighbouring blockEntity in a direction.
     * @param direction the direction to update in.
     * @param event event to update the blockEntity with.
     * @see Direction
     * @see BlockUpdateEvent
     */
    default void updateNeighborInDirection(Direction direction, BlockUpdateEvent event) {
    }

    /**
     * Triggered when the blockEntity is updated.
     * @param event the event the blockEntity is updated with.
     * @see BlockUpdateEvent
     */
    default void onNeighborUpdate(BlockUpdateEvent event) {
    }

    /**
     * Gets the slotContainer of the BlockEntity.
     * @return a {@link SlotContainer} will be {@code null} if the blockEntity has no slotContainer.
     */
    default SlotContainer getSlotContainer() {
        return null;
    }

    /**
     * Gets the point manifest of the blockEntity.
     * @return a {@link IDataPointManifest}
     */
    default IDataPointManifest getPointManifest() {
        return null;
    }

    /**
     * Sets the point manifest of the blockEntity.
     * @param manifest The new point manifest to be set.
     */
    default void setPointManifest(IDataPointManifest manifest) {
    }

    // ICRBinSerializable.java methods

    @Override
    default void read(CRBinDeserializer deserial) {
    }

    @Override
    default void write(CRBinSerializer serial) {
    }

}
