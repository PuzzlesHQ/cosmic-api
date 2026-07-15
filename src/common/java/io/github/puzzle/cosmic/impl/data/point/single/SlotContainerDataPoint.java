package io.github.puzzle.cosmic.impl.data.point.single;

import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.data.point.IDataPoint;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.impl.data.point.TaggedObjectDataPoint;

import java.nio.ByteBuffer;

public class SlotContainerDataPoint extends AbstractDataPoint<SlotContainer> {

    public SlotContainerDataPoint() {
        super(SlotContainer.class);
    }

    public SlotContainerDataPoint(SlotContainer value) {
        super(SlotContainer.class, value);
    }

    @Override
    public void read(CRBinDeserializer deserializer) {
        value = new SlotContainer(deserializer.readInt("numberOfSlots", -1));
        value.read(deserializer);
    }

    @Override
    public void write(CRBinSerializer serializer) {
        value.write(serializer);
    }

    @Override
    public IDataPoint<SlotContainer> copy() {
        DataPointManifest.serial.writeObj("obj", value);

        ByteBuffer buf = ByteBuffer.wrap(DataPointManifest.serial.toBytes());
        DataPointManifest.serial.reset();
        DataPointManifest.deserial.prepareForRead(buf);
        return new SlotContainerDataPoint(DataPointManifest.deserial.readObj("obj", value.getClass()));
    }
}
