package org.nardogames.rattlesnake.domain.player;

import org.lwjgl.input.Keyboard;
import org.nardogames.rattlesnake.common.keyboard.AbstractKeyEventListener;

public class PlayerKeys extends AbstractKeyEventListener {
    private Player player;

    public PlayerKeys(Player player) {
        this.player = player;

    }

    @Override
    public boolean listensToKey(int key) {
        return isRightArrow(key) || isLeftArrow(key);
    }

    public void notifyKeyIsDown(int key, float deltaTime) {
        double rotation = 0.5 * deltaTime;
        if(isRightArrow(key)) {
            player.getSnake().rotate( -rotation);
            return;
        }
        if(isLeftArrow(key)) {
            player.getSnake().rotate( rotation);
        }
    }

    private boolean isRightArrow(int key) {
        return key == Keyboard.KEY_RIGHT;
    }

    public boolean isLeftArrow(int key) {
        return key == Keyboard.KEY_LEFT;
    }
}
