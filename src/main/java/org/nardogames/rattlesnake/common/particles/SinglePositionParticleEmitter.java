package org.nardogames.rattlesnake.common.particles;

import org.lwjgl.opengl.GL11;
import org.nardogames.rattlesnake.common.gl.VBO;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

/**
 * Created by Terje on 24.01.2015.
 */
public class SinglePositionParticleEmitter extends AbstractParticleEmitter{

    public SinglePositionParticleEmitter(Texture particleTexture, AbstractParticleCreator particlePlacer, AbstractParticleUpdater particleUpdater, IBlendParticles particleBlender, int maximumParticles) {
        super(particleTexture, particlePlacer, particleUpdater, particleBlender, maximumParticles);

        float emitterx = (particleTexture.getImageWidth() * 0.5f) + RattleSnake.getInstance().getDisplayWidth() * 0.5f;
        float emittery = (particleTexture.getImageHeight() * 0.5f) + RattleSnake.getInstance().getDisplayHeight() * 0.5f;
        setPosition(emitterx, emittery, new Vector2f());

    }

    public void setPosition( float x, float y, Vector2f vector) {
        setPosition( new ParticleEmitterPosition(x, y, vector));
    }

    public void setPosition(ParticleEmitterPosition position) {
        positionList.clear();
        positionList.add(position);
    }

    public ParticleEmitterPosition getPosition() { return positionList.get(0); }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    protected void onRenderBuffer(VBO buffer) {
        GL11.glPushMatrix();
        //GL11.glTranslatef(getPosition().getX(), getPosition().getY(), 0f);
        buffer.render();
        GL11.glPopMatrix();
//        for (ParticleEmitterPosition position : positionList) {
//
//
//            GL11.glPushMatrix();
//            buffer.render(position.getX(), position.getY());
//            GL11.glPopMatrix();
//        }
    }

}
