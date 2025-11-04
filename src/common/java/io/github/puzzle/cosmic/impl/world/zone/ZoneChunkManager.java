package io.github.puzzle.cosmic.impl.world.zone;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IZone;

public class ZoneChunkManager implements IZone.IChunkManager {

    private transient final Zone puzzleLoader$zone;

    public ZoneChunkManager(Zone zone) {
        this.puzzleLoader$zone = zone;
    }

    @Override
    public void put(Chunk chunk, boolean b) {
        puzzleLoader$zone.addOrReplaceChunk(chunk, b);
    }

    @Override
    public void put(Chunk chunk)
    {
        puzzleLoader$zone.addChunk(chunk);
    }

    @Override
    public Chunk get(int i, int i1, int i2) {
        return puzzleLoader$zone.getChunkAtChunkCoords(i, i1, i2);
    }

    @Override
    public Chunk getAtBlock(int i, int i1, int i2) {
        return puzzleLoader$zone.getChunkAtBlock(i, i1, i2);
    }

    @Override
    public Chunk getAtVector(Vector3 vector3) {
        return puzzleLoader$zone.getChunkAtPosition(vector3);
    }

    @Override
    public void remove(Chunk chunk) {
        puzzleLoader$zone.removeChunk(chunk);
    }

    @Override
    public int getNumberOfChunks() {
        return puzzleLoader$zone.getNumberOfChunks();
    }

}
