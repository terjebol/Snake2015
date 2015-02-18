package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.domain.ICollideWithSnake;

public interface IAmFood extends ICollideWithSnake {
    public float getScore();
}
