package org.nardogames.rattlesnake.domain.enemies;

import org.nardogames.rattlesnake.domain.IDisposable;
import org.nardogames.rattlesnake.domain.player.Player;

public interface IAmEnemy extends IDisposable {
    public boolean collidesWithSnake(Player player);
    public void notifyHitSnake();
    public void update(float delta);
    public float getX();
    public float getY();
}
