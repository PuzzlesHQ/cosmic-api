package io.github.puzzle.cosmic.item;

import com.github.puzzle.core.Constants;
import com.github.puzzle.core.loader.meta.EnvType;
import com.github.puzzle.core.loader.util.Reflection;
import com.github.puzzle.game.PuzzleRegistries;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.util.GameTag;
import finalforeach.cosmicreach.util.GameTagList;
import finalforeach.cosmicreach.util.IGameTagged;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.data.point.ITaggedDataPoint;
import io.github.puzzle.cosmic.api.item.IItem;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.impl.client.item.CosmicItemModel;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.impl.data.point.single.EnumDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.IdentifierDataPoint;
import io.github.puzzle.cosmic.impl.data.point.single.PairDataPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCosmicItem implements IGameTagged, Item, IItem {

    public static GameTag MODDED_ITEM_TAG = GameTag.get("puzzle_modded_item");

    public final Identifier id;
    public final GameTagList list;
    public final DataPointManifest manifest;

    public AbstractCosmicItem(Identifier id) {
        this.id = id;
        this.list = new GameTagList();
        this.manifest = new DataPointManifest();
    }

    @Override
    public GameTagList getTags() {
        return new GameTagList();
    }

    @Override
    public void initTagList() {
        list.add(MODDED_ITEM_TAG);
    }

    @Override
    public boolean pIsModded() {
        return true;
    }

    @Override
    public DataPointManifest pGetPointManifest() {
        return manifest;
    }

    @Override
    public String getName() {
        return id.getName();
    }

    @Override
    public IIdentifier pGetIdentifier() {
        return (IIdentifier) id;
    }

    @Override
    public String getID() {
        return id.toString();
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean canMergeWith(Item item) {
        if (item.getID().equals(this.getID())) {
            return item.getClass().getName().equals(this.getClass().getName());
        }
        return false;
    }

    @Override
    public boolean canMergeWithSwapGroup(Item item) {
        if (item.getID().equals(this.getID())) {
            return item.getClass().getName().equals(this.getClass().getName());
        }
        return false;
    }

    @Override
    public boolean isCatalogHidden() {
        return false;
    }

    @Override
    public boolean hasIntProperty(String s) {
        return manifest.has(s);
    }

    @Override
    public int getIntProperty(String s, int i) {
        if (!manifest.has(s)) return i;
        if (!manifest.get(s).isOfType(Integer.class)) return i;

        return manifest.get(s).cast(Integer.class).getValue();
    }

    @Override
    public boolean hasFloatProperty(String s) {
        return manifest.has(s);
    }

    @Override
    public float getFloatProperty(String s, float v) {
        if (!manifest.has(s)) return v;

        if (manifest.get(s).isOfType(Integer.class))
            return manifest.get(s).cast(Integer.class).getValue();

        return manifest.get(s).cast(Float.class).getValue();
    }

    @Override
    public boolean canTargetBlockForBreaking(BlockState blockState) {
        return blockState.canRaycastForBreak();
    }

    @Override
    public float getBounciness() {
        return getFloatProperty("bounciness", 0);
    }

    /**
     * Adds multiple textures at ones to the item.
     * @param model ItemModel Identifier.
     * @param textures Textures Identifier.
     * @see AbstractCosmicItem#setCurrentTexture(IItemStack, int)
     */
    public final void addTexture(ItemModelType model, Identifier... textures) {
        for (Identifier location : textures) {
            addTexture(model, location);
        }
    }

    /**
     * Adds a texture to the item.
     * @param model ItemModel Identifier.
     * @param texture Texture Identifier,
     * @see AbstractCosmicItem#setCurrentTexture(IItemStack, int)
     */
    public final void addTexture(ItemModelType model, Identifier texture) {
        if (pGetPointManifest().has(ItemDataPointSpecs.TEXTURE_DICT)) {
            ITaggedDataPoint<List<PairDataPoint<EnumDataPoint<ItemModelType>, IdentifierDataPoint>>> texturesPoint = pGetPointManifest().get(ItemDataPointSpecs.TEXTURE_DICT);
            List<PairDataPoint<EnumDataPoint<ItemModelType>, IdentifierDataPoint>> textures = texturesPoint.getValue();
            textures.add(new PairDataPoint<>(new EnumDataPoint<>(model), new IdentifierDataPoint(texture)));
            texturesPoint.setValue(textures);
            pGetPointManifest().put(texturesPoint);
        } else {
            List<PairDataPoint<EnumDataPoint<ItemModelType>, IdentifierDataPoint>> textures = new ArrayList<>();
            textures.add(new PairDataPoint<>(new EnumDataPoint<>(model), new IdentifierDataPoint(texture)));
            pGetPointManifest().put(ItemDataPointSpecs.TEXTURE_DICT.create(textures));
        }
    }

    /**
     * Gets all the textures of the item.
     * @return a {@link List} of {@link PairDataPoint} value A is a {@link EnumDataPoint} of a {@link ItemModelType}
     * and value B is a {@link IdentifierDataPoint}.
     */
    public final List<PairDataPoint<EnumDataPoint<ItemModelType>, IdentifierDataPoint>> getTextures() {
        if (pGetPointManifest().has(ItemDataPointSpecs.TEXTURE_DICT)) {
            return pGetPointManifest().get(ItemDataPointSpecs.TEXTURE_DICT).getValue();
        }
        return new ArrayList<>();
    }

    /**
     * Gets all the textures of the item.
     * @param item the item to retrieve current textures from.
     * @return a {@link List} of {@link PairDataPoint} value A is a {@link EnumDataPoint} of a {@link ItemModelType}
     * and value B is a {@link IdentifierDataPoint}.
     */
    public static List<PairDataPoint<EnumDataPoint<ItemModelType>, IdentifierDataPoint>> getTextures(IItem item) {
        if (item.pGetPointManifest().has(ItemDataPointSpecs.TEXTURE_DICT)) {
            return item.pGetPointManifest().get(ItemDataPointSpecs.TEXTURE_DICT).getValue();
        }
        return new ArrayList<>();
    }

    /**
     * Sets the texture of the itemStack.
     * @param stack the itemStack to set texture of.
     * @param index the index of the texture.
     * @see AbstractCosmicItem#addTexture(ItemModelType, Identifier)
     * @see AbstractCosmicItem#addTexture(ItemModelType, Identifier...)
     */
    public static void setCurrentTexture(IItemStack stack, int index) {
        IDataPointManifest manifest = stack.pGetPointManifest();
        manifest.put(ItemDataPointSpecs.TEXTURE_INDEX.create(index));
    }

    /**
     * Gets the current texture index from itemStack.
     * @param stack the itemStack to retrieve current texture index from.
     */
    public static int getCurrentTexture(IItemStack stack) {
        IDataPointManifest manifest = stack.pGetPointManifest();
        if (!manifest.has(ItemDataPointSpecs.TEXTURE_INDEX)) {
            manifest.put(ItemDataPointSpecs.TEXTURE_INDEX.create(0));
            return 0;
        }
        return manifest.get(ItemDataPointSpecs.TEXTURE_INDEX).getValue();
    }

    /**
     * Registers the item.
     * @param item the item to register.
     */
    public static AbstractCosmicItem register(AbstractCosmicItem item) {
        if (EnvType.CLIENT == Constants.SIDE) {
            try {
                Class<?> clazz = Class.forName("io.github.puzzle.cosmic.impl.client.item.CosmicItemModel");
                Method method = Reflection.getMethod(clazz, "registerItemModel", Item.class);
                method.invoke(null, item);

            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        
        PuzzleRegistries.ITEMS.store((Identifier) item.pGetIdentifier(), item);
        return item;
    }

}
