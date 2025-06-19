package io.github.puzzle.cosmic.impl.client.item;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.github.puzzle.core.loader.util.Reflection;
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
    public void render(Vector3 vector3, Camera camera, Matrix4 matrix4, boolean useAmbientLighting, boolean applyFog) {
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
        renderAsHeldItem(
                vector3,
                stack,
                camera,
                Reflection.getFieldContents(heldItemRenderParams, "popUpTimer"),
                Reflection.getFieldContents(heldItemRenderParams, "maxPopUpTimer"),
                Reflection.getFieldContents(heldItemRenderParams, "swingTimer"),
                Reflection.getFieldContents(heldItemRenderParams, "maxSwingTimer")
        );
    }

    @Override
    public void renderAsItemEntity(Vector3 vector3, Camera camera, Matrix4 matrix4) {
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
