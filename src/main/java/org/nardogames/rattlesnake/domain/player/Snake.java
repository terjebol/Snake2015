package org.nardogames.rattlesnake.domain.player;

import com.sun.deploy.security.DeployClientAuthCertStore;
import org.nardogames.fastmath.easing.Linear;
import org.nardogames.rattlesnake.common.particles.*;
import org.nardogames.rattlesnake.common.util.Collision;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.enemies.IAmEnemy;
import org.newdawn.slick.geom.Vector2f;

import java.util.List;

public class Snake {
    private ParticleEmitterPosition emitterPosition;
    SinglePositionParticleEmitter particleEmitter;
    private final static float SNAKE_DEFAULT_SPEED_SCALER = 0.15f;
    private final static float SNAKE_DEFAULT_SIZE = 5f;
    private final static float SNAKE_MINIMUM_SIZE = 4f;
    private final static float SNAKE_MAXIMUM_SIZE = 20f;
    private float snakeSize;

    public Snake() {
        setSnakeSize(SNAKE_DEFAULT_SIZE);
        initializeParticleEmitter();
    }

    private void initializeParticleEmitter() {
        float emitterx = 20f; //RattleSnake.getInstance().getDisplayWidth() * 0.5f;
        float emittery = RattleSnake.getInstance().getDisplayHeight() * 0.5f;
        Vector2f snakeVector = new Vector2f(0.1f, 0f).normalise().scale(SNAKE_DEFAULT_SPEED_SCALER);
        emitterPosition = new ParticleEmitterPosition(emitterx,emittery, snakeVector);
        ParticleSet particleSet = new ParticleSet(
                new SnakeParticleCreator(this),
                new FoodSet.FoodParticleUpdater(Linear.easeIn),
                DefaultParticleSet.get().getBlender());
        particleEmitter = new SinglePositionParticleEmitter(
                TextureUtils.getTexture("textures/sphere.png"),
                particleSet, 200);
        particleEmitter.setPosition(emitterPosition);
        particleEmitter.initializeParticles(10);
        ParticleSystem.globalInstance().addEmitter(particleEmitter);
    }

    protected float getX() {
        return emitterPosition.getX();
    }

    protected float getY() {
        return emitterPosition.getY();
    }

    private void setSnakeSize(float sz) {
        snakeSize = sz;
    }

    private float getSnakeSize() {
        return snakeSize;
    }

    protected float getSnakeRadius() {
        return snakeSize * 4f;
    }

    protected boolean containsPoint(float x, float y) {

        float radius = getSnakeRadius();
        Vector2f normalised = emitterPosition.getVelocity().copy().normalise();
        float ix = getX() + (normalised.getX()* (radius+1) );
        float iy = getY() + (normalised.getY()* (radius+1) );
        return Collision.circleContainsPoint(ix, iy, radius, x, y);
    }

    protected void update(float deltaTime) {
        particleEmitter.update(deltaTime);
        List<Particle> particles = particleEmitter.getParticleList();
        for(Particle particle : particles) {
            if(particle.active) {
                if (containsPoint(particle.x, particle.y)) {
                    //System.out.println("Hit particle!!");
                }
            }
        }

        flipSnakePositionIfNecessary();
    }

    private void shrinkSnake() {
        setSnakeSize( getSnakeSize() - 0.1f);
        if(getSnakeSize() < SNAKE_MINIMUM_SIZE) {
            setSnakeSize( SNAKE_MINIMUM_SIZE );
            snakeDies();
        }
    }

    protected void growSnake(float score) {
        setSnakeSize( getSnakeSize() + (0.1f * score));
        if(getSnakeSize() > SNAKE_MAXIMUM_SIZE) {
            setSnakeSize(SNAKE_MAXIMUM_SIZE);
        }
    }

    private void snakeDies() {

    }

    private void flipSnakePositionIfNecessary() {

        Vector2f snakeVector = emitterPosition.getVelocity();
        float xPos = emitterPosition.getX();
        float yPos = emitterPosition.getY();
        float displayWidth = RattleSnake.getInstance().getDisplayWidth();
        float displayHeight = RattleSnake.getInstance().getDisplayHeight();

        if(snakeVector.getX() < 0f && xPos < 0f) {
            emitterPosition.setX(displayWidth);
        }
        if(snakeVector.getX() > 0f && xPos > displayWidth) {
            emitterPosition.setX(0);
        }

        if(snakeVector.getY() < 0f && yPos < 0f) {
            emitterPosition.setY(displayHeight);
        }
        if(snakeVector.getY() > 0f && yPos > displayHeight) {
            emitterPosition.setY(0f);
        }
    }

    protected void rotate(double theta) {
        particleEmitter.getPosition().getVelocity().add(theta).normalise().scale(SNAKE_DEFAULT_SPEED_SCALER);
    }

    private static class SnakeParticleCreator extends DefaultParticleSet.DefaultParticleCreator {
        private Snake snake;

        public SnakeParticleCreator(Snake snake) {
            this.snake = snake;
        }

        @Override
        public float getInitialXPosition() {
            return snake.getX();
        }

        @Override
        public float getInitialYPosition() {
            return snake.getY();
        }

        @Override
        public float getInitialParticleWidth() {
            return snake.getSnakeSize() * 8f;
        }

        @Override
        public float getInitialParticleHeight() {
            return snake.getSnakeSize() * 8f;
        }

        @Override
        public int getMaximumTimeToLive() {
            return Math.round(snake.getSnakeSize() * 500f);
        }

        @Override
        public int getMinimumTimeToLive() {
            return Math.round(snake.getSnakeSize() * 100f);
        }
    }
}
