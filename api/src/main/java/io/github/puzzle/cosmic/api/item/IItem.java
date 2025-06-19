package io.github.puzzle.cosmic.api.item;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.util.APISide;
import io.github.puzzle.cosmic.util.annotation.Internal;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Item")
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
    void setModded(boolean m);

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
    IDataPointManifest getPointManifest();

    /**
     * set the point manifest of the item.
     * @param manifest The new point manifest to be set.
     */
    void setPointManifest(IDataPointManifest manifest);

    enum ItemModelType {
        ITEM_MODEL_2D,
        ITEM_MODEL_3D
    }

}
