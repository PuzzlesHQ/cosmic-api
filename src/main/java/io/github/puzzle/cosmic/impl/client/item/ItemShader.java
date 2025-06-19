package io.github.puzzle.cosmic.impl.client.item;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import finalforeach.cosmicreach.rendering.shaders.GameShader;
import finalforeach.cosmicreach.util.Identifier;

import static io.github.puzzle.cosmic.CosmicConstants.MOD_ID;

public class ItemShader extends GameShader {

    public static ItemShader DEFAULT_ITEM_SHADER;
    private static final VertexAttribute posAttrib = VertexAttribute.Position();
    private static final VertexAttribute texCoordsAttrib = VertexAttribute.TexCoords(0);
    private static final VertexAttribute normalAttrib = VertexAttribute.Normal();


    public ItemShader(Identifier vertexShader, Identifier fragmentShader) {
        super(vertexShader, fragmentShader);
        this.allVertexAttributesObj = new VertexAttributes(posAttrib, texCoordsAttrib, normalAttrib);
    }

    public static void initItemShader() {
        DEFAULT_ITEM_SHADER = new ItemShader(
                Identifier.of(MOD_ID,"item_shader.vert.glsl"),
                Identifier.of(MOD_ID,"item_shader.frag.glsl"));
    }

    public void bind(Camera worldCamera) {
        super.bind(worldCamera);
        this.shader.setUniformMatrix("u_projViewTrans", worldCamera.combined);
    }
}
