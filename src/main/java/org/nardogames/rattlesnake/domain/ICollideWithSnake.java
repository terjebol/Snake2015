package org.nardogames.rattlesnake.domain;

import org.nardogames.rattlesnake.domain.player.Player;

public interface ICollideWithSnake {
    public boolean collidesWithSnake(Player player);
    public void notifyCollidedWithSnake(Player player);
    public boolean isRemovedAfterCollision();

}
