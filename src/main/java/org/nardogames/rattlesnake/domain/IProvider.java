package org.nardogames.rattlesnake.domain;

import org.nardogames.rattlesnake.domain.player.Player;

import java.util.List;

public interface IProvider<T> extends IDisposable{
    public T createEntity();
    boolean isInactive();
    public boolean shouldProvideMore();
    public List<? extends T> getCurrentEntities();
    public void update(float deltaTime, Player player);

}
