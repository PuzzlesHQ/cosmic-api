package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.GameTag;
import finalforeach.cosmicreach.util.GameTagList;
import io.github.puzzle.cosmic.api.block.PuzzleBlockState;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;

@Internal
@Mixin(BlockState.class)
public abstract class BlockStateMixin implements PuzzleBlockState {

    @Unique
    private final transient BlockState puzzleLoader$state = (BlockState) (Object)this;

    @Override
    public void setTags(GameTagList list) {
        puzzleLoader$state.tags = list;
    }

    @Override
    public void addTags(GameTag... tagz) {
        for (GameTag t : tagz) puzzleLoader$state.tags.add(t);
    }

    @Override
    public void removeTags(GameTag... tagz) {
        for (GameTag t : tagz) puzzleLoader$state.tags.remove(t);
    }

    @Override
    public void addTags(Collection<GameTag> collection) {
        puzzleLoader$state.tags.addAll(collection);
    }

    @SafeVarargs
    @Override
    public final void removeTags(Collection<GameTag>... collection) {
        puzzleLoader$state.tags.removeAll((Collection<?>) (Object) collection);
    }

}
