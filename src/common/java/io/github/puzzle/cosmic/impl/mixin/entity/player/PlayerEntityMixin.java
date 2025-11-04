package io.github.puzzle.cosmic.impl.mixin.entity.player;

import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.entities.player.PlayerEntity;
import finalforeach.cosmicreach.entities.player.skins.GameTexturePlayerSkin;
import io.github.puzzle.cosmic.api.entity.player.IPlayerEntity;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Internal
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IPlayerEntity {

    @Shadow(remap = false) public abstract Player getPlayer();

    @Shadow(remap = false)
    transient GameTexturePlayerSkin playerSkin;

    @Override
    public GameTexturePlayerSkin getPlayerSkin() {
        return this.playerSkin;
    }

    @Override
    public void setPlayerSkin(GameTexturePlayerSkin PlayerSkin) {
        this.playerSkin = PlayerSkin;
    }



}
