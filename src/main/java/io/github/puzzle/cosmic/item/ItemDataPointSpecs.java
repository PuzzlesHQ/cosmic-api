package io.github.puzzle.cosmic.item;

import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.impl.data.point.TaggedDataPointSpec;
import io.github.puzzle.cosmic.impl.data.point.array.ListDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.EnumDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.IdentifierDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.IntegerDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.PairDataPoint;

import java.util.List;

public class ItemDataPointSpecs {

    public static final TaggedDataPointSpec<List<PairDataPoint<EnumDataPoint<IItem.ItemModelType>, IdentifierDataPoint>>> TEXTURE_DICT;

    @Deprecated
    public static final TaggedDataPointSpec<Integer> TEXTURE_INDEX;
    @Deprecated
    public static final TaggedDataPointSpec<IItem.ItemModelType> MODEL_TYPE;
    @Deprecated
    public static final TaggedDataPointSpec<Identifier> TEXTURE_LOCATION;

    static {
        TEXTURE_DICT = new TaggedDataPointSpec<>("texture_dict", ListDataPoint::new);
        TEXTURE_INDEX = new TaggedDataPointSpec<>("texture_index", IntegerDataPoint::new);
        TEXTURE_LOCATION = new TaggedDataPointSpec<>("texture_location", IdentifierDataPoint::new);
        MODEL_TYPE = new TaggedDataPointSpec<>("model_type", () -> new EnumDataPoint<>(IItem.ItemModelType.class));
    }

}
