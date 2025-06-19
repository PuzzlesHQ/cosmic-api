package io.github.puzzle.cosmic.api.item;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("ItemStack")
public interface IItemStack extends ICRBinSerializable {


    /**
     * Gets the point manifest of the itemStack.
     * @return a {@link IDataPointManifest}
     */
    IDataPointManifest getPointManifest();

    /**
     * Sets the point manifest of the itemStack.
     * @param manifest The new point manifest to be set.
     */
    void setPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer crBinDeserializer);

    @Override
    void write(CRBinSerializer crBinSerializer);
}
