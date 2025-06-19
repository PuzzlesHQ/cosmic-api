package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Block")
public interface IBlock {

    /**
     * Gets all the blockState of the block.
     * @return a {@link BlockStateMap} of the BlockStates.
     */
    BlockStateMap getStates();


    /**
     * Gets the identifier of the block.s
     *
     * @return a {@link Identifier}
     */
    Identifier getIdentifier();


    interface BlockStateMap {
        BlockState get(String key);
    }
}
