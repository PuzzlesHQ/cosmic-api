package io.github.puzzle.cosmic.api.item;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IItemStack extends ICRBinSerializable {


    /**
     * Gets the point manifest of the itemStack.
     * @return a {@link IDataPointManifest}
     */
    default IDataPointManifest getPointManifest() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Sets the point manifest of the itemStack.
     * @param manifest The new point manifest to be set.
     */
    default void setPointManifest(IDataPointManifest manifest) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    // ICRBinSerializable.java methods

    @Override
    default void read(CRBinDeserializer crBinDeserializer) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    @Override
    default void write(CRBinSerializer crBinSerializer) {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }
}
