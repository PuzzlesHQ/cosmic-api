package io.github.puzzle.cosmic.impl.client.engine.rendering.text;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import finalforeach.cosmicreach.FontTexture;
import finalforeach.cosmicreach.ui.FontRenderer;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static finalforeach.cosmicreach.ui.FontRenderer.getFontTexOfChar;
import static io.github.puzzle.cosmic.impl.client.engine.rendering.text.FormatColors.FORMAT_KEY;


public class FormatText {
    public static final Pattern FORMAT_PATTER = Pattern.compile(FORMAT_KEY + "(\\[([0-9A-Fa-f]{6})\\]|[0-9A-FRa-fr]{1})");
    public static final Pattern FORMAT_VALUE = Pattern.compile("(?i)("+FORMAT_KEY+"\\[[0-9A-FR]{6}][^"+FORMAT_KEY+"]+|"+FORMAT_KEY+"[0-9A-FR][^"+FORMAT_KEY+"]+)");

    public static Vector2 getLastCursorPlace(String text, Vector2 textDim) {
        float x = 0;
        float y = 0;
        int textLength = text.length();
        for (int i = 0; i < textLength; ++i) {
            char c = text.charAt(i);
            FontTexture f = getFontTexOfChar(c);
            if (f == null) {
                c = 7;
                f = getFontTexOfChar(c);
            }

            TextureRegion texReg = f.getTexRegForChar(c);


            Vector2 startPosVec = f.getCharStartPos(c);
            int regionWidth = texReg.getRegionWidth();
            x -= startPosVec.x % (float) regionWidth;
            switch (c) {
                case '\n':
                    y += (float) texReg.getRegionHeight();
                    x = 0;
                    break;
                case ' ':
                    x += f.getCharSize(c).x / 4.0F;
                    break;
                default:
                    x += f.getCharSize(c).x + startPosVec.x % (float) regionWidth + 2.0F;
            }
        }
        return textDim.set(x,y);
    }

    final String text;
    final String rawText;
    final List<TextPart> parts;

    FormatText(@NonNull String text, @NonNull List<TextPart> parts) {
        String text1;
        text1 = text;
        for (MatchResult r : FORMAT_PATTER.matcher(text).results().toList()) {
            text1 = text1.replaceAll(r.group().replace("[", "\\["), "");
        }
//        this.text = FORMAT_PATTER.matcher(text).replaceAll("");
        this.text = text1;
        this.rawText = text;
        this.parts = parts;
    }

    public static FormatText of(String text) {
        return of(text, Color.WHITE.cpy());
    }
    void drawParts(Batch batch, Viewport uiViewport, float xStart, float yStart, boolean flip){
        Vector2 cursor = new Vector2(0,0);
        List<FormatText.TextPart> parts = this.getParts();

        for (FormatText.TextPart part : parts) {
            batch.setColor(part.color());
            FontRenderer.drawText(batch,uiViewport,part.text(),xStart + cursor.x,yStart + cursor.y,flip);
            cursor.add(getLastCursorPlace(part.text(), new Vector2()));
        }


    }
    public static FormatText of(String text, Color resetColor) {
        List<TextPart> parts = new ArrayList<>();

        // Repair String if it's encoded in a broken charset
        if (text == null)
            return new FormatText("", new ArrayList<>());

        text = new String(text.getBytes());
        if (!text.startsWith(FORMAT_KEY))
            text = FORMAT_KEY + "r" + text;

        if (text.contains(FORMAT_KEY)) {
            List<MatchResult> resultList = FORMAT_VALUE.matcher(text).results().toList();

            for (MatchResult result : resultList) {
                char[] chars = new char[result.end() - result.start()];
                text.getChars(result.start(), result.end(), chars, 0);

                String segment = new String(chars);
                Matcher m = FORMAT_PATTER.matcher(segment);

                String colourFormatter = "§r";
                if (m.find()) {
                    colourFormatter = m.group();
                }

                String segmentText = segment.replaceAll(colourFormatter.replace("[", "\\["), "");
                parts.add(new TextPart(segmentText, FormatColors.toColor(colourFormatter, resetColor)));
            }
        }

        return new FormatText(text, parts);
    }

    public String getText() {
        return text;
    }

    public String getRawText() {
        return rawText;
    }

    public List<TextPart> getParts() {
        return parts;
    }

    public String strip() {
        return getText().strip();
    }

    @Override
    public String toString() {
        return getText();
    }

    public record TextPart(String text, Color color) {
        public Vector2 getDimensions(Viewport vp) {
            return FontRenderer.getTextDimensions(vp, text, new Vector2());
        }
        public float getWidth(Viewport vp) {
            return getDimensions(vp).x;
        }

        public float getHeight(Viewport vp) {
            return getDimensions(vp).y;
        }
    }

}