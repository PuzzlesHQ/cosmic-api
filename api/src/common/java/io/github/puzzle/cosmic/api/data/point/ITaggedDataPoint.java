package io.github.puzzle.cosmic.api.data.point;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface ITaggedDataPoint<T> extends IDataPoint<T> {

    /**
     * Gets the name of the TaggedDataPoint.
     */
    String getName();

}
