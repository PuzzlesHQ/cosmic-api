package io.github.puzzle.cosmic.item;

import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemBlock;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.util.APISide;

import static io.github.puzzle.cosmic.CosmicConstants.MOD_ID;

public class BlockWrench extends AbstractCosmicItem {

    public BlockWrench() {
        super(Identifier.of("puzzle-loader", "block_wrench"));
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(ItemModelType.ITEM_MODEL_3D));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(Identifier.of(MOD_ID, "block_wrench.png")));
    }

    @Override
    public boolean use(APISide side, ItemSlot itemSlot, Player player, BlockPosition targetPlaceBlockPos, BlockPosition targetBreakBlockPos, boolean isLeftClick) {
        if ((side == APISide.SERVER || side == APISide.SINGLE_PLAYER_CLIENT) && !isLeftClick) {
            if (targetBreakBlockPos == null) return false;
            BlockState state = targetBreakBlockPos.getBlockState();
            if (state == null) return false;
            BlockUtil.setBlockAt(targetBreakBlockPos.getZone(), ((ItemBlock) state.getItem().getNextSwapGroupItem()).getBlockState(), targetBreakBlockPos);
        }
        return false;
    }



    @Override
    public boolean isTool() {
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
