package org.nardogames.rattlesnake.common.particles;

import org.newdawn.slick.geom.Vector2f;

public class ParticleEmitterPosition {
    private float x, y;
    private Vector2f velocity;

    public ParticleEmitterPosition(float x, float y) {
        this(x, y, new Vector2f(0f,0f));
    }
    public ParticleEmitterPosition(float x, float y, Vector2f velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void update(float deltaTime) {
        x += velocity.x * deltaTime;
        y += velocity.y * deltaTime;
    }

    public final static ParticleEmitterPosition empty = new ParticleEmitterPosition(0f,0f, new Vector2f());
}
