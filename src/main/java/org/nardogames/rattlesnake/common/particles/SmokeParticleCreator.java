package org.nardogames.rattlesnake.common.particles;

import org.nardogames.rattlesnake.common.content.Bounds;

public class SmokeParticleCreator extends AbstractParticleCreator {

    private Bounds bounds;

    public SmokeParticleCreator(Bounds bounds) {
        this.bounds = bounds;
    }
    @Override
    public boolean hasVariableStartingPosition() {
        return false;
    }

    @Override
    public float getVariableStartingPositionRadius() {
        return 0;
    }

    @Override
    public int getMinimumTimeToLive() {
        return 3000;
    }

    @Override
    public int getMaximumTimeToLive() {
        return 18000;
    }

    @Override
    public float getInitialParticleWidth() {
        return 5;
    }

    @Override
    public float getInitialParticleHeight() {
        return 5;
    }

    @Override
    public float getInitialXPosition() {
        return bounds.getX();
    }

    @Override
    public float getInitialYPosition() {
        return bounds.getY();
    }

    @Override
    public float getInitialVelocityX() {
        return -0.08f;
    }

    @Override
    public float getInitialVelocityY() {
        return 0;
    }

    @Override
    public float[] getInitialRGBA() {
        return new float[] { .7f, .7f, .7f, 1f };
    }
}
