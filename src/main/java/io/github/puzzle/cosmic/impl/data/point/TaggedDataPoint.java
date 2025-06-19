package io.github.puzzle.cosmic.impl.data.point;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import io.github.puzzle.cosmic.api.data.point.IDataPoint;
import io.github.puzzle.cosmic.api.data.point.ITaggedDataPoint;

public class TaggedDataPoint<V> implements ITaggedDataPoint<V> {

    protected String name;
    protected IDataPoint<V> point;

    public TaggedDataPoint() {}
    public TaggedDataPoint(String name, IDataPoint<V> point) {
        this.name = name;
        this.point = point;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        name = deserializer.readString("tag-name");
        try {
            point = (IDataPoint<V>) deserializer.readObj("tag-value", Class.forName(deserializer.readString("tag-type")));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeString("tag-name", name);
        serializer.writeString("tag-type", point.getClass().getName());
        serializer.writeObj("tag-value", point);
    }

    @Override
    public V setValue(V v) {
        return point.setValue(v);
    }

    @Override
    public V getValue() {
        return point.getValue();
    }

    @Override
    public Class<V> getClassType() {
        return point.getClassType();
    }
}
