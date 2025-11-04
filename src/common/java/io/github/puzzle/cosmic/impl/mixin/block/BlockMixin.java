package io.github.puzzle.cosmic.impl.mixin.block;

import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.block.IBlock;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(Block.class)
public class BlockMixin implements IBlock {

    @Unique
    private final transient Block puzzleLoader$block = (Block)(Object)this;

    @Override
    public BlockStateMap getStates() {
        return key -> puzzleLoader$block.blockStates.get(key);
    }

    @Override
    public Identifier getIdentifier() {
        return Identifier.of(puzzleLoader$block.getStringId());
    }


}
