package io.github.puzzle.cosmic.api.util;

import finalforeach.cosmicreach.items.ItemStack;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.item.IItemStack;

public class DataPointUtil {

    /**
     * Gets the data point manifest of provided itemStack.
     * @param itemStack the itemStack to get the point manifest of.
     * @return a {@link IDataPointManifest}.
     */
    public static IDataPointManifest getManifestFromStack(ItemStack itemStack) {
        return ((IItemStack) itemStack).getPointManifest();
    }

    /**
     * Sets the point manifest of a itemStack.
     * @param manifest the new manifest to set.
     * @param itemStack the itemStack to add to.
     */
    public static ItemStack setManifestOnStack(IDataPointManifest manifest, ItemStack itemStack) {
        ((IItemStack) itemStack).setPointManifest(manifest);
        return itemStack;
    }

}
