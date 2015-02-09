package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.domain.Snake;

public interface IAmFood {
    public boolean collidesWithSnake(Snake snake);
    public void notifyEaten();
    public boolean isEaten();
    public double getScore();
}
