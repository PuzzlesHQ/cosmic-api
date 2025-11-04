package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.ItemBlock;
import io.github.puzzle.cosmic.api.item.IItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemBlock.class)
public abstract class BlockItemMixin implements IItem {

    @Override
    public boolean isModded() {
        return false;
    }

    @Override
    public void setModded(boolean m) {
        throw new RuntimeException("Nuh uh");
    }
}
