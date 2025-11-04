package io.github.puzzle.cosmic.impl.data.point.array;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.data.point.DataPointCastingException;
import io.github.puzzle.cosmic.api.data.point.IDataPoint;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDataPoint<T extends ICRBinSerializable> extends AbstractDataPoint<List<T>> implements Iterable<T> {

    public ListDataPoint() {
        super(null);

        value = new ArrayList<>();
    }

    public ListDataPoint(List<T> value) {
        super(null, value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        try {
            int size = deserializer.readInt("size", 0);
            setValue(new ArrayList<>());
            for (int i = 0; i < size; i++) {
                value.add((T) deserializer.readObj(
                        "v_" + i,
                        Class.forName(deserializer.readString("type_" + i))
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isOfType(Class<?> typeClass) {
        if (value == null) {
            return List.class.isAssignableFrom(typeClass);
        } else {
            return List.class.isAssignableFrom(typeClass) || value.getClass().isAssignableFrom(typeClass);
        }
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeInt("size", value.size());
        for (int i = 0; i < value.size(); i++) {
            serializer.writeString("type_" + i, value.get(i).getClass().getName());
            serializer.writeObj("v_" + i, (ICRBinSerializable) value.get(i));
        }
    }

    @Override
    public <C> IDataPoint<C> cast(Class<C> castType) {
        if (!this.isOfType(castType)) {
            throw DataPointCastingException.of(this, castType);
        } else {
            return (IDataPoint<C>) this;
        }
    }

    @Override
    public @Nullable Class<List<T>> getClassType() {
        return (Class<List<T>>) value.getClass();
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return value.iterator();
    }
}
