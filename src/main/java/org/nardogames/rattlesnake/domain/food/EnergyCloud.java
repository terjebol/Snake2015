package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.common.particles.ParticleEmitterPosition;
import org.nardogames.rattlesnake.common.util.Collision;
import org.nardogames.rattlesnake.domain.player.Player;

public class EnergyCloud extends ParticleEmitterPosition implements IAmFood {

    private float collisionRadius;
    private boolean isEaten;

    protected EnergyCloud(float x, float y, float collisionRadius) {
        super(x, y);
        isEaten = false;
        this.collisionRadius = collisionRadius;
    }

    @Override
    public boolean collidesWithSnake(Player player) {
        float x = player.getX();
        float y = player.getY();
        float snakeRadius = player.getSnakeRadius();
        return Collision.circleIntersectsCircle(x, y, snakeRadius, getX(), getY(), collisionRadius);
    }

    @Override
    public float getScore() {
        return 10f;
    }
}
