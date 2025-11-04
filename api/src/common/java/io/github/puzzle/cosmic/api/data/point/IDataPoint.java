package io.github.puzzle.cosmic.api.data.point;

import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IDataPoint<T> extends ICRBinSerializable {

    /**
     * Sets the value of the DataPoint.
     * @param value the value to set.
     */
    T setValue(T value);

    /**
     * Gets the value of the DataPoint.
     */
    T getValue();

    /**
     * Checks if the DataPoint is this type.
     * @param typeClass the type to check.
     */
    default boolean isOfType(Class<?> typeClass) {
        return getClassType().isAssignableFrom(typeClass);
    }

    /**
     * Casts this {@link IDataPoint} instance to the specified type.
     * @param castType the {@link Class} type to cast this instance to.
     * @param <C> the type to cast the {@code IDataPoint} instance to.
     */
    default <C> IDataPoint<C> cast(Class<C> castType) {
        if (!isOfType(castType)) throw DataPointCastingException.of(this, castType);

        return (IDataPoint<C>) this;
    }

    /**
     * Gets the {@link IDataPoint} class type.
     */
    Class<T> getClassType();

}
