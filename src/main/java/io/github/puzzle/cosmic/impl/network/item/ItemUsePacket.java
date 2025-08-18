package io.github.puzzle.cosmic.impl.network.item;

import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.networking.GamePacket;
import finalforeach.cosmicreach.networking.NetworkIdentity;
import finalforeach.cosmicreach.networking.NetworkSide;
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
        int lossIdx = this.readByte(in);
        System.out.println(lossIdx);
        if ((lossIdx & 1) == 0) this.targetPlaceBlockPos = this.readBlockPosition(in, GameSingletons.world.getZoneIfExists(this.readString(in)));
        if ((lossIdx & 2) == 0) this.targetBreakBlockPos = this.readBlockPosition(in, GameSingletons.world.getZoneIfExists(this.readString(in)));
        this.usedLeftClick = this.readBoolean(in);
    }

    @Override
    public void write() {
        this.writeInt(this.slotId);
        int lidx = 0;
        if (this.targetPlaceBlockPos == null) lidx |= 1;
        if (this.targetBreakBlockPos == null) lidx |= 2;
        this.writeByte(lidx);
        if (this.targetPlaceBlockPos != null) {
            this.writeString(targetPlaceBlockPos.getZone().zoneId);
            this.writeBlockPosition(this.targetPlaceBlockPos);
        }
        if (this.targetBreakBlockPos != null) {
            this.writeString(targetBreakBlockPos.getZone().zoneId);
            this.writeBlockPosition(this.targetBreakBlockPos);
        }
        this.writeBoolean(this.usedLeftClick);
    }

    @Override
    public void handle(NetworkIdentity identity, ChannelHandlerContext channelHandlerContext) {
        if (identity.getSide() == NetworkSide.CLIENT) return;

        Player player = identity.getPlayer();

        ItemSlot slot = player.inventory.getSlot(slotId);
        ItemStack stack = slot != null ? slot.getItemStack() : null;

        System.out.println(slotId + " " + slot);
        if (stack != null) {
            stack.getItem().use(APISide.SERVER, slot, player, targetPlaceBlockPos, targetBreakBlockPos, usedLeftClick);
        }
    }
}
