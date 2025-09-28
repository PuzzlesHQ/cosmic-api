package io.github.puzzle.cosmic.item;

import finalforeach.cosmicreach.util.Identifier;

public class BasicItem extends AbstractCosmicItem {

    String name;

    public BasicItem(Identifier id) {
        super(id);
        name = id.getName();
        addTexture(ItemModelType.ITEM_MODEL_3D, Identifier.of(id.getNamespace(), "textures/items/" + id.getName() + ".png"));
    }

    public BasicItem(Identifier id, ItemModelType itemModelType) {
        super(id);
        name = id.getName();
        addTexture(itemModelType, Identifier.of(id.getNamespace(), "textures/items/" + id.getName() + ".png"));
    }

    public BasicItem(Identifier id, Identifier textureLocation) {
        super(id);
        name = id.getName();
        addTexture(ItemModelType.ITEM_MODEL_3D, textureLocation);
    }

    public BasicItem(Identifier id, Identifier textureLocation, ItemModelType itemModelType) {
        super(id);
        name = id.getName();
        addTexture(itemModelType, textureLocation);
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
