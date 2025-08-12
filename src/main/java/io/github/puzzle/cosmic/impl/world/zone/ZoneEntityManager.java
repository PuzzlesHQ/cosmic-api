package io.github.puzzle.cosmic.impl.world.zone;

import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.EntityUniqueId;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IZone;

import java.util.function.Consumer;

public class ZoneEntityManager implements IZone.IEntityManager {

    Zone puzzleLoader$zone;

    public ZoneEntityManager(Zone zone) {
        this.puzzleLoader$zone = zone;
    }

    @Override
    public void addEntity(Entity entity) {
        puzzleLoader$zone.addEntity(entity);
    }

    @Override
    public Entity getEntity(EntityUniqueId entityUniqueId) {
        return puzzleLoader$zone.getEntity(entityUniqueId);
    }

    @Override
    public boolean hasEntity(Entity entity) {
        return puzzleLoader$zone.hasEntity(entity);
    }

    @Override
    public void removeEntity(EntityUniqueId entityUniqueId) {
        puzzleLoader$zone.removeEntity(entityUniqueId);
    }

    @Override
    public void removeEntity(Entity entity) {
        puzzleLoader$zone.removeEntity(entity);
    }

    @Override
    public void despawnEntity(Entity entity) {
        puzzleLoader$zone.despawnEntity(entity);
    }

    @Override
    public void forEachEntity(Consumer<Entity> consumer) {
        puzzleLoader$zone.forEachEntity(consumer);
    }

    @Override
    public Array<Entity> getAllEntities() {
        return puzzleLoader$zone.getAllEntities();
    }

}
