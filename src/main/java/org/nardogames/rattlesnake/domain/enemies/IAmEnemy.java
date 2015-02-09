package org.nardogames.rattlesnake.domain.enemies;

import org.nardogames.rattlesnake.domain.IDisposable;
import org.nardogames.rattlesnake.domain.Snake;

public interface IAmEnemy extends IDisposable {
    public boolean isActive();
    public boolean collidesWithSnake(Snake snake);
    public void notifyHitSnake();
    public void update(float delta);
    public float getX();
    public float getY();
}
