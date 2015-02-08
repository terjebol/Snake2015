package org.nardogames.rattlesnake.domain.enemies;

import org.fastmath.easing.Linear;
import org.nardogames.rattlesnake.common.particles.*;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.domain.Enemy;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.Snake;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by Terje on 07.02.2015.
 */
public class FireWhip extends Enemy {

    private SinglePositionParticleEmitter particleEmitter;
    private static Random randomizer = new Random();
    private Vector2f velocity;
    private float x, y;


    public static FireWhip createNewEnemy() {
        Vector2f enemyPosition = createRandomizedPosition();
        FireWhip fireWhip = new FireWhip(enemyPosition);
        fireWhip.initializeParticleEmitter();
        return fireWhip;
    }

    private static Vector2f createRandomizedPosition() {

        float w = RattleSnake.getInstance().getDisplayWidth();
        float h = RattleSnake.getInstance().getDisplayHeight();
        float posX = (0.2f * w) + randomizer.nextFloat() * (0.6f * w);
        float posY = (0.2f * h) + randomizer.nextFloat() * (0.6f * h);
        return new Vector2f(posX, posY);
    }

    public FireWhip(Vector2f pos) {
        x = pos.getX();
        y = pos.getY();
    }

    private void initializeParticleEmitter() {
        ParticleEmitterPosition position = new ParticleEmitterPosition(x, y, new Vector2f());
        particleEmitter = new SinglePositionParticleEmitter(
                TextureUtils.getTexture("textures/sphere.png"),
                new SunFlareParticleCreator(position),
                new FireParticleUpdater(Linear.easeIn),
                new DefaultParticleBlender(), 200);
        particleEmitter.setPosition(position);
        particleEmitter.initializeParticles(50);
        ParticleSystem.globalInstance().addEmitter(particleEmitter);
    }
    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean collidesWithSnake(Snake snake) {
        return false;
    }

    @Override
    public void notifyHitSnake() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {

    }

    private static class SunFlareParticleCreator extends DefaultParticleCreator {

        private static Random randomizer = new Random();
        private Vector2f particleVector;
        private ParticleEmitterPosition position;
        private int index = 0;

        public SunFlareParticleCreator(ParticleEmitterPosition position) {
            this.position = position;
        }

        @Override
        public Particle createParticle() {
            particleVector = createRandomizedVector();
            particleVector = new Vector2f(index);
            index++;
            if(index == 360)
                index = 0;

            return super.createParticle();
        }

        private static Vector2f createRandomizedVector() {
            return new Vector2f(
                    2f * (-0.5f + randomizer.nextFloat()),
                    2f * (-0.5f + randomizer.nextFloat())).normalise().scale(0.25f);
        }

        @Override
        public float getInitialVelocityX() {
            return particleVector.getX();
        }

        @Override
        public float getInitialVelocityY() {
            return particleVector.getY();
        }

        @Override
        public float getInitialXPosition() {
            return position.getX();
        }

        @Override
        public float getInitialYPosition() {
            return position.getY();
        }

        @Override
        public int getMaximumTimeToLive() {
            return 750;
        }

        @Override
        public int getMinimumTimeToLive() {
            return 400;
        }
    }
}
