package io.github.puzzle.cosmic.api.data.point;

import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IDataPointManifest extends ICRBinSerializable {

    /**
     * Adds a ITaggedDataPoint to the IDataPointManifest.
     * @param point the dataPoint to be added.
     */
    <T> ITaggedDataPoint<T> put(ITaggedDataPoint<T> point);

    /**
     * Adds a IDataPoint to the IDataPointManifest.
     * @param name the name to add it as.
     * @param point the dataPoint to be added.
     */
    <T> IDataPoint<T> put(String name, IDataPoint<T> point);

    /**
     * Gets the IDataPoint with this name and type.
     * @param name the IDataPoint's name.
     * @param type the IDataPoint's type.
     */
    <T> IDataPoint<T> get(String name, Class<T> type);

    /**
     * Gets the IDataPoint with this name.
     * @param name the IDataPoint's name.
     */
    IDataPoint<?> get(String name);

    /**
     * Gets the ITaggedDataPoint with this  ITaggedDataPointSpec.
     * @param spec the ITaggedDataPointSpec to get the ITaggedDataPoint by.
     */
    <T> ITaggedDataPoint<T> get(ITaggedDataPointSpec<T> spec);

    /**
     * Checks if the DataPoint Manifest has a DataPoint with this name.
     * @param name the name to check.
     */
    boolean has(String name);

    /**
     * Checks if the DataPoint Manifest has a DataPoint with this name and type.
     * @param name the name to check.
     * @param type the type to check.
     */
    boolean has(String name, Class<?> type);

    /**
     * Checks if the DataPoint Manifest has a DataPoint with this ITaggedDataPointSpec.
     * @param spec the spec to check.
     */
    boolean has(ITaggedDataPointSpec<?> spec);

    /**
     * Removes the DataPoint with this name.
     * @param name the name to check.
     */
    void remove(String name);

}
