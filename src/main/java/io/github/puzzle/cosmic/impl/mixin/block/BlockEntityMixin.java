package io.github.puzzle.cosmic.impl.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.constants.Direction;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements IBlockEntity {

    @Shadow public abstract Zone getZone();

    @Shadow public abstract int getGlobalX();

    @Shadow public abstract int getGlobalY();

    @Shadow public abstract int getGlobalZ();

    @Shadow public int x;
    @Shadow public int y;
    @Shadow public int z;

    @Unique
    private final transient BlockEntity puzzleLoader$entity = (BlockEntity)(Object)this;

    @Unique
    private transient IDataPointManifest puzzleLoader$manifest = new DataPointManifest();

    int lx, ly, lz;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo info) {
        int cx = Math.floorDiv(x, 16);
        int cy = Math.floorDiv(y, 16);
        int cz = Math.floorDiv(z, 16);

        lx = x - (16 * cx);
        ly = y - (16 * cy);
        lz = z - (16 * cz);
    }

    @Inject(method = "setGlobalPosition", at = @At("TAIL"))
    private void setGlobalPosition(int x, int y, int z, CallbackInfo info) {
        int cx = Math.floorDiv(x, 16);
        int cy = Math.floorDiv(y, 16);
        int cz = Math.floorDiv(z, 16);

        lx = x - (16 * cx);
        ly = y - (16 * cy);
        lz = z - (16 * cz);
    }

    @Inject(method = "read", at = @At("TAIL"))
    private void read(CallbackInfo info) {
        int cx = Math.floorDiv(x, 16);
        int cy = Math.floorDiv(y, 16);
        int cz = Math.floorDiv(z, 16);

        lx = x - (16 * cx);
        ly = y - (16 * cy);
        lz = z - (16 * cz);
    }

    @Override
    public BlockPosition getBlockPosition() {
        return new BlockPosition(getChunk(), getLocalX(), getLocalY(), getLocalZ());
    }

    @Override
    public Chunk getChunk() {
        return puzzleLoader$entity.getZone().getChunkAtBlock(
                getGlobalX(),
                getGlobalY(),
                getGlobalZ()
        );
    }

    @Override
    public void onNeighborUpdate(BlockUpdateEvent iBlockUpdateEvent) {
        // Implemented to prevent crash, can be overridden.
    }

    @Override
    public void updateNeighbors(BlockUpdateEvent event) {
        getBlockPosition().updateNeighbors(event);
    }

    @Override
    public void updateNeighborInDirection(Direction direction, BlockUpdateEvent event) {
        getBlockPosition().updateNeighborInDirection(event, direction);
    }


    @Inject(method = "read", at = @At("TAIL"), remap = false)
    private void write(CRBinDeserializer crbd, CallbackInfo ci) {
        IDataPointManifest manifest = crbd.readObj("point_manifest", DataPointManifest.class);
        if (manifest != null) setPointManifest(manifest);
    }

    @Inject(method = "write", at = @At("TAIL"), remap = false)
    private void write(CRBinSerializer crbs, CallbackInfo ci) {
        crbs.writeObj("point_manifest", puzzleLoader$manifest);
    }

    @Override
    public IDataPointManifest getPointManifest() {
        return puzzleLoader$manifest;
    }

    @Override
    public void setPointManifest(IDataPointManifest iDataPointManifest) {
        puzzleLoader$manifest = iDataPointManifest;
    }

    @Override
    public int getLocalX() {
        return lx;
    }

    @Override
    public int getLocalY() {
        return ly;
    }

    @Override
    public int getLocalZ() {
        return lz;
    }
}
