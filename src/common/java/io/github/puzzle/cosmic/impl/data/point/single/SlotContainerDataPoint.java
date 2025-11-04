package io.github.puzzle.cosmic.impl.data.point.single;

import finalforeach.cosmicreach.items.containers.SlotContainer;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import io.github.puzzle.cosmic.impl.data.point.AbstractDataPoint;

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
}
