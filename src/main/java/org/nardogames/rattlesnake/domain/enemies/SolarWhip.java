package org.nardogames.rattlesnake.domain.enemies;

import org.nardogames.fastmath.easing.Linear;
import org.nardogames.rattlesnake.common.particles.*;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.domain.IAmEntity;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.player.Player;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by Terje on 07.02.2015.
 */
public class SolarWhip implements IAmEntity {

    private SinglePositionParticleEmitter particleEmitter;
    private ParticleEmitterPosition position;
    private static Random randomizer = new Random();


    public static SolarWhip create() {
        Vector2f enemyPosition = createRandomizedPosition();
        SolarWhip fireWhip = new SolarWhip(enemyPosition);
        fireWhip.initializeParticleEmitter();
        return fireWhip;
    }

    private static Vector2f createRandomizedPosition() {

        float w = RattleSnake.getInstance().getDisplayWidth();
        float h = RattleSnake.getInstance().getDisplayHeight();
        float posX = (0.2f * w) + (randomizer.nextFloat() * (0.6f * w));
        float posY = (0.2f * h) + (randomizer.nextFloat() * (0.6f * h));
        return new Vector2f(posX, posY);
    }

    public SolarWhip(Vector2f pos) {
        position = new ParticleEmitterPosition(pos.getX(), pos.getY(), new Vector2f());
    }

    private void initializeParticleEmitter() {

        particleEmitter = new SinglePositionParticleEmitter(
                TextureUtils.getTexture("textures/sphere.png"),
                new SunFlareParticleCreator(position),
                new FireParticleUpdater(Linear.easeIn),
                DefaultParticleSet.get().getBlender(), 200);
        particleEmitter.setPosition(position);
        particleEmitter.initializeParticles(50);
        ParticleSystem.globalInstance().addEmitter(particleEmitter);
    }


    @Override
    public boolean collidesWithSnake(Player player) {
        return false;
    }

    @Override
    public void notifyCollidedWithSnake(Player player) {

    }

    @Override
    public boolean isRemovedAfterCollision() {
        return false;
    }

    @Override
    public float getX() {
        return position.getX();
    }

    @Override
    public float getY() {
        return position.getY();
    }

    @Override
    public void dispose() {

    }


    private static class SunFlareParticleCreator extends DefaultParticleSet.DefaultParticleCreator {

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
