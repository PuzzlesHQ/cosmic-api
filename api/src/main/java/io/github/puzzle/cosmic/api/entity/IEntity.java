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
    Vector3 getViewDirection();


    /**
     * Gets the entities' ID.
     * @return a {@link Identifier}
     */
    Identifier getEntityId();

    /**
     * Gets the point manifest of the entity.
     * @return a {@link IDataPointManifest}
     */
    IDataPointManifest getPointManifest();

    /**
     * Sets the point manifest of the entity.
     * @param manifest The new point manifest to be set.
     */
    void setPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer crBinDeserializer);

    @Override
    void write(CRBinSerializer crBinSerializer);

}
