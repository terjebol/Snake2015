package org.nardogames.rattlesnake.domain.food;

import org.nardogames.rattlesnake.domain.IAmFood;
import org.nardogames.rattlesnake.domain.IDisposable;
import org.nardogames.rattlesnake.domain.Snake;

import java.util.List;

public interface IProvideFood extends IDisposable {
    public IAmFood provideFood();

    boolean isInactive();

    public boolean shouldProvideFood();
    public List<? extends IAmFood> getCurrentFood();
    public void update(float deltaTime, Snake snake);
}
