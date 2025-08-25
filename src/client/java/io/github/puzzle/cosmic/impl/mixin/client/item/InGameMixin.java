package io.github.puzzle.cosmic.impl.mixin.client.item;

import dev.puzzleshq.puzzleloader.loader.util.ReflectionUtil;
import finalforeach.cosmicreach.BlockRaycasts;
import finalforeach.cosmicreach.BlockSelection;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.networking.client.ClientNetworkManager;
import finalforeach.cosmicreach.settings.ControlSettings;
import finalforeach.cosmicreach.singletons.GameSingletons;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.item.IItem;
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
            if (stack != null && stack.getItem() instanceof IItem item) {
                if ((ControlSettings.keyUsePlace.isPressed() && !isPressed) || (ControlSettings.keyAttackBreak.isPressed() && !isPressed)) {
                    BlockPosition targetPlaceBlockPos = null;
                    BlockPosition targetBreakBlockPos = null;
                    try {
                        targetPlaceBlockPos = ((BlockRaycasts) ReflectionUtil.getField(blockSelection, "blockRaycasts").get(blockSelection)).getPlacingBlockPos();
                        targetBreakBlockPos = ((BlockRaycasts) ReflectionUtil.getField(blockSelection, "blockRaycasts").get(blockSelection)).getBreakingBlockPos();
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    boolean isLeftClick = ControlSettings.keyAttackBreak.isPressed();

                    if (GameSingletons.isHost){
                        item.use(
                                APISide.SINGLE_PLAYER_CLIENT,
                                UI.hotbar.getSelectedSlot(),
                                localPlayer,
                                targetPlaceBlockPos,
                                targetBreakBlockPos,
                                isLeftClick
                        );
                    } else {
                        ItemUsePacket packet = new ItemUsePacket(
                                UI.hotbar.getSelectedSlotNum(),
                                targetPlaceBlockPos,
                                targetBreakBlockPos,
                                isLeftClick
                        );
                        ClientNetworkManager.sendAsClient(packet);
                        item.use(
                                APISide.REMOTE_CLIENT,
                                UI.hotbar.getSelectedSlot(),
                                localPlayer,
                                targetPlaceBlockPos,
                                targetBreakBlockPos,
                                isLeftClick
                        );
                    }
                    isPressed = true;
                }
                if ((isPressed && !ControlSettings.keyUsePlace.isPressed()) && (isPressed && !ControlSettings.keyAttackBreak.isPressed())) isPressed = false;
            }
        }
    }

}
