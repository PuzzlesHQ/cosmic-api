package io.github.puzzle.cosmic.impl.mixin.client.menu;

import com.github.puzzle.core.loader.util.Reflection;
import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.networking.packet.items.UseModdedItemPacket;
import finalforeach.cosmicreach.BlockRaycasts;
import finalforeach.cosmicreach.BlockSelection;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.networking.client.ClientNetworkManager;
import finalforeach.cosmicreach.settings.ControlSettings;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.item.IPuzzleItem;
import io.github.puzzle.cosmic.api.item.IPuzzleItemSlot;
import io.github.puzzle.cosmic.impl.network.item.ItemUsePacket;
import io.github.puzzle.cosmic.util.APISide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGame.class)
public class InGameMixin {

    @Shadow(remap = false)
    private static Player localPlayer;
    @Shadow(remap = false)
    public BlockSelection blockSelection;
    boolean isPressed;

    @Inject(method = "update", at = @At("HEAD"), remap = false)
    private void update(float deltaTime, CallbackInfo ci) {
        if (UI.hotbar.getSelectedSlot() != null){
            ItemStack stack = UI.hotbar.getSelectedSlot().getItemStack();
            if (stack != null && stack.getItem() instanceof IPuzzleItem item) {
                if ((ControlSettings.keyUsePlace.isPressed() && !isPressed) || (ControlSettings.keyAttackBreak.isPressed() && !isPressed)) {
                    BlockRaycasts raycasts = Reflection.getFieldContents(blockSelection, "blockRaycasts");

                    BlockPosition targetPlaceBlockPos = raycasts.getPlacingBlockPos();
                    BlockPosition targetBreakBlockPos = raycasts.getBreakingBlockPos();
                    boolean isLeftClick = ControlSettings.keyAttackBreak.isPressed();

                    APISide side = APISide.SINGLE_PLAYER_CLIENT;
                    if (!GameSingletons.isHost){
                        ItemUsePacket packet = new ItemUsePacket(
                                UI.hotbar.getSelectedSlotNum(),
                                (IPuzzleBlockPosition) targetPlaceBlockPos,
                                (IPuzzleBlockPosition) targetBreakBlockPos,
                                isLeftClick
                        );
                        ClientNetworkManager.sendAsClient(packet);
                        side = APISide.REMOTE_CLIENT;
                    }

                    item._use(
                            side,
                            (IPuzzleItemSlot) UI.hotbar.getSelectedSlot(),
                            (IPuzzlePlayer) localPlayer,
                            (IPuzzleBlockPosition) targetPlaceBlockPos,
                            (IPuzzleBlockPosition) targetBreakBlockPos,
                            isLeftClick
                    );
                    isPressed = true;
                }
                if ((isPressed && !ControlSettings.keyUsePlace.isPressed()) && (isPressed && !ControlSettings.keyAttackBreak.isPressed())) isPressed = false;
            }
        }
    }

}
