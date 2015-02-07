package org.nardogames.rattlesnake.common.keyboard;

public abstract class AbstractKeyEventListener implements IListenToKeyEvents {
    @Override
    public abstract boolean listensToKey(int key);

    @Override
    public void notifyKeyPushed(int key) {
    }

    @Override
    public void notifyKeyReleased(int key, long timeDown) {
    }

    @Override
    public void notifyKeyIsDown(int key, float deltaTime) {

    }
}
