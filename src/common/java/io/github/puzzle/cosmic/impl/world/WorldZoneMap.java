package io.github.puzzle.cosmic.impl.world;

import finalforeach.cosmicreach.world.World;
import finalforeach.cosmicreach.world.Zone;
import finalforeach.cosmicreach.worldgen.ZoneGenerator;
import io.github.puzzle.cosmic.api.world.IWorld;

import java.util.Collection;
import java.util.Set;

public class WorldZoneMap implements IWorld.IZoneMap {

    World puzzleLoader$world;

    public WorldZoneMap(World world) {
        this.puzzleLoader$world = world;
    }

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
}
