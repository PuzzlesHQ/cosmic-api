package io.github.puzzle.cosmic.impl.mixin.item.container;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.item.container.PuzzleSlotContainer;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Internal
@Mixin(SlotContainer.class)
public abstract class SlotContainerMixin implements PuzzleSlotContainer {

    @Shadow public abstract void dropAllItems(Zone zone, float x, float y, float z);

    @Shadow public abstract void forEachSlot(Consumer<ItemSlot> slotConsumer);

    @Unique
    SlotContainer puzzleLoader$container = (SlotContainer)(Object)this;

    @Override
    public void dropAllItems(Zone zone, Vector3 position) {
        this.dropAllItems(zone,position.x,position.y,position.z);
    }

    @Override
    public ItemSlot getFirstFullItemSlot() {
        AtomicReference<ItemSlot> fullItemSlot = new AtomicReference<>(null);
        this.forEachSlot(itemSlot -> {
            if (!itemSlot.isEmpty()) {
                fullItemSlot.set(itemSlot);
            }
        });

        return fullItemSlot.get();
    }

    @Override
    public List<ItemSlot> getSlots() {
        List<ItemSlot> itemSlotList = new ArrayList<>();
        puzzleLoader$container.forEachSlot(itemSlotList::add);
        return itemSlotList;
    }

    @Override
    public List<ItemSlot> getInputSlots() {
        List<ItemSlot> itemSlotList = new ArrayList<>();
        puzzleLoader$container.forEachSlot(itemSlot -> {
            if (!itemSlot.isOutputOnly()) itemSlotList.add(itemSlot);
        });
        return itemSlotList;
    }

    @Override
    public List<ItemSlot> getOutputSlots() {
        List<ItemSlot> itemSlotList = new ArrayList<>();
        puzzleLoader$container.forEachSlot(itemSlot -> {
            if (itemSlot.isOutputOnly()) itemSlotList.add(itemSlot);
        });
        return itemSlotList;
    }

    @Override
    public void clear() {
        this.forEachSlot((p) -> p.setItemStack(null));
    }


}
