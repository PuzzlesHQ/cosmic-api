package io.github.puzzle.cosmic.api.item;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.util.APISide;
import io.github.puzzle.cosmic.util.annotation.Internal;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IItem {

    /**
     * Gets the id of the item.
     * @return a {@link Identifier}
     */
    Identifier getIdentifier();

    /**
     * Uses the item.
     * @param side the network side the item is use on.
     * @param itemSlot the slot the item is in.
     * @param player the player using the item.
     * @param targetPlaceBlockPos the block targeted for placing.
     * @param targetBreakBlockPos the block targeted for breaking.
     * @param isLeftClick is left click used.
     * @return {@code true} if successful.
     */
    default boolean use(APISide side, ItemSlot itemSlot, Player player, BlockPosition targetPlaceBlockPos, BlockPosition targetBreakBlockPos, boolean isLeftClick) {
        return false;
    }

    @Internal
    default void setModded(boolean m) {}

    @Internal
    default boolean isModded() {
        return true;
    }

    /**
     * Checks if the item is a tool.
     */
    default boolean isTool() {
        return false;
    }

    /**
     * Checks if the item can break the blockState.
     * @param blockState blockState to break.
     * @return {@code true} if successful.
     */
    default boolean canBreakBlockWith(BlockState blockState) {
        return true;
    }

    /**
     * Checks if the item can interact with the blockState.
     * @param blockState blockState to interact with.
     * @return {@code true} if successful.
     */
    default boolean canInteractWithBlock(BlockState blockState) {
        return true;
    }

    /**
     * Checks if the item can break the blockState.
     * @param blockState blockState to break.
     * @return {@code true} if successful.
     */
    default boolean canInteractWithBlockEntity(BlockState blockState) {
        return true;
    }

    /**
     * Gets the point manifest of the item.
     * @return a {@link IDataPointManifest}
     */
    default IDataPointManifest getPointManifest() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * set the point manifest of the item.
     * @param manifest The new point manifest to be set.
     */
    default void setPointManifest(IDataPointManifest manifest) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    enum ItemModelType {
        ITEM_MODEL_2D,
        ITEM_MODEL_3D
    }

}
