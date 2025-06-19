package io.github.puzzle.cosmic.item;

import finalforeach.cosmicreach.util.Identifier;

import static io.github.puzzle.cosmic.CosmicConstants.MOD_ID;

public class CheckBoard extends AbstractCosmicItem {

    public CheckBoard() {
        super(Identifier.of("puzzle-loader", "checker_board"));
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(ItemModelType.ITEM_MODEL_3D));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(Identifier.of(MOD_ID, "checker_board.png")));
    }

    @Override
    public String getName() {
        return "Debug | Checker Board";
    }

}
