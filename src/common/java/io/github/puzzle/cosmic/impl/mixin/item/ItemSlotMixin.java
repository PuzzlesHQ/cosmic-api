package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.ItemSlot;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(ItemSlot.class)
public class ItemSlotMixin implements IItemSlot {

    @Unique
    private final transient ItemSlot puzzleLoader$slot = (ItemSlot)(Object)this;

}
