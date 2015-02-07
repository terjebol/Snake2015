package org.nardogames.rattlesnake.domain;

public abstract class Enemy implements IDisposable {
    public abstract boolean isActive();

    public abstract boolean collidesWithSnake(Snake snake);
    public abstract void notifyHitSnake();

    public abstract void update(float delta);
}
