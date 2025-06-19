package io.github.puzzle.cosmic.api.entity.player;

import finalforeach.cosmicreach.entities.player.PlayerEntity;
import finalforeach.cosmicreach.entities.player.skins.GameTexturePlayerSkin;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author CrabK1ng
 * @since 0.4.1
 */
@ApiGen("PlayerEntity")
public interface IPlayerEntity {

    /**
     * Gets the playerSkin of the playerEntity.
     */
    GameTexturePlayerSkin getPlayerSkin();

    /**
     * Sets the playerSkin of the playerEntity.
     * @param playerSkin the playerSkin to set.
     * @see PlayerEntity#updateSkin() use updateSkin to update the skin after setting.
     */
    void setPlayerSkin(GameTexturePlayerSkin playerSkin);



}
