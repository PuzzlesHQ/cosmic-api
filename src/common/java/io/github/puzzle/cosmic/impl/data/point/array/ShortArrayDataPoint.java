package io.github.puzzle.cosmic.impl.data.point.array;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import io.github.puzzle.cosmic.api.data.point.IDataPoint;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;

public class ShortArrayDataPoint extends AbstractDataPoint<short[]> {

    public ShortArrayDataPoint() {
        super(short[].class);
    }

    public ShortArrayDataPoint(short[] value) {
        super(short[].class, value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        setValue(deserializer.readShortArray("v"));
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeShortArray("v", value);
    }

    @Override
    public IDataPoint<short[]> copy() {
        short[] clone = new short[getValue().length];
        System.arraycopy(value, 0, clone, 0, value.length);
        return new ShortArrayDataPoint(clone);
    }
}
