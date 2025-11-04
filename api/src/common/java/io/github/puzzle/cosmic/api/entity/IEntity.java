package io.github.puzzle.cosmic.api.entity;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IEntity extends ICRBinSerializable {


    /**
     * Gets the view direction of the entity.
     */
    default Vector3 getViewDirection() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }


    /**
     * Gets the entities' ID.
     * @return a {@link Identifier}
     */
    default Identifier getEntityId() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Gets the point manifest of the entity.
     * @return a {@link IDataPointManifest}
     */
    default IDataPointManifest getPointManifest() {
        throw new UnsupportedOperationException("Contact this mod-developer if this error ever appears.");
    }

    /**
     * Sets the point manifest of the entity.
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
