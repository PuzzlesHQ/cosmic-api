package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import finalforeach.cosmicreach.util.GameTag;
import finalforeach.cosmicreach.util.GameTagList;

import java.util.Collection;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface PuzzleBlockState extends IBlockState {

    /**
     * Sets the tags of the blockState.
     * @param tags a GameTagList of all the tags.
     */
    default void setTags(GameTagList tags)  {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    default void addTags(GameTag... tags) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    default void addTags(Collection<GameTag> tags) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    default void removeTags(GameTag... tags) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    default void removeTags(Collection<GameTag>... tags) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

}
