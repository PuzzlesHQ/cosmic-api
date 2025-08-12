package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.world.World;
import finalforeach.cosmicreach.world.Zone;
import finalforeach.cosmicreach.worldgen.ZoneGenerator;
import io.github.puzzle.cosmic.api.world.IWorld;
import io.github.puzzle.cosmic.impl.world.WorldZoneMap;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;
import java.util.Set;

@Internal
@Mixin(World.class)
public class WorldMixin implements IWorld {

    @Unique
    private final transient World puzzleLoader$world = (World)(Object)this;

    @Unique
    private final transient IZoneMap puzzleLoader$zoneMap = new WorldZoneMap(puzzleLoader$world);

    @Override
    public IZoneMap getZoneMap() {
        return puzzleLoader$zoneMap;
    }

}
