package io.github.puzzle.cosmic.item;

import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.item.ITickingItem;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.APISide;

import static io.github.puzzle.cosmic.CosmicConstants.MOD_ID;

public class NullStick extends AbstractCosmicItem implements ITickingItem {

    int texture_count;

    public NullStick() {
        super(Identifier.of("puzzle-loader", "null_stick"));

        addTexture(
            ItemModelType.ITEM_MODEL_3D,
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png")
        );

        addTexture(
                ItemModelType.ITEM_MODEL_2D,
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png"),
                Identifier.of(MOD_ID, "null_stick.png")
        );

        texture_count = getPointManifest().get(ItemDataPointSpecs.TEXTURE_DICT).getValue().size() - 1;
    }

    @Override
    public boolean use(APISide side, ItemSlot itemSlot, Player player, BlockPosition targetPlaceBlockPos, BlockPosition targetBreakBlockPos, boolean isLeftClick) {
        if ((side == APISide.SERVER || side == APISide.SINGLE_PLAYER_CLIENT) && !isLeftClick) {
            //TODO maybe add a ray cast example
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
    public void tickEntity(IZone zone, double deltaTime, IEntity entity, IItemStack stack) {
        int currentEntry = getCurrentTexture(stack);
        currentEntry = currentEntry >= texture_count ? 0 : currentEntry + 1;
        setCurrentTexture(stack, currentEntry);
    }

    @Override
    public void tickStack(float fixedUpdateTimeStep, IItemStack stack, boolean isBeingHeld) {
        int currentEntry = getCurrentTexture(stack);
        currentEntry = currentEntry >= texture_count ? 0 : currentEntry + 1;
        setCurrentTexture(stack, currentEntry);
    }

    @Override
    public String getName() {
        return "Debug | Null Stick";
    }
}
