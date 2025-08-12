package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import finalforeach.cosmicreach.world.Chunk;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.impl.event.BlockUpdateEvent;
import io.github.puzzle.cosmic.impl.world.chunk.ChunkBlockEntityController;
import io.github.puzzle.cosmic.impl.world.chunk.ChunkMeshingController;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
    private final transient IMeshingController puzzleLoader$meshingController = new ChunkMeshingController(puzzleLoader$chunk);

    @Unique
    private final transient IBlockEntityController puzzleLoader$blockEntityController = new ChunkBlockEntityController(puzzleLoader$chunk);


}
