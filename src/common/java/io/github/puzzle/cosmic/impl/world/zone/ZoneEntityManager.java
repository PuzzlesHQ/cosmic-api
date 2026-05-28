package io.github.puzzle.cosmic.impl.world.zone;

import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.entities.GameEntity;
import finalforeach.cosmicreach.entities.GameEntityUniqueId;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.world.IZone;

import java.util.function.Consumer;

public class ZoneEntityManager implements IZone.IEntityManager {

    private transient final Zone puzzleLoader$zone;

    public ZoneEntityManager(Zone zone) {
        this.puzzleLoader$zone = zone;
    }

    @Override
    public void addEntity(GameEntity entity) {
        puzzleLoader$zone.addEntity(entity);
    }

    @Override
    public GameEntity getEntity(GameEntityUniqueId entityUniqueId) {
        return puzzleLoader$zone.getEntity(entityUniqueId);
    }

    @Override
    public boolean hasEntity(GameEntity entity) {
        return puzzleLoader$zone.hasEntity(entity);
    }

    @Override
    public void removeEntity(GameEntityUniqueId entityUniqueId) {
        puzzleLoader$zone.removeEntity(entityUniqueId);
    }

    @Override
    public void removeEntity(GameEntity entity) {
        puzzleLoader$zone.removeEntity(entity);
    }

    @Override
    public void despawnEntity(GameEntity entity) {
        puzzleLoader$zone.despawnEntity(entity);
    }

    @Override
    public void forEachEntity(Consumer<GameEntity> consumer) {
        puzzleLoader$zone.forEachEntity(consumer);
    }

    @Override
    public Array<GameEntity> getAllEntities() {
        return puzzleLoader$zone.getAllEntities();
    }

}
