package io.github.puzzle.cosmic.api.item;

import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.world.IZone;

public interface ITickingItem {

    /**
     * This allows to add multiple textures to an item for later.
     * @param fixedUpdateTimeStep elapsed time since the last update.
     * @param itemStack The ItemStack that will be tick.
     * @param isBeingHeld If the ItemStack is being held by player.
     */
    default void tickStack(float fixedUpdateTimeStep, IItemStack itemStack, boolean isBeingHeld) {}


    /**
     * This allows to add multiple textures to an item for later.
     * @param zone the zone this ItemStack is in.
     * @param deltaTime elapsed time since the last update.
     * @param entity the ItemEntity that contains the ItemStack.
     * @param itemStack The ItemStack that will be tick.
     */
    default void tickEntity(IZone zone, double deltaTime, IEntity entity, IItemStack itemStack) {}

}
