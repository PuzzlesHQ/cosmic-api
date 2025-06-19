package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.rendering.IChunkMeshGroup;
import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Internal
@Mixin(Chunk.class)
public abstract class ChunkMixin implements IChunk {

    @Unique
    private final transient Chunk puzzleLoader$chunk = (Chunk)(Object)this;

    @Unique
    private final transient BlockPosition puzzleLoader$tmp = new BlockPosition(puzzleLoader$chunk, 0, 0, 0);

    @Inject(method = "setBlockState(Lfinalforeach/cosmicreach/blocks/BlockState;III)V", at = @At("TAIL"), remap = false)
    private void updateBlockEntities(BlockState blockState, int x, int y, int z, CallbackInfo ci) {
        ((IBlockPosition)puzzleLoader$tmp.set(puzzleLoader$chunk, x, y, z)).updateNeighbors(new BlockUpdateEvent());
    }

    @Inject(method = "setBlockState(Lfinalforeach/cosmicreach/savelib/blocks/IBlockState;III)V", at = @At("TAIL"), remap = false)
    private void updateBlockEntities(IBlockState blockState, int x, int y, int z, CallbackInfo ci) {
        ((IBlockPosition)puzzleLoader$tmp.set(puzzleLoader$chunk, x, y, z)).updateNeighbors(new BlockUpdateEvent());
    }

    @Unique
    private final transient IMeshingController puzzleLoader$meshingController = new IMeshingController() {

        @Override
        public void flagForRemeshing(boolean b) {
            puzzleLoader$chunk.flagForRemeshing(b);
        }

        @Override
        public void flagHorizontalTouchingChunksForRemeshing(Zone zone, boolean b) {
            puzzleLoader$chunk.flagHorizontalTouchingChunksForRemeshing(zone, b);
        }

        @Override
        public void flagTouchingChunksForRemeshing(Zone zone, boolean b) {
            puzzleLoader$chunk.flagTouchingChunksForRemeshing(zone, b);
        }

        @Override
        public void flagTouchingChunksForRemeshing(Zone zone, int i, int i1, int i2, boolean b) {
            puzzleLoader$chunk.flagTouchingChunksForRemeshing(zone, i, i1, i2, b);
        }

        @Override
        public void setMeshGroup(IChunkMeshGroup<?> iChunkMeshGroup) {
            puzzleLoader$chunk.setMeshGroup(iChunkMeshGroup);
        }

        @Override
        public IChunkMeshGroup<?> getMeshGroup() {
            return puzzleLoader$chunk.getMeshGroup();
        }
    };

    @Unique
    private final transient IBlockEntityController puzzleLoader$blockEntityController = new IBlockEntityController() {

        @Override
        public BlockEntity get(int i, int i1, int i2) {
            return puzzleLoader$chunk.getBlockEntity(i, i1, i2);
        }

        @Override
        public BlockEntity put(BlockState state, int i, int i1, int i2) {
            return puzzleLoader$chunk.setBlockEntity(state, i, i1, i2);
        }

        @Override
        public BlockEntity put(BlockState state, BlockEntity blockEntity, int i, int i1, int i2, boolean b) {
            puzzleLoader$chunk.setBlockEntityDirect(state, blockEntity, i, i1, i2, b);
            return blockEntity;
        }

        @Override
        public void foreach(Consumer<BlockEntity> consumer) {
            puzzleLoader$chunk.forEachBlockEntity(consumer);
        }

        @Override
        public int getCount() {
            return puzzleLoader$chunk.getNumberOfBlockEntities();
        }

        @Override
        public boolean isEmpty() {
            return puzzleLoader$chunk.hasBlockEntities();
        }
    };


    @Override
    public IMeshingController getMeshingController() {
        return puzzleLoader$meshingController;
    }

    @Override
    public IBlockEntityController getBlockEntityController() {
        return puzzleLoader$blockEntityController;
    }
}
