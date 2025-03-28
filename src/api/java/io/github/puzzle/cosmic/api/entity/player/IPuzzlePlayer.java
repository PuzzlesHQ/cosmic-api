package io.github.puzzle.cosmic.api.entity.player;

import com.badlogic.gdx.math.Vector3;
import io.github.puzzle.cosmic.api.account.IPuzzleAccount;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.entity.IPuzzleEntity;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.api.world.IPuzzleChunk;
import io.github.puzzle.cosmic.api.world.IPuzzleWorld;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Player")
public interface IPuzzlePlayer {

    /**
     * Gets the entity of the player.
     * @return a {@link IPuzzleEntity}
     */
    IPuzzleEntity pGetEntity();

    /**
     * Gets the playerEntity of the Player.
     * @return a {@link IPuzzlePlayerEntity}
     */
    IPuzzlePlayerEntity pGetPlayerEntity();

    /**
     * Gets the view offset of the player.
     */
    Vector3 pGetViewOffset();

    /**
     * Checks if the player should be prone.
     * @param zone the zone of the player.
     */
    void pProneCheck(IPuzzleZone zone);

    /**
     * Checks if the player should be crouched.
     * @param zone the zone of the player.
     */
    void pCrouchCheck(IPuzzleZone zone);

    /**
     * Respawns the player.
     * @param world the world the player is in.
     */
    void pRespawn(IPuzzleWorld world);

    /**
     * Respawns the player.
     * @param zone the zone the player is in.
     */
    void pRespawn(IPuzzleZone zone);

    /**
     * Sets the player position.
     * @param x the new x.
     * @param y the new Y.
     * @param z the new Z.
     */
    void pSetPosition(float x, float y, float z);

    /**
     * Gets the zone the player is in.
     * @return a {@link IPuzzleZone}
     */
    IPuzzleZone pGetZone();

    /**
     * Gets the chunk the player is in.
     * @param world the world the player is in.
     * @return a {@link IPuzzleChunk}
     */
    IPuzzleChunk pGetChunk(IPuzzleWorld world);

    /**
     * Gets the light of the block at the player position.
     * @param world the world the player is in.
     */
    short pGetBlockLight(IPuzzleWorld world);

    /**
     * Gets the skylight of the block at the player position.
     * @param world the world the player is in.
     */
    int pGetSkyLight(IPuzzleWorld world);

    /**
     * Drops an item from the player.
     * @param world the world the player is in.
     * @param itemStack the item to be dropped.
     */
    void pSpawnDroppedItem(IPuzzleWorld world, IPuzzleItemStack itemStack);

    /**
     * Gets the player position as a blockPosition.
     * @return a {@link IPuzzleBlockPosition}
     */
    IPuzzleBlockPosition pGetBlockPosition();

    /**
     * Gets the player position.
     */
    Vector3 pGetPosition();

    /**
     * Checks if the player is loading.
     */
    boolean pIsLoading();

    /**
     * Sets the players zone.
     * @param zone the zone to set.
     */
    default void pSetZone(IPuzzleZone zone) {
        pSetZone(zone.pGetId());
    }

    /**
     * Sets the players zone.
     * @param zoneId the zoneId of a zone to set.
     */
    default void pSetZone(IPuzzleIdentifier zoneId) {
        pSetZone(zoneId.asString());
    }

    /**
     * Sets the players zone.
     * @param zoneId the zoneId of a zone to set.
     */
    void pSetZone(String zoneId);

    /**
     * Checks if the player is dead.
     */
    boolean pIsDead();

    /**
     * Gets the account of the player.
     * @return a {@link IPuzzleAccount}
     */
    IPuzzleAccount pGetAccount();

    /**
     * Gets the username of the player.
     */
    String pGetUsername();

}
