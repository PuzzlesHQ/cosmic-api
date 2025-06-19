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
    int getLocalX();
    /**
     * Gets the Local X of the blockEntity.
     */
    int getLocalY();

    /**
     * Gets the Local X of the blockEntity.
     */
    int getLocalZ();

    /**
     * Gets the blockPosition of the blockEntity.
     *
     * @return a {@link BlockPosition}
     */
    BlockPosition getBlockPosition();

    /**
     * Gets the chunk of the blockEntity.
     * @return a {@link Chunk}
     */
    Chunk getChunk();


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
    void updateNeighborInDirection(Direction direction, BlockUpdateEvent event);

    /**
     * Triggered when the blockEntity is updated.
     * @param event the event the blockEntity is updated with.
     * @see BlockUpdateEvent
     */
    void onNeighborUpdate(BlockUpdateEvent event);

    /**
     * Gets the slotContainer of the BlockEntity.
     * @return a {@link SlotContainer} will be {@code null} if the blockEntity has no slotContainer.
     */
    SlotContainer getSlotContainer();

    /**
     * Gets the point manifest of the blockEntity.
     * @return a {@link IDataPointManifest}
     */
    IDataPointManifest getPointManifest();

    /**
     * Sets the point manifest of the blockEntity.
     * @param manifest The new point manifest to be set.
     */
    void setPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer deserial);

    @Override
    void write(CRBinSerializer serial);

}
