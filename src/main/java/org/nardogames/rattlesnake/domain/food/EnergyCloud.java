package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.common.particles.ParticleEmitterPosition;
import org.nardogames.rattlesnake.common.util.Collision;
import org.nardogames.rattlesnake.domain.IAmFood;
import org.nardogames.rattlesnake.domain.Snake;

public class EnergyCloud extends ParticleEmitterPosition implements IAmFood {

    private float collisionRadius;
    private boolean isEaten;

    protected EnergyCloud(float x, float y, float collisionRadius) {
        super(x, y);
        isEaten = false;
        this.collisionRadius = collisionRadius;
    }

    @Override
    public boolean collidesWithSnake(Snake snake) {
        float x = snake.getX();
        float y = snake.getY();
        float snakeRadius = snake.getSnakeRadius();
        return Collision.circleIntersectsCircle(x, y, snakeRadius, getX(), getY(), collisionRadius);
    }

    @Override
    public void notifyEaten() {
        isEaten = true;
    }

    @Override
    public boolean isEaten() {
        return isEaten;
    }

    @Override
    public double getScore() {
        return 50.0;
    }
}
