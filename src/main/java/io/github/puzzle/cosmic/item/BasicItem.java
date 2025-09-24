package io.github.puzzle.cosmic.item;

import finalforeach.cosmicreach.util.Identifier;

public class BasicItem extends AbstractCosmicItem {

    String name;

    public BasicItem(Identifier id) {
        super(id);
        name = id.getName();
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(ItemModelType.ITEM_MODEL_3D));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(Identifier.of(id.getNamespace(), "textures/items/" + id.getName() + ".png")));
    }

    public BasicItem(Identifier id, ItemModelType itemModelType) {
        super(id);
        name = id.getName();
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(itemModelType));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(Identifier.of(id.getNamespace(), "textures/items/" + id.getName() + ".png")));
    }

    public BasicItem(Identifier id, Identifier textureLocation) {
        super(id);
        name = id.getName();
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(ItemModelType.ITEM_MODEL_3D));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(textureLocation));
    }

    public BasicItem(Identifier id, Identifier textureLocation, ItemModelType itemModelType) {
        super(id);
        name = id.getName();
        manifest.put(ItemDataPointSpecs.MODEL_TYPE.create(itemModelType));
        manifest.put(ItemDataPointSpecs.TEXTURE_LOCATION.create(textureLocation));
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
