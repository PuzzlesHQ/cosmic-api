package io.github.puzzle.cosmic.impl.mixin.item;

import com.badlogic.gdx.utils.JsonValue;
import com.llamalad7.mixinextras.sugar.Local;
import finalforeach.cosmicreach.items.ItemThing;
import io.github.puzzle.cosmic.api.item.IItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemThing.class)
public abstract class ItemThingMixin implements IItem {

    @Unique
    transient boolean cosmicAPI$isModded;

    @Override
    public boolean isModded() {
        return cosmicAPI$isModded;
    }

    @Override
    public void setModded(boolean m) {
        cosmicAPI$isModded = m;
    }

    @Inject(method = "loadItemFromJson", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/items/Item;registerItem(Lfinalforeach/cosmicreach/items/Item;)V", shift = At.Shift.BEFORE), remap = false)
    private static void cosmicAPI$loadItemFromJson(JsonValue loadJson, CallbackInfo ci, @Local ItemThing thing) {
        ((IItem) thing).setModded(false);
    }
}
