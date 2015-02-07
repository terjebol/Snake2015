package org.nardogames.rattlesnake.common.particles;

/**
 * Created by tebaol on 01.09.2014.
 */
public class DefaultParticleCreator extends AbstractParticleCreator {

    public DefaultParticleCreator() {
        super();
//        setVariableStartingPosition(true);
//        setVariableStartingPositionRadius(5f);
//        setParticleWidth(32f);
//        setParticleHeight(32f);
    }

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
        return 0f; //-0.1f;
    }

    @Override
    public float getInitialVelocityY() {
        return 0f; //0.01f * getNewParticlePositionY();
    }

    @Override
    public float[] getInitialRGBA() {
        return new float[] { 1f,1f,1f,1f };
    }

}
