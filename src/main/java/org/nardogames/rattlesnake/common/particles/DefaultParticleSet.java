package org.nardogames.rattlesnake.common.particles;

import org.lwjgl.opengl.GL11;
import org.nardogames.fastmath.easing.IEasingMethod;
import org.nardogames.fastmath.easing.Linear;

public class DefaultParticleSet extends ParticleSet {

    private static DefaultParticleSet defaultInstance = null;

    public static ParticleSet get() {
        if (defaultInstance == null) {
            defaultInstance = new DefaultParticleSet();
        }
        return defaultInstance;
    }

    public DefaultParticleSet() {
        super(new DefaultParticleCreator(), new DefaultParticleUpdater(Linear.easeIn), new DefaultParticleBlender());
    }

    public static class DefaultParticleCreator extends AbstractParticleCreator {

        @Override
        public boolean hasVariableStartingPosition() {
            return false;
        }

        @Override
        public float getVariableStartingPositionRadius() {
            return 5f;
        }

        @Override
        public int getMinimumTimeToLive() {
            return 2000;
        }

        @Override
        public int getMaximumTimeToLive() {
            return 5000;
        }

        @Override
        public float getInitialParticleWidth() {
            return 128f;
        }

        @Override
        public float getInitialParticleHeight() {
            return 128f;
        }

        @Override
        public float getInitialXPosition() {
            return 0;
        }

        @Override
        public float getInitialYPosition() {
            return 0;
        }

        @Override
        public float getInitialVelocityX() {
            return 0f;
        }

        @Override
        public float getInitialVelocityY() {
            return 0f;
        }

        @Override
        public float[] getInitialRGBA() {
            return new float[]{1f, 1f, 1f, 1f};
        }

    }

    public static class DefaultParticleUpdater extends AbstractParticleUpdater {
        protected IEasingMethod easingMethod;

        public DefaultParticleUpdater(IEasingMethod easingMethod) {
            this.easingMethod = easingMethod;
        }

        @Override
        public int getMaxParticlesAddedOnUpdate() {
            return 1;
        }

        @Override
        public float getAlpha(float factorOfTtl) {
            return 1f - easingMethod.execute(factorOfTtl, 0f, 1f, 1f);
        }

        @Override
        public float[] getColor(float factorOfTtl) {
            return new float[]{
                    easingMethod.execute(factorOfTtl, 0f, 1f, 1f),
                    easingMethod.execute(factorOfTtl, 0f, 1f, 1f),
                    easingMethod.execute(factorOfTtl, 0f, 1f, 1f)
            };
        }

        @Override
        public float[] getSize(float factorOfTtl, float initialWidth, float initialHeight) {
            // linjær reduksjon i størrelse etterhvert som levetiden til partiklen går mot slutten.
            return new float[]{(1f - factorOfTtl) * initialWidth, (1f - factorOfTtl) * initialHeight};
        }


        @Override
        public boolean hasConstantSize() {
            return false;
        }

        @Override
        public boolean hasConstantColor() {
            return false;
        }

        @Override
        public boolean hasConstantVelocity() {
            return true;
        }

        @Override
        public float getVelocityX(float factorOfTtl, float initialVelocityX) {
            return initialVelocityX;
        }

        @Override
        public float getVelocityY(float factorOfTtl, float initialVelocityY) {
            return initialVelocityY;
        }
    }

    public static class DefaultParticleBlender implements IBlendParticles {
        @Override
        public void applyBlendFunc() {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        }
    }

}
