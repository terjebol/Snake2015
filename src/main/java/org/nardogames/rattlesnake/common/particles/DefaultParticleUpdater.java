package org.nardogames.rattlesnake.common.particles;


import org.fastmath.easing.IEasingMethod;

public class DefaultParticleUpdater extends AbstractParticleUpdater {

    private IEasingMethod easingMethod;

    public DefaultParticleUpdater(IEasingMethod easingMethod) {
        this.easingMethod = easingMethod;
    }

    public IEasingMethod getEasingMethod() {
        return easingMethod;
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
        // bare en eksempelsvis fargeovergang ...
        return new float[] {
           easingMethod.execute(factorOfTtl, 0f, 1f, 1f),
           0.25f * easingMethod.execute(factorOfTtl, 0f, 1f, 1f),
           1f - easingMethod.execute(factorOfTtl, 0f, 1f, 1f)
        };
    }

    @Override
    public float[] getSize(float factorOfTtl, float initialWidth, float initialHeight) {
        // linjær reduksjon i størrelse etterhvert som levetiden til partiklen går mot slutten.
        return new float[] { (1f-factorOfTtl) * initialWidth, (1f-factorOfTtl)*initialHeight };
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
