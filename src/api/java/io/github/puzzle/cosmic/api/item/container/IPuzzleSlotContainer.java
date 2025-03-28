package io.github.puzzle.cosmic.api.item.container;

import com.badlogic.gdx.math.Vector3;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.Note;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */

@ApiGen("SlotContainer")
@Note("This interface only applies to SlotContainer and not ISlotContainer internally.")
public interface IPuzzleSlotContainer {

    /**
     * Gets the slotContainer use if you have a ISlotContainer.
     * @return a {@link IPuzzleSlotContainer}
     */
    IPuzzleSlotContainer pGetBackingContainer();

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same item.
     * @param itemStack the itemStack to add.
     */
    boolean pAddItemStack(IPuzzleItemStack itemStack);

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same swapGroup.
     * @param itemStack the itemStack to add.
     */
    boolean pAddItemStackWithSwapGroup(IPuzzleItemStack itemStack);

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same swapGroup.
     * @param itemSlot the itemSlot to add or merge from.
     */
    boolean pAddOrMergeFrom(IPuzzleItemSlot itemSlot);

    /**
     * Adds a itemStack to this slotContainer if there is an empty itemSlot or an itemStack of the same swapGroup, and it matches the slotPredicate.
     * @param itemSlot the itemSlot to add or merge from.
     * @param itemSlotPredicate the slotPredicate to test.
     */
    boolean pAddOrMergeFrom(IPuzzleItemSlot itemSlot, Predicate<IPuzzleItemSlot> itemSlotPredicate);

    /**
     * Drops all the items in the slotContainer.
     * @param zone the zone the item will be drop in.
     * @param x the X pos where the items will be dropped.
     * @param y the Y pos where the items will be dropped.
     * @param z the Z pos where the items will be dropped.
     */
    void pDropAllItems(IPuzzleZone zone, float x, float y, float z);

    /**
     * Drops all the items in the slotContainer.
     * @param zone the zone the item will be drop in.
     * @param position the position where the items will be dropped.
     */
    default void pDropAllItems(IPuzzleZone zone, Vector3 position) {
        pDropAllItems(zone, position.x, position.y, position.z);
    }

    /**
     * Iterates over each item slot.
     * @param itemSlotConsumer action to be performed.
     */
    void pForEachSlot(Consumer<IPuzzleItemSlot> itemSlotConsumer);

    /**
     * Gets the first empty itemSlot.
     * @return a {@link IPuzzleItemSlot}
     */
    IPuzzleItemSlot pGetFirstEmptyItemSlot();

    /**
     * Gets the first full itemSlot.
     * @return a {@link IPuzzleItemSlot}
     */
    IPuzzleItemSlot pGetFirstFullItemSlot();

    /**
     * Gets the number of itemSlot in the slotContainer.
     */
    int pGetSlotCount();

    /**
     * Gets the itemSlot at the specified index.
     * @param index the index of the itemSlot.
     * @return a {@link IPuzzleItemSlot}
     */
    IPuzzleItemSlot pGetSlot(int index);

    /**
     * does nothing at the moment. UPDATE THIS WHEN YOU ADD IT TO THE BACKEND!!!!!
     * @return a {@link IPuzzleItemSlot}
     */
    IPuzzleItemSlot pGetSlots(int index);

    /**
     * Checks if the slotContainer is empty.
     */
    boolean pIsEmpty();

    /**
     * Clears all itemSlots by removing their itemStack.
     */
    default void pClear() {
        pForEachSlot((p) -> {
            p.pSetItemStack(null);
        });
    }

}
