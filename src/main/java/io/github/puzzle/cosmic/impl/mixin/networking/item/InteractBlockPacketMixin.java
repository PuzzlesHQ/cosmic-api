package io.github.puzzle.cosmic.impl.mixin.networking.item;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.networking.packets.blocks.InteractBlockPacket;
import io.github.puzzle.cosmic.api.item.IItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InteractBlockPacket.class)
public class InteractBlockPacketMixin {

    @Redirect(remap = false, method = "handle", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/items/ItemStack;useItem(Lfinalforeach/cosmicreach/items/ItemSlot;Lfinalforeach/cosmicreach/entities/player/Player;Lfinalforeach/cosmicreach/blocks/BlockPosition;)Z"))
    private boolean useItem(ItemStack instance, ItemSlot itemSlot, Player player, BlockPosition targetBlockPos) {
//        IItem.as(instance.getItem())._use(APISide.SERVER, itemSlot, player, null, targetBlockPos, false);
        if (!((IItem) instance.getItem()).isModded())
            return instance.getItem().useItem(itemSlot, player, targetBlockPos);
        return false;
    }

}
