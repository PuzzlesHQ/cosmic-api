package io.github.puzzle.cosmic.impl.mixin.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import dev.puzzleshq.puzzleloader.loader.util.ReflectionUtil;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.ItemEntity;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.rendering.entities.instances.ItemEntityModelInstance;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.Identifier;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.impl.client.item.CosmicItemModelWrapper;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Internal
@Mixin(Entity.class)
public abstract class EntityMixin implements IEntity {

    @Unique
    private final transient Entity puzzleLoader$entity = (Entity) (Object)this;

    @Unique
    private transient IDataPointManifest puzzleLoader$manifest = new DataPointManifest();



    @Override
    public Vector3 getViewDirection() {
        return puzzleLoader$entity.viewDirection;
    }


    @Override
    public Identifier getEntityId() {
        return Identifier.of(puzzleLoader$entity.entityTypeId);
    }


    @Shadow(remap = false)
    @Final
    protected static Matrix4 tmpModelMatrix;

    @Inject(remap = false, method = "renderModelAfterMatrixSet", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/rendering/entities/IEntityModelInstance;render(Lfinalforeach/cosmicreach/entities/Entity;Lcom/badlogic/gdx/graphics/Camera;Lcom/badlogic/gdx/math/Matrix4;Z)V", shift = At.Shift.BEFORE), cancellable = true)
    private void render(Camera worldCamera, boolean shouldRender, CallbackInfo ci) {
        if (puzzleLoader$entity.modelInstance instanceof ItemEntityModelInstance) {
            try {
                if (ReflectionUtil.getField(puzzleLoader$entity.modelInstance, "model").get(puzzleLoader$entity.modelInstance) instanceof CosmicItemModelWrapper m) {
                    ItemStack stack = (ItemStack) ReflectionUtil.getField(ItemEntity.class, "itemStack").get(this);
                    m.renderAsEntity(puzzleLoader$entity.position, stack, worldCamera, tmpModelMatrix);
                    ci.cancel();
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }

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
