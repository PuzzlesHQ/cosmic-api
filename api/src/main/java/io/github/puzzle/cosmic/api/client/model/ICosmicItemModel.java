package io.github.puzzle.cosmic.api.client.model;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemStack;

import java.lang.ref.WeakReference;

/**
 *
 * @author Mr_Zombii
 * @since 0.4.4
 */
public interface ICosmicItemModel {

    /**
     * Renders the item model in a slot.
     * @param pos the rendered position of the item model.
     * @param stack the item stack to be rendered.
     * @param slotCamera the camera to render the item.
     * @param tmpMatrix the position, scale, and rotational transformations of the item.
     * @param useAmbientLighting the toggle of the use of ambient lighting.
     */
    void renderInSlot(Vector3 pos, ItemStack stack, Camera slotCamera, Matrix4 tmpMatrix, boolean useAmbientLighting);

    /**
     * Renders the item model in your "hand".
     * @param pos the rendered position of the item model.
     * @param stack the item stack to be rendered.
     * @param handCam the camera to render the item.
     * @param popUpTimer the timer to time to pop-up animation.
     * @param maxPopUpTimer the max the time takes to pop the item up.
     * @param swingTimer the timer to time to swing animation.
     * @param maxSwingTimer the max the time takes to swing the item.
     */
    void renderAsHeldItem(Vector3 pos, ItemStack stack, Camera handCam, float popUpTimer, float maxPopUpTimer, float swingTimer, float maxSwingTimer);

    /**
     * Renders the item model in your world.
     * @param pos the rendered position of the item model.
     * @param stack the item stack to be rendered.
     * @param entityCam the camera to render the item.
     * @param tmpMatrix the position, scale, and rotational transformations of the item.
     */
    void renderAsEntity(Vector3 pos, ItemStack stack, Camera entityCam, Matrix4 tmpMatrix);

    /**
     * Disposes the item model to free memory.
     * @param itemRef the reference to the item.
     */
    void dispose(WeakReference<Item> itemRef);

    /**
     * Gets the item slot camera.
     * @return {@link Camera}
     */
    Camera getItemSlotCamera();

}
