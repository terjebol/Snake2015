package org.nardogames.rattlesnake.common.particles;

import org.lwjgl.opengl.GL11;
import org.nardogames.fastmath.easing.IEasingMethod;

/**
 * Created by Magnus on 12.02.2015.
 */
public class BlackMatterParticles {

    private static class BlackMatterBlender implements IBlendParticles {

        @Override
        public void applyBlendFunc() {
            GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR); //Special blend mode for Black Smoke!
        }
    }

    private static class BlackMatterUpdater extends DefaultParticleUpdater {

        public BlackMatterUpdater(IEasingMethod easingMethod) {
            super(easingMethod);
        }

        @Override
        public float[] getColor(float factorOfTtl) {
            float factor = getEasingMethod().execute(factorOfTtl, 0f, 1f, 1f);
            float result = Math.max(0.5f - factor, 0f);
            return new float[]{ result, result, result };
        }
    }
}
