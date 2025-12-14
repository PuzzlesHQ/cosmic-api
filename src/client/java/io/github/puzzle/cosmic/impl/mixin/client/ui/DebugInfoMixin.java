package io.github.puzzle.cosmic.impl.mixin.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.ui.FontRenderer;
import finalforeach.cosmicreach.ui.debug.DebugInfo;
import io.github.puzzle.cosmic.impl.client.engine.rendering.text.FormatText;
import io.github.puzzle.cosmic.impl.client.engine.rendering.text.FormattedTextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(DebugInfo.class)
public class DebugInfoMixin {
    @Redirect(method = "drawDebugText",at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/ui/FontRenderer;drawText(Lcom/badlogic/gdx/graphics/g2d/Batch;Lcom/badlogic/gdx/utils/viewport/Viewport;Ljava/lang/String;FF)V",ordinal = 1),remap = false)
    private static void redirectToFormattedDraw(Batch batch, Viewport uiViewport, String text, float xStart, float yStart){
        FormattedTextRenderer.drawTextNoCache(batch,uiViewport,text,xStart,yStart);
    }
    @Redirect(method = "drawDebugText",at = @At(value = "INVOKE", target = "Lfinalforeach/cosmicreach/ui/FontRenderer;drawText(Lcom/badlogic/gdx/graphics/g2d/Batch;Lcom/badlogic/gdx/utils/viewport/Viewport;Ljava/lang/String;FF)V",ordinal = 0),remap = false)
    private static void redirectToFormattedDrawOrd0(Batch batch, Viewport uiViewport, String text, float xStart, float yStart){
        Color resetColor = batch.getColor().cpy();
        FontRenderer.drawText(batch,uiViewport,FormatText.of(text, resetColor).getText(),xStart,yStart);
    }
}
