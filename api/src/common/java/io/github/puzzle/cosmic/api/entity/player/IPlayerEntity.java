package io.github.puzzle.cosmic.api.entity.player;

import finalforeach.cosmicreach.entities.player.PlayerEntity;
import finalforeach.cosmicreach.entities.player.skins.GameTexturePlayerSkin;

/**
 *
 * @author CrabK1ng
 * @since 0.4.1
 */
public interface IPlayerEntity {

    /**
     * Gets the playerSkin of the playerEntity.
     */
    default GameTexturePlayerSkin getPlayerSkin() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Sets the playerSkin of the playerEntity.
     * @param playerSkin the playerSkin to set.
     * @see PlayerEntity#updateSkin() use updateSkin to update the skin after setting.
     */
    default void setPlayerSkin(GameTexturePlayerSkin playerSkin) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }



}
