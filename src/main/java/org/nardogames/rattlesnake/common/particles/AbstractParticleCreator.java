package org.nardogames.rattlesnake.common.particles;

import java.util.Random;

/**
 * Created by tebaol on 01.09.2014.
 */
public abstract class AbstractParticleCreator {
    private Random randomizer;

    protected AbstractParticleCreator() {
        randomizer = new Random();
    }

    public abstract boolean hasVariableStartingPosition();
    public abstract float getVariableStartingPositionRadius();
    public abstract int getMinimumTimeToLive();
    public abstract int getMaximumTimeToLive();
    public abstract float getInitialParticleWidth();
    public abstract float getInitialParticleHeight();
    public abstract float getInitialXPosition();
    public abstract float getInitialYPosition();
    public abstract float getInitialVelocityX();
    public abstract float getInitialVelocityY();
    public abstract float[] getInitialRGBA();
    public boolean doesRespawnParticles() {
        return true;
    }

    public Particle createParticle() {
        Particle p = new Particle();
        p.x = getNewParticlePositionX();
        p.y = getNewParticlePositionY();
        p.vx = getInitialVelocityX();
        p.vy = getInitialVelocityY();
        p.initialWidth = getInitialParticleWidth();
        p.initialHeight = getInitialParticleHeight();
        p.width = p.initialWidth;
        p.height = p.initialHeight;
        float[] initialColor = getInitialRGBA();
        p.red = initialColor[0];
        p.green = initialColor[1];
        p.blue = initialColor[2];
        p.alpha = initialColor[3];
        p.originalTtl = getMinimumTimeToLive() + randomizer.nextInt( getMaximumTimeToLive() - getMinimumTimeToLive() );
        p.active = true;
        return p;
    }

    protected float getNewParticlePositionX() {
        if(hasVariableStartingPosition()) {
            return getInitialXPosition() + (getJitter() * getVariableStartingPositionRadius());
        }
        return getInitialXPosition();
    }

    protected float getNewParticlePositionY() {
        if(hasVariableStartingPosition()) {
            return getInitialYPosition() + (getJitter() * getVariableStartingPositionRadius());
        }
        return getInitialYPosition();
    }

    /**
     * A function that returns a random number between -1f and 1f, allowing for random distribution
     * of particle positioning.
     * @return -1f -> 1f
     */
    private float getJitter() {
        return 2f * (-0.5f + randomizer.nextFloat());
    }
}
