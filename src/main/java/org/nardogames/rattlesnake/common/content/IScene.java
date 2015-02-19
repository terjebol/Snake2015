package org.nardogames.rattlesnake.common.content;

public interface IScene extends IUpdate{
    public boolean isInitialized();
    public void initialize();
    public void render();
}
