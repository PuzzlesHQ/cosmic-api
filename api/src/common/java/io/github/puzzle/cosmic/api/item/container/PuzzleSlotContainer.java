package io.github.puzzle.cosmic.api.item.container;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.util.annotation.Note;

import java.util.List;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */

@Note("This interface only applies to SlotContainer and not ISlotContainer internally.")
public interface PuzzleSlotContainer {

    /**
     * Drops all the items in the slotContainer.
     * @param zone the zone the item will be drop in.
     * @param position the position where the items will be dropped.
     */
    default void dropAllItems(Zone zone, Vector3 position) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Gets the first full itemSlot.
     *
     * @return a {@link ItemSlot}
     */
    default ItemSlot getFirstFullItemSlot() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Gets all the itemSlots in the slotContainer as a list.
     *
     * @return a list of {@link ItemSlot}
     */
    default List<ItemSlot> getSlots() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Gets all the input itemSlots in the slotContainer as a list.
     *
     * @return a list of {@link ItemSlot}
     */
    default List<ItemSlot> getInputSlots() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Gets all the output itemSlots in the slotContainer as a list.
     *
     * @return a list of {@link ItemSlot}
     */
    default List<ItemSlot> getOutputSlots() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Clears all itemSlots by removing their itemStack.
     */
    default void clear() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

}
