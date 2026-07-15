package io.github.puzzle.cosmic.impl.data.point.array;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import io.github.puzzle.cosmic.api.data.point.IDataPoint;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;

public class LongArrayDataPoint extends AbstractDataPoint<long[]> {

    public LongArrayDataPoint() {
        super(long[].class);
    }

    public LongArrayDataPoint(long[] value) {
        super(long[].class, value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        setValue(deserializer.readLongArray("v"));
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeLongArray("v", value);
    }

    @Override
    public IDataPoint<long[]> copy() {
        long[] clone = new long[getValue().length];
        System.arraycopy(value, 0, clone, 0, value.length);
        return new LongArrayDataPoint(clone);
    }
}
