package io.github.puzzle.cosmic.api.block;

import finalforeach.cosmicreach.savelib.blocks.IBlockState;
import io.github.puzzle.cosmic.api.item.IPuzzleItem;
import io.github.puzzle.cosmic.api.tmp.GameTagListTmpClass;
import io.github.puzzle.cosmic.api.tmp.GameTagTmpClass;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

import java.util.Collection;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("BlockState")
public interface IPuzzleBlockState extends IBlockState {

    /**
     * Gets the block of the blockState.
     * @return a {@link IPuzzleBlock}
     */
    IPuzzleBlock pGetBlock();

    /**
     * Gets the blockState as an item.
     * @return a {@link IPuzzleItem}
     */
    IPuzzleItem pGetAsItem();

    /**
     * Sets the tags of the blockState.
     * @param tags a GameTagList of all the tags.
     */
    void pSetTags(GameTagListTmpClass tags);

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    void pAddTags(GameTagTmpClass... tags);

    /**
     * Adds tags to the blockState.
     * @param tags the tags to be added.
     */
    void pAddTags(Collection<GameTagTmpClass> tags);

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    void pRemoveTags(GameTagTmpClass... tags);

    /**
     * Removes the tags on blockState.
     * @param tags the tags to be removed.
     */
    void pRemoveTags(Collection<GameTagTmpClass>... tags);

    /**
     * Gets the blockId of the blockState.
     * @return a {@link IPuzzleIdentifier}
     */
    IPuzzleIdentifier pGetBlockID();

    /**
     * Gets the saveKey of the blockState.
     */
    String pGetSaveKey();

}
