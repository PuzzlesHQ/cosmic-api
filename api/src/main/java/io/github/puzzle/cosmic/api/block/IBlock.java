package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.util.Identifier;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IBlock {

    /**
     * Gets all the blockState of the block.
     * @return a {@link BlockStateMap} of the BlockStates.
     */
    default BlockStateMap getStates() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }


    /**
     * Gets the identifier of the block.s
     *
     * @return a {@link Identifier}
     */
    default Identifier getIdentifier() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }


    interface BlockStateMap {
        BlockState get(String key);
    }
}
