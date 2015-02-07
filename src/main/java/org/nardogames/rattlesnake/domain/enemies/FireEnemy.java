package org.nardogames.rattlesnake.domain.enemies;

import org.fastmath.easing.IEasingMethod;
import org.fastmath.easing.Linear;
import org.nardogames.rattlesnake.common.particles.*;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.domain.Enemy;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.Snake;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by Terje on 28.01.2015.
 */
public class FireEnemy extends Enemy {

    private SinglePositionParticleEmitter particleEmitter;
    private static Random randomizer = new Random();
    private Vector2f velocity;
    private float x, y;


    public static FireEnemy createNewEnemy() {
        Vector2f enemyVector = createRandomizedVector();
        Vector2f enemyPosition = createRandomizedPosition(enemyVector);
        FireEnemy fireEnemy = new FireEnemy(enemyPosition, enemyVector);
        fireEnemy.initializeParticleEmitter();
        return fireEnemy;
    }

    private static Vector2f createRandomizedVector() {
        return new Vector2f(
                2f * (-0.5f + randomizer.nextFloat()),
                2f * (-0.5f + randomizer.nextFloat())).normalise().scale(0.5f);
    }

    private static Vector2f createRandomizedPosition(Vector2f velocity) {

        Vector2f normalisedCopy = velocity.copy().normalise();

        float w = RattleSnake.getInstance().getDisplayWidth();
        float h = RattleSnake.getInstance().getDisplayHeight();
        float posX = (0.5f*w) - w * normalisedCopy.getX();
        float posY = (0.5f*h) - h * normalisedCopy.getY();
        if(posX < 0f) posX = 0f;
        if(posX >= w) posX = w;
        if(posY < 0f) posY = 0f;
        if(posY >= h) posY = h;
        return new Vector2f(posX, posY);
    }

    public FireEnemy(Vector2f pos, Vector2f velocity) {
        this.velocity = velocity;
        x = pos.getX();
        y = pos.getY();

    }

    private void initializeParticleEmitter() {
        ParticleEmitterPosition position = new ParticleEmitterPosition(x, y, velocity);
        particleEmitter = new SinglePositionParticleEmitter(
                TextureUtils.getTexture("textures/sphere.png"),
                new FireParticleCreator(position),
                new FireParticleUpdater(Linear.easeIn),
                new DefaultParticleBlender(), 200);
        particleEmitter.setPosition(position);
        particleEmitter.initializeParticles(0);
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

    public void update(float deltaTime) {
        particleEmitter.update(deltaTime);
    }

    @Override
    public void dispose() {
        ParticleSystem.globalInstance().removeEmitter( particleEmitter );
    }


    private static class FireParticleCreator extends DefaultParticleCreator {
        private ParticleEmitterPosition position;

        public FireParticleCreator(ParticleEmitterPosition position) {

            this.position = position;
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
        public float getInitialVelocityX() {
            return position.getVelocity().negate().scale(0.25f).getX();
        }

        @Override
        public float getInitialVelocityY() {
            return position.getVelocity().negate().scale(0.25f).getY();
        }
    }

    private static class FireParticleUpdater extends DefaultParticleUpdater {

        public FireParticleUpdater(IEasingMethod easingMethod) {
            super(easingMethod);
        }

        @Override
        public float[] getColor(float factorOfTtl) {
            float factor = getEasingMethod().execute(factorOfTtl, 0f, 1f, 1f);
            return new float[] {
                    1f, Math.max(0.5f - factor, 0f), 0f //0.5f - (0.5f * factor)
            };
        }
    }
}
