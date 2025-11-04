package io.github.puzzle.cosmic.impl.data.point.single;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;
import org.jetbrains.annotations.Nullable;

public class EnumDataPoint<T extends Enum> extends AbstractDataPoint<T> {

    public EnumDataPoint() {
        super(null);
    }

    public EnumDataPoint(Class<T> enumType) {
        super(enumType);
    }

    public EnumDataPoint(T value) {
        super((Class<T>) value.getClass(), value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(deserializer.readString("type"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        value = (T) clazz.getEnumConstants()[deserializer.readInt("v", 0)];
    }

    @Override
    public boolean isOfType(Class<?> typeClass) {
        return value.getClass().isAssignableFrom(typeClass);
    }

    @Override
    public @Nullable Class<T> getClassType() {
        return (Class<T>) value.getClass();
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeString("type", value.getClass().getName());
        serializer.writeInt("v", value.ordinal());
    }
}
