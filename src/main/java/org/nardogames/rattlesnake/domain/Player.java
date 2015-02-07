package org.nardogames.rattlesnake.domain;

import org.lwjgl.input.Keyboard;
import org.nardogames.rattlesnake.common.keyboard.AbstractKeyEventListener;

public class Player extends AbstractKeyEventListener {
    private Snake snake;
    private Score score;

    public Player() {
        snake = new Snake();
        score = new Score();
        RattleSnake.getInstance().getKeyboardController().addListener(this);
    }

    public void update(float deltaTime) {
        snake.update(deltaTime);
    }

    @Override
    public boolean listensToKey(int key) {
        return isRightArrow(key) || isLeftArrow(key);
    }

    public void notifyKeyIsDown(int key, float deltaTime) {
        double rotation = 0.5 * deltaTime;
        if(isRightArrow(key)) {
            snake.rotate( -rotation);
            return;
        }
        if(isLeftArrow(key)) {
            snake.rotate( rotation);
        }
    }

    private boolean isRightArrow(int key) {
        return key == Keyboard.KEY_RIGHT;
    }

    public boolean isLeftArrow(int key) {
        return key == Keyboard.KEY_LEFT;
    }

    public Snake getSnake() {
        return snake;
    }

    public Score getScore() {
        return score;
    }
}
