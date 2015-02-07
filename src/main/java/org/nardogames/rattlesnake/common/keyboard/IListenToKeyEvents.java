package org.nardogames.rattlesnake.common.keyboard;

public interface IListenToKeyEvents {
    public boolean listensToKey(int key);

    void notifyKeyPushed(int key);
    void notifyKeyIsDown(int key, float deltaTime);

    void notifyKeyReleased(int key, long timeDown);
}
