package io.github.puzzle.cosmic.impl.mixin.client.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import dev.puzzleshq.puzzleloader.loader.util.ReflectionUtil;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.ItemEntity;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.rendering.entities.instances.ItemEntityModelInstance;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.impl.client.item.CosmicItemModelWrapper;
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
    private transient final Entity puzzleLoader$entity = (Entity) (Object) this;

    @Shadow(remap = false)
    @Final
    protected static Matrix4 tmpModelMatrix;

    @Inject(remap = false, method = "renderModelAfterMatrixSet", at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/rendering/entities/IEntityModelInstance;render(FLfinalforeach/cosmicreach/entities/Entity;Lcom/badlogic/gdx/graphics/Camera;Lcom/badlogic/gdx/math/Matrix4;Z)V", shift = At.Shift.BEFORE), cancellable = true)
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

}
