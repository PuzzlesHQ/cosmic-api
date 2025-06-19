package io.github.puzzle.cosmic.api.item.container;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.util.annotation.Note;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.List;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */

@ApiGen("SlotContainer")
@Note("This interface only applies to SlotContainer and not ISlotContainer internally.")
public interface PuzzleSlotContainer {

    /**
     * Drops all the items in the slotContainer.
     * @param zone the zone the item will be drop in.
     * @param position the position where the items will be dropped.
     */
    void dropAllItems(Zone zone, Vector3 position);

    /**
     * Gets the first full itemSlot.
     *
     * @return a {@link ItemSlot}
     */
    ItemSlot getFirstFullItemSlot();

    /**
     * Gets all the itemSlots in the slotContainer as a list.
     *
     * @return a list of {@link ItemSlot}
     */
    List<ItemSlot> getSlots();

    /**
     * Gets all the input itemSlots in the slotContainer as a list.
     *
     * @return a list of {@link ItemSlot}
     */
    List<ItemSlot> getInputSlots();

    /**
     * Gets all the output itemSlots in the slotContainer as a list.
     *
     * @return a list of {@link ItemSlot}
     */
    List<ItemSlot> getOutputSlots();

    /**
     * Clears all itemSlots by removing their itemStack.
     */
    void clear();

}
