package org.nardogames.rattlesnake.domain;

public interface IAmEntity extends IDisposable, ICollideWithSnake {
    public float getX();
    public float getY();
}
