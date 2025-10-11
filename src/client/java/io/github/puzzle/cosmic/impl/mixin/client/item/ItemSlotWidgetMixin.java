package io.github.puzzle.cosmic.impl.mixin.client.item;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.llamalad7.mixinextras.sugar.Local;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.rendering.items.ItemModel;
import finalforeach.cosmicreach.rendering.items.ItemRenderer;
import finalforeach.cosmicreach.ui.widgets.ItemStackWidget;
import io.github.puzzle.cosmic.impl.client.item.CosmicItemModelWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStackWidget.class)
public class ItemSlotWidgetMixin {

    @Shadow(remap = false)
    transient ItemStack itemStack;
    @Unique
    private static final Matrix4 cosmicAPI$identMat4 = new Matrix4();

    @Inject(remap = false, method = "drawItem",
            at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/rendering/items/ItemRenderer;drawItem(Lcom/badlogic/gdx/graphics/Camera;Lfinalforeach/cosmicreach/items/Item;Lfinalforeach/cosmicreach/items/ItemStack;)V"
                    , shift = At.Shift.BEFORE),
            cancellable = true)
    private void drawItem(Viewport itemViewport, CallbackInfo ci, @Local Camera itemCam) {
        ItemModel model = ItemRenderer.getModel(itemStack.getItem(), true);
        if (model instanceof CosmicItemModelWrapper itemModel) {
            itemModel.renderInSlot(null, itemStack, itemCam, cosmicAPI$identMat4, false);
            ci.cancel();
            return;
        }
        model.render(null, itemCam, cosmicAPI$identMat4, false, false, itemStack);
    }

}
