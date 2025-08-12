package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.impl.world.zone.ZoneChunkManager;
import io.github.puzzle.cosmic.impl.world.zone.ZoneEntityManager;
import io.github.puzzle.cosmic.impl.world.zone.ZonePlayerManager;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

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
    private final transient IChunkManager puzzleLoader$chunkManager = new ZoneChunkManager(puzzleLoader$zone);

    @Unique
    private final transient IEntityManager puzzleLoader$entityManager = new ZoneEntityManager(puzzleLoader$zone);
    @Unique
    private final transient IPlayerManager puzzleLoader$playerManager = new ZonePlayerManager(puzzleLoader$zone);

    @Override
    public String getStringID() {
        return puzzleLoader$zone.zoneId;
    }

    @Override
    public Identifier getId() {
        return Identifier.of(puzzleLoader$zone.zoneId);
    }
}
