package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.impl.client.item.CosmicItemModel;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(Item.class)
public interface ItemMixin extends IItem {

    @Shadow String getID();

    @Inject(method = "registerItem", at = @At("TAIL"))
    private static void register(Item item, CallbackInfo ci) {
        if (!CosmicItemModel.hasItemModel(item))
            CosmicItemModel.registerItemModel(item);
    }

    @Override
    default Identifier getIdentifier() {
        return Identifier.of(this.getID());
    }
}
