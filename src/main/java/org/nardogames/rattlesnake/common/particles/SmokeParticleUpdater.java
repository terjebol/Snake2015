package org.nardogames.rattlesnake.common.particles;


import org.fastmath.easing.IEasingMethod;

public class SmokeParticleUpdater extends AbstractParticleUpdater {
    private IEasingMethod easingMethod;


    public SmokeParticleUpdater(IEasingMethod easingMethod) {
        this.easingMethod = easingMethod;
    }

    @Override
    public int getMaxParticlesAddedOnUpdate() {
        return 1;
    }

    @Override
    public float getAlpha(float factorOfTtl) {
        float alpha =  1.5f - easingMethod.execute(factorOfTtl, 0f, 1f, 1f);
        if(alpha > 1f) alpha = 1f;
        return alpha;
    }

    @Override
    public float[] getColor(float factorOfTtl) {
        return new float[0];
    }

    @Override
    public float[] getSize(float factorOfTtl, float initialWidth, float initialHeight) {
        return new float[0];
    }

    @Override
    public boolean hasConstantSize() {
        return true;
    }

    @Override
    public boolean hasConstantColor() {
        return true;
    }

    @Override
    public boolean hasConstantVelocity() {
        return false;
    }

    @Override
    public float getVelocityX(float factorOfTtl, float initialVelocityX) {
        float velocity = initialVelocityX - (initialVelocityX * (1.4f * easingMethod.execute(factorOfTtl, 0f, 1f, 1f)));
        if(initialVelocityX > 0f && velocity < 0f) return 0f;
        if(initialVelocityX <= 0f && velocity >= 0f) return 0f;
        return velocity;
    }

    @Override
    public float getVelocityY(float factorOfTtl, float initialVelocityY) {
        return initialVelocityY + 0.05f * easingMethod.execute(factorOfTtl, 0f, 1f, 1f);
    }
}
