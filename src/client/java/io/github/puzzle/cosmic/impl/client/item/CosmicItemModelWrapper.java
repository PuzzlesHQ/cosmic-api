package io.github.puzzle.cosmic.impl.client.item;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import dev.puzzleshq.puzzleloader.loader.util.ReflectionUtil;
import finalforeach.cosmicreach.entities.ItemEntity;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.rendering.items.HeldItemRenderParams;
import finalforeach.cosmicreach.rendering.items.ItemModel;
import finalforeach.cosmicreach.ui.UI;
import io.github.puzzle.cosmic.api.client.model.ICosmicItemModel;

import java.lang.ref.WeakReference;

public class CosmicItemModelWrapper extends ItemModel implements ICosmicItemModel {

    public static ItemModel wrap(ICosmicItemModel model) {
        return new CosmicItemModelWrapper(model);
    }

    ICosmicItemModel parent;
    public CosmicItemModelWrapper(ICosmicItemModel parent) {
        this.parent = parent;
    }

    @Override
    public void renderInSlot(Vector3 pos, ItemStack stack, Camera slotCamera, Matrix4 tmpMatrix, boolean useAmbientLighting) {
        parent.renderInSlot(pos, stack, slotCamera, tmpMatrix, useAmbientLighting);
    }

    @Override
    public void render(Vector3 vector3, Camera camera, Matrix4 matrix4, boolean useAmbientLighting, boolean applyFog, ItemStack itemStack, Color color) {
        renderInSlot(vector3, null, camera, matrix4, useAmbientLighting);
    }

    @Override
    public void dispose(WeakReference<Item> itemRef) {
        parent.dispose(itemRef);
    }

    @Override
    public Camera getItemSlotCamera() {
        return parent.getItemSlotCamera();
    }

    @Override
    public void renderAsHeldItem(Vector3 vector3, Camera camera, HeldItemRenderParams heldItemRenderParams) {
        ItemStack stack = UI.hotbar.getSelectedItemStack();
        try {
            renderAsHeldItem(
                    vector3,
                    stack,
                    camera,
                    (Float) ReflectionUtil.getField(heldItemRenderParams, "popUpTimer").get(heldItemRenderParams),
                    (Float) ReflectionUtil.getField(heldItemRenderParams, "maxPopUpTimer").get(heldItemRenderParams),
                    (Float) ReflectionUtil.getField(heldItemRenderParams, "swingTimer").get(heldItemRenderParams),
                    (Float) ReflectionUtil.getField(heldItemRenderParams, "maxSwingTimer").get(heldItemRenderParams)
            );
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renderAsItemEntity(Vector3 vector3, Camera camera, Matrix4 matrix4, ItemEntity itemEntity) {
        renderAsEntity(vector3, null, camera, matrix4);
    }

    @Override
    public void renderAsHeldItem(Vector3 pos, ItemStack stack, Camera handCam, float popUpTimer, float maxPopUpTimer, float swingTimer, float maxSwingTimer) {
        parent.renderAsHeldItem(pos, stack, handCam, popUpTimer, maxPopUpTimer, swingTimer, maxSwingTimer);
    }

    @Override
    public void renderAsEntity(Vector3 pos, ItemStack stack, Camera entityCam, Matrix4 tmpMatrix) {
        parent.renderAsEntity(pos, stack, entityCam, tmpMatrix);
    }
}
