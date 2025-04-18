package io.github.puzzle.cosmic.item;

import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.items.ItemBlock;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.item.IItemSlot;
import io.github.puzzle.cosmic.util.APISide;

import static io.github.puzzle.cosmic.CosmicConstants.MOD_ID;

public class BlockWrench extends AbstractCosmicItem {

    public BlockWrench() {
        super(Identifier.of("puzzle-loader", "block_wrench"));
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(ItemModelType.ITEM_MODEL_3D));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(Identifier.of(MOD_ID, "block_wrench.png")));
    }

    @Override
    public boolean pUse(APISide side, IItemSlot slot, IPlayer player, IBlockPosition targetPlaceBlockPos, IBlockPosition targetBreakBlockPos, boolean isLeftClick) {
        if ((side == APISide.SERVER || side == APISide.SINGLE_PLAYER_CLIENT) && !isLeftClick) {
            if (targetBreakBlockPos == null) return false;
            BlockState state = targetBreakBlockPos.as().getBlockState();
            if (state == null) return false;
            BlockUtil.setBlockAt(targetBreakBlockPos.as().getZone(), ((ItemBlock) state.getItem().getNextSwapGroupItem()).getBlockState(), targetBreakBlockPos.as());
        }
        return false;
    }

    @Override
    public boolean pIsTool() {
        return true;
    }

    @Override
    public int getDefaultStackLimit() {
        return 1;
    }

    @Override
    public String getName() {
        return "State Wrench";
    }

}
