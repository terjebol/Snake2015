package org.nardogames.rattlesnake.domain.player;

import org.lwjgl.opengl.GL11;
import org.nardogames.rattlesnake.common.gl.TextureCoord2f;
import org.nardogames.rattlesnake.common.gl.VBO;
import org.nardogames.rattlesnake.common.particles.ParticleSystem;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.common.util.Vertex2f;
import org.nardogames.rattlesnake.common.util.VertexUtils;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.newdawn.slick.opengl.Texture;

public class Score {
    private float currentScore;
    private float scoreModifier;
    private Texture scoreTexture;
    private VBO vbo;

    public Score() {
        currentScore = 0.0f;
        scoreModifier = 1.0f;
        scoreTexture = TextureUtils.getTexture("textures/score_background.png");
        initVBO();
    }

    public float getScore() {
        return currentScore;
    }

    public float getScoreModifier() {
        return scoreModifier;
    }

    public void addScore(float score) {
        currentScore += (score * getScoreModifier());
    }

    public void incrementModifier() {
        scoreModifier += 0.1f;
    }

    public void decrementModifier() {
        scoreModifier -= 0.1f;
        if(scoreModifier < 0.5f) {
            scoreModifier = 0.5f;
        }
    }

    private void initVBO() {
        vbo = new VBO(1);

        float w = scoreTexture.getImageWidth();
        float h = scoreTexture.getImageHeight();

        Vertex2f[] vertices = VertexUtils.createDefaultQuad(
                RattleSnake.getInstance().getDisplayWidth() - (0.5f * w),
                RattleSnake.getInstance().getDisplayHeight() - (0.5f* h), w, h);


        vbo.resetData();
        for(Vertex2f vertex : vertices) {
            //System.out.println(vertex.getValues()[0]+","+vertex.getValues()[1]);
            vbo.putVertex(vertex);
            vbo.putColor( new float[] { 1f,1f,1f,1f });
        }
        TextureCoord2f txt = new TextureCoord2f();
        //txt = txt.top(1.2f).right(1.2f);
        vbo.putTextureCoords( txt );
    }

    public void render() {

        scoreTexture.bind();
        vbo.render();

    }
}
