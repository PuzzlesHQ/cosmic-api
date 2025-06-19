package io.github.puzzle.cosmic.api.world;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.EntityUniqueId;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Chunk;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.function.Consumer;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Zone")
public interface IZone {

    /**
     * Gets the chunk manager of the zone.
     * @return a {@link IChunkManager}
     */
    IChunkManager getChunkManager();

    /**
     * Gets the player manager of the zone.
     * @return a {@link IPlayerManager}
     */
    IPlayerManager getPlayerManager();

    /**
     * Gets the entity manager of the zone.
     * @return a {@link IEntityManager}
     */
    IEntityManager getEntityManager();

    interface IChunkManager {

        /**
         * Adds or replaces the chunk.
         * @param chunk  the chunk to add.
         * @param immediate {@code true} if the chunk should remeshing immediate.
         */
        void put(Chunk chunk, boolean immediate);

        /**
         * Adds a new chunk.
         * @param chunk the chunk to add.
         */
        void put(Chunk chunk);

        /**
         * Gets the chunk at the position.
         *
         * @param cx the chunks X.
         * @param cy the chunks Y.
         * @param cz the chunks Z.
         * @return a {@link Chunk}
         */
        Chunk get(int cx, int cy, int cz);

        /**
         * Gets the chunk at the block position.
         *
         * @param x the x block position.
         * @param y the Y block position.
         * @param z the Z block position.
         * @return a {@link Chunk}
         */
        Chunk getAtBlock(int x, int y, int z);

        /**
         * Gets the chunk at the block position.
         *
         * @param position the block position.
         * @return a {@link Chunk}
         */
        Chunk getAtVector(Vector3 position);

        /**
         * Removes the chunk.
         * @param chunk the chunk to remove.
         */
        void remove(Chunk chunk);

        /**
         * Gets the number of chunks in the zone.
         */
        int getNumberOfChunks();
    }

    interface IPlayerManager {
        /**
         * Adds the player to the zone.
         * @param player the player to add.
         */
        void addPlayer(Player player);

        /**
         * Removes the player.
         * @param player the player to remove.
         */
        void removePlayer(Player player);

        /**
         * Iterates over each player.
         * @param playerConsumer action to be performed.
         */
        void forEachPlayer(Consumer<Player> playerConsumer);

        /**
         * Gets all the players in the zone.
         * @return a {@link Array< Player >} of players.
         */
        Array<Player> getPlayers();
    }

    interface IEntityManager {

        /**
         * Adds the entity to the zone.
         * @param entity the entity to add.
         */
        void addEntity(Entity entity);

        /**
         * Gets the entity from its EntityUniqueId.
         *
         * @param id the EntityUniqueId of the entity.
         * @return a {@link Entity}
         */
        Entity getEntity(EntityUniqueId id);

        /**
         * Checks if the zone has the entity.
         * @param entity the entity to check.
         */
        boolean hasEntity(Entity entity);

        /**
         * Removes the entity.
         * @param id the EntityUniqueId of the entity to remove.
         */
        void removeEntity(EntityUniqueId id);

        /**
         * Removes the entity.
         * @param entity the entity to be removed.
         */
        void removeEntity(Entity entity);

        /**
         * Despawns the entity.
         * @param entity the entity to despawn.
         */
        void despawnEntity(Entity entity);

        /**
         * Iterates over each entity.
         * @param consumer  action to be performed.
         */
        void forEachEntity(Consumer<Entity> consumer);

        /**
         * Gets all the entity in the zone.
         * @return a {@link Array<Entity>} of entities.
         */
        Array<Entity> getAllEntities();
    }


    /**
     * Gets the zoneID as a string.
     */
    String getStringID();

    /**
     * Gets the zoneID.
     *
     * @return a {@link Identifier}
     */
    Identifier getId();

}
