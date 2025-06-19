package io.github.puzzle.cosmic.impl.mixin.world;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.EntityUniqueId;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Consumer;

@Internal
@Mixin(Zone.class)
public class ZoneMixin implements IZone {

    @Unique
    private final transient Zone puzzleLoader$zone = (Zone)(Object)this;

    @Override
    public IChunkManager getChunkManager() {
        return puzzleLoader$chunkManager;
    }

    @Override
    public IEntityManager getEntityManager() {
        return puzzleLoader$entityManager;
    }

    @Override
    public IPlayerManager getPlayerManager() {
        return puzzleLoader$playerManager;
    }

    @Unique
    private final transient IChunkManager puzzleLoader$chunkManager = new IChunkManager() {

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
    };

    @Unique
    private final transient IEntityManager puzzleLoader$entityManager = new IEntityManager() {

        @Override
        public void addEntity(Entity entity) {
            puzzleLoader$zone.addEntity(entity);
        }

        @Override
        public Entity getEntity(EntityUniqueId entityUniqueId) {
            return puzzleLoader$zone.getEntity(entityUniqueId);
        }

        @Override
        public boolean hasEntity(Entity entity) {
            return puzzleLoader$zone.hasEntity(entity);
        }

        @Override
        public void removeEntity(EntityUniqueId entityUniqueId) {
            puzzleLoader$zone.removeEntity(entityUniqueId);
        }

        @Override
        public void removeEntity(Entity entity) {
            puzzleLoader$zone.removeEntity(entity);
        }

        @Override
        public void despawnEntity(Entity entity) {
            puzzleLoader$zone.despawnEntity(entity);
        }

        @Override
        public void forEachEntity(Consumer<Entity> consumer) {
            puzzleLoader$zone.forEachEntity(consumer);
        }

        @Override
        public Array<Entity> getAllEntities() {
            return puzzleLoader$zone.getAllEntities();
        }
    };

    @Unique
    private final transient IPlayerManager puzzleLoader$playerManager = new IPlayerManager() {

        @Override
        public void addPlayer(Player player) {
            puzzleLoader$zone.addPlayer(player);
        }

        @Override
        public void removePlayer(Player player) {
            puzzleLoader$zone.removePlayer(player);
        }

        @Override
        public void forEachPlayer(Consumer<Player> consumer) {
            puzzleLoader$zone.forEachPlayer(consumer);
        }

        @Override
        public Array<Player> getPlayers() {
            return puzzleLoader$zone.getPlayers();
        }
    };

    @Override
    public String getStringID() {
        return puzzleLoader$zone.zoneId;
    }

    @Override
    public Identifier getId() {
        return Identifier.of(puzzleLoader$zone.zoneId);
    }
}
