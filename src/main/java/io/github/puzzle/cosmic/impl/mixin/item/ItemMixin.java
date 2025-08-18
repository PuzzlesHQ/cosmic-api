package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Internal
@Mixin(Item.class)
public interface ItemMixin extends IItem {

    @Shadow String getID();

    @Override
    default Identifier getIdentifier() {
        return Identifier.of(this.getID());
    }
}
