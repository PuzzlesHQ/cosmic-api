package io.github.puzzle.cosmic.impl.mixin.entity;

import finalforeach.cosmicreach.entities.ItemGameEntity;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.item.ITickingItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemGameEntity.class)
public class ItemEntityMixin {

    @Shadow(remap = false)
    ItemStack itemStack;

    @Inject(method = "update", at = @At("HEAD"), remap = false)
    private void update(Zone zone, float deltaTime, CallbackInfo ci) {
        if (itemStack.getItem() instanceof ITickingItem tickingItem) {
            tickingItem.tickEntity(zone, deltaTime, (IEntity) this, itemStack);
        }
    }

}
