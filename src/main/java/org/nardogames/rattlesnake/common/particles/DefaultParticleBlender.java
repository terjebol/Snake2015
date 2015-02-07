package org.nardogames.rattlesnake.common.particles;

import org.lwjgl.opengl.GL11;

public class DefaultParticleBlender implements IBlendParticles {
    @Override
    public void applyBlendFunc() {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    }
}
