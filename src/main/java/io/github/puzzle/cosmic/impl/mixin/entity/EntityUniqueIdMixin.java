package io.github.puzzle.cosmic.impl.mixin.entity;

import finalforeach.cosmicreach.entities.EntityUniqueId;
import io.github.puzzle.cosmic.api.entity.IEntityUniqueId;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(EntityUniqueId.class)
public abstract class EntityUniqueIdMixin implements IEntityUniqueId {

    @Unique
    private final transient EntityUniqueId puzzleLoader$uid = IEntityUniqueId.as(this);

    public long pGetTime() {
        return puzzleLoader$uid.getTime();
    }

    public int pGetRand() {
        return puzzleLoader$uid.getRand();
    }

    public int pGetNumber() {
        return puzzleLoader$uid.getNumber();
    }

}
