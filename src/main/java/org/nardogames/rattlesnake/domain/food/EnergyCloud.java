package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.common.particles.ParticleEmitterPosition;
import org.nardogames.rattlesnake.common.util.Collision;
import org.nardogames.rattlesnake.domain.player.Player;

public class EnergyCloud extends ParticleEmitterPosition implements IAmFood {

    private float collisionRadius;

    protected EnergyCloud(float x, float y, float collisionRadius) {
        super(x, y);
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
    public void notifyCollidedWithSnake(Player player) {
        player.eatFood(this);
    }

    @Override
    public boolean isRemovedAfterCollision() {
        return true;
    }

    @Override
    public float getScore() {
        return 10f;
    }
}
