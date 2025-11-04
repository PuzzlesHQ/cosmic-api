package io.github.puzzle.cosmic.impl.mixin.item;

import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements IItemStack {

    @Unique
    private final transient ItemStack puzzleLoader$stack = (ItemStack) (Object)this;

    @Unique
    private transient IDataPointManifest puzzleLoader$manifest = new DataPointManifest();

    @Inject(method = "read", at = @At("TAIL"), remap = false)
    private void write(CRBinDeserializer crbd, CallbackInfo ci) {
        IDataPointManifest manifest = crbd.readObj("point_manifest", DataPointManifest.class);
        if (manifest != null) setPointManifest(manifest);
    }

    @Inject(method = "write", at = @At("TAIL"), remap = false)
    private void write(CRBinSerializer crbs, CallbackInfo ci) {
        crbs.writeObj("point_manifest", puzzleLoader$manifest);
    }

    @Override
    public IDataPointManifest getPointManifest() {
        return puzzleLoader$manifest;
    }

    @Override
    public void setPointManifest(IDataPointManifest iDataPointManifest) {
        puzzleLoader$manifest = iDataPointManifest;
    }
}
