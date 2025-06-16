package io.github.puzzle.cosmic.api.item;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.block.PBlockState;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("ItemStack")
public interface IItemStack extends ICRBinSerializable {

    /**
     * Copies the itemStack.
     * @return a {@link IItemStack}
     */
    IItemStack pCopy();

    /**
     * Gets the item of the itemStack.
     * @return a {@link IItem}
     */
    IItem pGetItem();

    /**
     * Sets the item of the itemStack.
     * @param item the item to set.
     */
    void pSetItem(IItem item);

    /**
     * Cycles the item through the swap group.
     */
    void pCycleSwapGroupItem();

    /**
     * Spawns the itemStack as a itemEntity.
     * @param zone the zone to spawn the itemStack in.
     * @param pos the position to spawn the itemStack in.
     * @return a {@link IEntity}
     */
    IEntity pSpawnItemEntityAt(IZone zone, Vector3 pos);

    /**
     * Spawns the itemStack as a itemEntity.
     * @param position the blockPosition to spawn at.
     */
    void pSpawnItemEntityAt(IBlockPosition position);

    /**
     * Uses the itemStack.
     * @param itemSlot the slot the item is in.
     * @param player the player using the item.
     * @param position the block you're looking at.
     * @return {@code true} if successful.
     */
    boolean pUseItem(IItemSlot itemSlot, IPlayer player, IBlockPosition position);

    /**
     * Gets the durability of the itemStack.
     */
    int pGetDurability();

    /**
     * Gets the max durability of the itemStack.
     */
    int pGetMaxDurability();

    /**
     * Checks if the itemStack has durability.
     */
    boolean pHasDurability();

    /**
     * Damages the itemStack.
     * @param damage the amount of damage.
     */
    void pDamage(int damage);

    /**
     * Checks if the itemStack is broken.
     */
    boolean pIsBroken();

    /**
     * Checks if the itemStack can break the blockState.
     * @param blockState blockState to break.
     * @return {@code true} if successful.
     */
    boolean pCanTargetBlockForBreaking(PBlockState blockState);

    /**
     * Checks if the itemStack can interact with the blockState.
     * @param blockState blockState to interact with.
     * @return {@code true} if successful.
     */
    boolean pIsEffectiveBreaking(PBlockState blockState);

    /**
     * Gets the effective breaking speed of the itemStack.
     */
    float pGetEffectiveBreakingSpeed();

    /**
     * Sets the amount of items the itemStack has.
     * @param amount the amount to set.
     * @return a {@link IItemStack}
     */
    IItemStack pSetAmount(int amount);

    /**
     * Gets the item name.
     */
    String pGetName();

    /**
     * Gets the point manifest of the itemStack.
     * @return a {@link IDataPointManifest}
     */
    IDataPointManifest pGetPointManifest();

    /**
     * Sets the point manifest of the itemStack.
     * @param manifest The new point manifest to be set.
     */
    void pSetPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer crBinDeserializer);

    @Override
    void write(CRBinSerializer crBinSerializer);
}
