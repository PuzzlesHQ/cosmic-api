package io.github.puzzle.cosmic.impl.data.point.single;

import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;

public class PuzzleIdentifierDataPoint extends AbstractDataPoint<Identifier> {

    public PuzzleIdentifierDataPoint() {
        super(Identifier.class);
    }

    public PuzzleIdentifierDataPoint(Identifier value) {
        super(Identifier.class, value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        setValue((Identifier.of(deserializer.readString("v"))));
    }

    @Override
    public void write(CRBinSerializer serializer) {
        serializer.writeString("v", ((IIdentifier)value).asString());
    }
}
