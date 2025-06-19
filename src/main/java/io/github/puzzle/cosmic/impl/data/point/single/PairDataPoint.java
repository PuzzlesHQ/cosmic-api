package io.github.puzzle.cosmic.impl.data.point.single;

import com.llamalad7.mixinextras.lib.apache.commons.tuple.ImmutablePair;
import com.llamalad7.mixinextras.lib.apache.commons.tuple.Pair;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;

public class PairDataPoint<A extends ICRBinSerializable, B extends ICRBinSerializable> extends AbstractDataPoint<Pair<A, B>> {

    public PairDataPoint() {
        super(null);
    }

    public PairDataPoint(Pair<A, B> value) {
        super(null, value);
    }

    public PairDataPoint(A a, B b) {
        this(new ImmutablePair<>(a, b));
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        try {
            final A a = (A) deserializer.readObj(
                    "a_v",
                    Class.forName(deserializer.readString("a_type"))
            );

            final B b = (B) deserializer.readObj(
                    "b_v",
                    Class.forName(deserializer.readString("b_type"))
            );

            setValue(new Pair<A, B>() {
                A left = a;
                B right = b;

                @Override
                public A getLeft() {
                    return left;
                }

                @Override
                public B getRight() {
                    return right;
                }

                @Override
                public B setValue(B value) {
                    return right = value;
                }
            });

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeString("a_type", value.getLeft().getClass().getName());
        serializer.writeObj("a_v", value.getLeft());
        serializer.writeString("b_type", value.getRight().getClass().getName());
        serializer.writeObj("b_v", value.getRight());
    }

    @Override
    public boolean isOfType(Class<?> typeClass) {
        if (value == null) {
            return Pair.class.isAssignableFrom(typeClass);
        } else {
            return value.getClass().isAssignableFrom(typeClass);
        }
    }

    @Override
    public Class<Pair<A, B>> getClassType() {
        return (Class<Pair<A, B>>) value.getClass();
    }
}
