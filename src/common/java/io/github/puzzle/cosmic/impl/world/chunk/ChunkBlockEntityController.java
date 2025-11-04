package io.github.puzzle.cosmic.impl.world.chunk;

import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.blocks.blockentities.BlockEntity;
import finalforeach.cosmicreach.world.Chunk;
import io.github.puzzle.cosmic.api.world.IChunk;

import java.util.function.Consumer;

public class ChunkBlockEntityController implements IChunk.IBlockEntityController {

    private transient final Chunk puzzleLoader$chunk;

    public ChunkBlockEntityController(Chunk chunk) {
        this.puzzleLoader$chunk = chunk;
    }

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

}
