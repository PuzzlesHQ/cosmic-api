package io.github.puzzle.cosmic.impl.network.item;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import finalforeach.cosmicreach.networking.NetworkSide;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.util.APISide;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ItemUsePacket extends GamePacket {

    int slotId;
    BlockPosition targetPlaceBlockPos;
    BlockPosition targetBreakBlockPos;
    boolean usedLeftClick;

    public ItemUsePacket() {}

    public ItemUsePacket(int slotId, BlockPosition targetPlaceBlockPos, BlockPosition targetBreakBlockPos, boolean usedLeftClick) {
        this.slotId = slotId;
        this.targetPlaceBlockPos = targetPlaceBlockPos;
        this.targetBreakBlockPos = targetBreakBlockPos;
        this.usedLeftClick = usedLeftClick;
    }

    @Override
    public void receive(ByteBuf in) {
        this.slotId = this.readInt(in);
        this.targetPlaceBlockPos = this.readBlockPositionZoneless(in);
        this.targetBreakBlockPos = this.readBlockPositionZoneless(in);
        this.usedLeftClick = this.readBoolean(in);
    }

    @Override
    public void write() {
        this.writeInt(this.slotId);
        if (this.targetPlaceBlockPos != null) {
            this.writeBlockPosition(this.targetPlaceBlockPos);
        }
        if (this.targetBreakBlockPos != null) {
            this.writeBlockPosition(this.targetBreakBlockPos);
        }
        this.writeBoolean(this.usedLeftClick);
    }

    @Override
    public void handle(NetworkIdentity identity, ChannelHandlerContext channelHandlerContext) {
        if (identity.getSide() == NetworkSide.CLIENT) return;

        Player player = identity.getPlayer();
        Zone zone =identity.getZone();

        ItemSlot slot = player.inventory.getSlot(slotId);
        ItemStack stack = slot != null ? slot.getItemStack() : null;
        if (this.targetPlaceBlockPos != null) this.targetPlaceBlockPos.convertToLocal(zone);
        if (this.targetBreakBlockPos != null) this.targetBreakBlockPos.convertToLocal(zone);

        if (stack != null) {
            ((IItem)stack.getItem()).use(APISide.SERVER, slot, player, targetPlaceBlockPos, targetBreakBlockPos, usedLeftClick);
        }
    }
}
