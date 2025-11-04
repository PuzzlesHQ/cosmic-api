package io.github.puzzle.cosmic.api.data.point;

import java.util.function.Supplier;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface ITaggedDataPointSpec<V> {

    /**
     * Gets the name of the ITaggedDataPointSpec.
     */
    String getName();

    /**
     * Gets the value supplier.
     */
    Supplier<IDataPoint<V>> getValueSupplier();

    /**
     * Creates a new {@link ITaggedDataPoint} instance with the specified value.
     * @param value the value to be added to the new data point.
     */
    ITaggedDataPoint<V> create(V value);

}
