package org.nardogames.rattlesnake.domain;

public interface IEntity extends IDisposable, ICollideWithSnake {
    public float getX();
    public float getY();
}
