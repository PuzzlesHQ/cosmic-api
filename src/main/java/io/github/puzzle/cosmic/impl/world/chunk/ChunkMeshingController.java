package io.github.puzzle.cosmic.impl.world.chunk;

import finalforeach.cosmicreach.rendering.IChunkMeshGroup;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IChunk;

public class ChunkMeshingController implements IChunk.IMeshingController {

    Chunk puzzleLoader$chunk;

    public ChunkMeshingController(Chunk chunk) {
        this.puzzleLoader$chunk = chunk;
    }

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

}
