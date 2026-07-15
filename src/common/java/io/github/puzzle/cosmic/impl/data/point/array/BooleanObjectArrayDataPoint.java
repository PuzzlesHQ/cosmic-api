package io.github.puzzle.cosmic.impl.data.point.array;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.SchemaType;
import io.github.puzzle.cosmic.api.data.point.IDataPoint;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;

public class BooleanObjectArrayDataPoint extends AbstractDataPoint<Boolean[]> {

    public BooleanObjectArrayDataPoint() {
        super(Boolean[].class);
    }

    public BooleanObjectArrayDataPoint(Boolean[] value) {
        super(Boolean[].class, value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        boolean[] backing = deserializer.readBooleanArray("v");
        setValue(new Boolean[backing.length]);
        for (int i = 0; i < backing.length; i++)
            value[i] = backing[i];
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeArray("v", SchemaType.BOOLEAN_ARRAY, value.length, (i) -> {
            serializer.writeBoolean(null, value[i]);
        });
    }

    @Override
    public IDataPoint<Boolean[]> copy() {
        Boolean[] clone = new Boolean[getValue().length];
        System.arraycopy(value, 0, clone, 0, value.length);
        return new BooleanObjectArrayDataPoint(clone);
    }
}
