package io.github.puzzle.cosmic.impl.client.engine.rendering.text;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.ui.FontRenderer;
import finalforeach.cosmicreach.ui.HorizontalAnchor;
import finalforeach.cosmicreach.ui.VerticalAnchor;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormattedTextRenderer {

    static final Map<String, FormatText> textCache = new HashMap<>();

    public static @Nullable FormatText getFormatTextFromString(String str) {
        return textCache.get(str);
    }

    public static void drawText(Batch batch, Viewport vp, String text, float xStart, float yStart) {
        drawText(batch, vp, text, xStart, yStart, false);
    }

    public static void drawTextNoCache(Batch batch, Viewport vp, String text, float xStart, float yStart) {
        drawTextNoCache(batch, vp, text, xStart, yStart, false);
    }

    public static void drawText(Batch batch, Viewport uiViewport, String text, float xStart, float yStart, HorizontalAnchor hAnchor, VerticalAnchor vAnchor) {
        Color resetColor = batch.getColor().cpy();

        FormatText formatted = textCache.get(text);
        formatted = formatted == null ? FormatText.of(text, resetColor) : formatted;

        drawText(batch, uiViewport, formatted, xStart, yStart, hAnchor, vAnchor);
    }

    public static void drawTextNoCache(Batch batch, Viewport uiViewport, String text, float xStart, float yStart, HorizontalAnchor hAnchor, VerticalAnchor vAnchor) {
        Color resetColor = batch.getColor().cpy();

        var formatted  = FormatText.of(text, resetColor);

        drawText(batch, uiViewport, formatted, xStart, yStart, hAnchor, vAnchor);
    }

    public static void drawTextNoCache(Batch batch, Viewport vp, String text, float xStart, float yStart, boolean flip) {
        Color resetColor = batch.getColor().cpy();

        var formatted  = FormatText.of(text, resetColor);

        drawText(batch, vp, formatted, xStart, yStart, flip);
    }

    public static void drawText(Batch batch, Viewport vp, String text, float xStart, float yStart, boolean flip) {
        Color resetColor = batch.getColor().cpy();

        FormatText formatted = textCache.get(text);
        formatted = formatted == null ? FormatText.of(text, resetColor) : formatted;

        drawText(batch, vp, formatted, xStart, yStart, flip);
    }

    public static void drawText(Batch batch, Viewport vp, FormatText text, float xStart, float yStart) {
        drawText(batch, vp, text, xStart, yStart, false);
    }

    public static void drawText(Batch batch, Viewport uiViewport, FormatText text, float xStart, float yStart, HorizontalAnchor hAnchor, VerticalAnchor vAnchor) {
        float w = getRenderWidth(uiViewport, text);
        float h = getRenderHeight(uiViewport, text);
        if (hAnchor != null) {
            switch (hAnchor) {
                case LEFT_ALIGNED:
                    xStart -= uiViewport.getWorldWidth() / 2.0F;
                    break;
                case RIGHT_ALIGNED:
                    xStart = xStart + uiViewport.getWorldWidth() / 2.0F - w;
                    break;
                case CENTERED:
                default:
                    xStart -= w / 2.0F;
            }
        }

        if (vAnchor != null) {
            switch (vAnchor) {
                case TOP_ALIGNED:
                    yStart -= uiViewport.getWorldHeight() / 2.0F;
                    break;
                case BOTTOM_ALIGNED:
                    yStart = yStart + uiViewport.getWorldHeight() / 2.0F - h;
                    break;
                case CENTERED:
                default:
                    yStart -= h / 2.0F;
            }
        }

        drawText(batch, uiViewport, text, xStart, yStart);
    }

    public static void drawText(Batch batch, Viewport vp, FormatText text, float xStart, float yStart, boolean flip) {
        Color resetColor = batch.getColor().cpy();

        text.drawParts(batch, vp, xStart, yStart, flip);

        batch.setColor(resetColor);
    }

    public static float getRenderWidth(Viewport vp, String text) {
        return getRenderWidth(vp, FormatText.of(text));
    }

    public static float getRenderHeight(Viewport vp, String text) {
        return getRenderHeight(vp, FormatText.of(text));
    }

    public static float getRenderWidth(Viewport vp, FormatText text) {
        float totalSize = 0;
        List<FormatText.TextPart> parts = text.getParts();

        for (FormatText.TextPart part : parts) {
            totalSize += part.getWidth(vp);
        }
        return totalSize;
    }

    public static float getRenderHeight(Viewport vp, FormatText text) {
        float totalSize = 0;
        List<FormatText.TextPart> parts = text.getParts();

        for (FormatText.TextPart part : parts) {
            if (totalSize < part.getHeight(vp))
                totalSize += part.getHeight(vp);
        }
        return totalSize;
    }

}