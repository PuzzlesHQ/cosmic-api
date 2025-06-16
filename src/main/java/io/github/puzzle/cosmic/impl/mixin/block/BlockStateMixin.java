package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.GameTag;
import finalforeach.cosmicreach.util.GameTagList;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.block.IBlock;
import io.github.puzzle.cosmic.api.block.PBlockState;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;

@Internal
@Mixin(BlockState.class)
public abstract class BlockStateMixin implements PBlockState {

    @Unique
    private final transient BlockState puzzleLoader$state = PBlockState.as(this);

    @Override
    public IBlock pGetBlock() {
        return (IBlock) puzzleLoader$state.getBlock();
    }

    @Override
    public IItem pGetAsItem() {
        return (IItem) puzzleLoader$state.getItem();
    }

    @Override
    public IIdentifier pGetBlockID() {
        return (IIdentifier) Identifier.of(puzzleLoader$state.getBlockId());
    }

    @Override
    public String pGetSaveKey() {
        return puzzleLoader$state.getSaveKey();
    }

    @Override
    public void pSetTags(GameTagList list) {
        puzzleLoader$state.tags = list;
    }

    @Override
    public void pAddTags(GameTag... tagz) {
        for (GameTag t : tagz) puzzleLoader$state.tags.add(t);
    }

    @Override
    public void pRemoveTags(GameTag... tagz) {
        for (GameTag t : tagz) puzzleLoader$state.tags.remove(t);
    }

    @Override
    public void pAddTags(Collection<GameTag> collection) {
        puzzleLoader$state.tags.addAll(collection);
    }

    @SafeVarargs
    @Override
    public final void pRemoveTags(Collection<GameTag>... collection) {
        puzzleLoader$state.tags.removeAll((Collection<?>) (Object) collection);
    }

}
