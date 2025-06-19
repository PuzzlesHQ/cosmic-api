package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import finalforeach.cosmicreach.util.GameTag;
import finalforeach.cosmicreach.util.GameTagList;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.Collection;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockState")
public interface PuzzleBlockState extends IBlockState {

    /**
     * Sets the tags of the blockState.
     * @param tags a GameTagList of all the tags.
     */
    void setTags(GameTagList tags);

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    void addTags(GameTag... tags);

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    void addTags(Collection<GameTag> tags);

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    void removeTags(GameTag... tags);

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    void removeTags(Collection<GameTag>... tags);

}
