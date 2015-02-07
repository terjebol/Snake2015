package org.nardogames.rattlesnake.domain;

public interface IAmFood {
    public boolean collidesWithSnake(Snake snake);
    public void notifyEaten();
    public boolean isEaten();
    public double getScore();
}
