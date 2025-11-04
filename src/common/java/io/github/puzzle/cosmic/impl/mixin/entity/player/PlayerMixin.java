package io.github.puzzle.cosmic.impl.mixin.entity.player;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.singletons.GameSingletons;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(Player.class)
public class PlayerMixin implements IPlayer {

    @Unique
    private final transient Player puzzleLoader$player = (Player)(Object)(this);

    @Override
    public Zone getZone() {
        return GameSingletons.world.getZoneCreateIfNull(puzzleLoader$player.zoneId);
    }

    @Override
    public boolean isLoading() {
        return puzzleLoader$player.loading;
    }

    @Override
    public BlockPosition getBlockPosition() {
        return BlockPosition.ofGlobal(
                puzzleLoader$player.getZone(),
                (int) puzzleLoader$player.getPosition().x,
                (int) puzzleLoader$player.getPosition().y,
                (int) puzzleLoader$player.getPosition().z
        );
    }


}
