package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.world.World;
import finalforeach.cosmicreach.world.Zone;
import finalforeach.cosmicreach.worldgen.ZoneGenerator;
import io.github.puzzle.cosmic.api.world.IWorld;
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
    private final transient IZoneMap puzzleLoader$zoneMap = new IZoneMap() {
        @Override
        public Zone get(String s) {
            return puzzleLoader$world.getZoneIfExists(s);
        }

        @Override
        public Zone getOrCreate(String s) {
            return puzzleLoader$world.getZoneCreateIfNull(s);
        }

        @Override
        public void put(Zone zone) {
            puzzleLoader$world.addZone(zone);
        }

        @Override
        public void put(String s, ZoneGenerator zoneGenerator) {
            puzzleLoader$world.addNewZone(s, zoneGenerator);
        }

        @Override
        public Set<String> getKeys() {
            return puzzleLoader$world.getZoneIds();
        }

        @Override
        public Collection<Zone> getValues() {
            return puzzleLoader$world.getZones();
        }
    };

    @Override
    public IZoneMap getZoneMap() {
        return puzzleLoader$zoneMap;
    }

}
