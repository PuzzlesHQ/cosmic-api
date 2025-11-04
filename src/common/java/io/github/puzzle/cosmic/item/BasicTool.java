package io.github.puzzle.cosmic.item;

import finalforeach.cosmicreach.util.Identifier;

public class BasicTool extends AbstractCosmicItem {

    String name;

    public BasicTool(Identifier id) {
        super(id);
        name = id.getName();
        addTexture(ItemModelType.ITEM_MODEL_3D, Identifier.of(id.getNamespace(), "textures/items/" + id.getName() + ".png"));
    }

    public BasicTool(Identifier id, ItemModelType itemModelType) {
        super(id);
        name = id.getName();
        addTexture(itemModelType, Identifier.of(id.getNamespace(), "textures/items/" + id.getName() + ".png"));
    }

    public BasicTool(Identifier id, Identifier textureLocation) {
        super(id);
        name = id.getName();
        addTexture(ItemModelType.ITEM_MODEL_3D, textureLocation);
    }

    public BasicTool(Identifier id, Identifier textureLocation, ItemModelType itemModelType) {
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

    @Override
    public boolean isTool() {
        return true;
    }

    @Override
    public int getDefaultStackLimit() {
        return 1;
    }
}
