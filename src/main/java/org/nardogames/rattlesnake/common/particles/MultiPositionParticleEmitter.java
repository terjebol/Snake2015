package org.nardogames.rattlesnake.common.particles;

import org.lwjgl.opengl.GL11;
import org.nardogames.rattlesnake.common.gl.VBO;
import org.newdawn.slick.opengl.Texture;

import java.util.List;
import java.util.Random;

/**
 * Created by Terje on 26.01.2015.
 */
public class MultiPositionParticleEmitter extends AbstractParticleEmitter {

    public MultiPositionParticleEmitter(Texture particleTexture, AbstractParticleCreator particlePlacer, AbstractParticleUpdater particleUpdater, IBlendParticles particleBlender, int maximumParticles) {
        super(particleTexture, particlePlacer, particleUpdater, particleBlender, maximumParticles);
    }

    public void setPositions(List<ParticleEmitterPosition> positions) {
        positionList = positions;
    }

    public void clearPositions() {
        positionList.clear();
    }
    public void addPosition(ParticleEmitterPosition position) {
        positionList.add(position);
    }

    @Override
    protected void onRenderBuffer(VBO buffer) {
        int i = 0;
        for (ParticleEmitterPosition position : positionList) {
            i++;
            GL11.glPushMatrix();
            GL11.glTranslatef(position.getX(), position.getY(), 0f);
            GL11.glRotatef(360f / i, 0f, 0f, 1f);
            buffer.render();
            GL11.glPopMatrix();
        }
    }
}
