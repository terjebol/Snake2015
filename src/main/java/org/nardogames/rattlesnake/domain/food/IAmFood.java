package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.domain.player.Player;

public interface IAmFood {
    public boolean collidesWithSnake(Player player);
    public float getScore();
}
