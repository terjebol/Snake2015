package org.nardogames.rattlesnake.common.keyboard;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class KeyboardController {

    private long[] keyStatuses = new long[Keyboard.KEYBOARD_SIZE];
    private List<IListenToKeyEvents> listeners = new ArrayList<>();

    public KeyboardController() {
        Keyboard.enableRepeatEvents(true);
    }

    public void addListener(IListenToKeyEvents listener) {
        listeners.add(listener);
    }

    public void pushKey(int key) {
        keyStatuses[key] = Sys.getTime();
        for(IListenToKeyEvents listener : listeners) {
            if(listener.listensToKey(key))
                listener.notifyKeyPushed(key);
        }
    }

    public void releaseKey(int key) {
        long now = Sys.getTime();
        for(IListenToKeyEvents listener : listeners) {
            if(listener.listensToKey(key))
                listener.notifyKeyReleased(key, now - keyStatuses[key]);
        }
        keyStatuses[key] = 0;
    }

    private void keyIsStillDown(int key, float deltaTime) {
        for(IListenToKeyEvents listener : listeners) {
            if(listener.listensToKey(key))
                listener.notifyKeyIsDown(key, deltaTime);
        }
    }

    public void update(float deltaTime) {

        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            keyIsStillDown(Keyboard.KEY_LEFT, deltaTime);
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            keyIsStillDown(Keyboard.KEY_RIGHT, deltaTime);

        while(Keyboard.next()) {
            int key = Keyboard.getEventKey();

//            if(Keyboard.isRepeatEvent() || Keyboard.isKeyDown(key)) {
//                keyIsStillDown(key, deltaTime);
//            }

            if(Keyboard.getEventKeyState()) {
                if(keyStatuses[key] == 0) {
                    pushKey(key);
                }
            }
            else if(keyStatuses[key] > 0) {
                releaseKey(key);
            }
        }
    }
}
