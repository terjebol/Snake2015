package org.nardogames.rattlesnake.domain.player;

import org.lwjgl.input.Keyboard;
import org.nardogames.rattlesnake.common.keyboard.AbstractKeyEventListener;
import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.food.IAmFood;

public class Player extends AbstractKeyEventListener {
    private Snake snake;
    private Score score;
    private PlayerKeys playerKeys;

    public Player() {
        snake = new Snake();
        score = new Score();
        playerKeys = new PlayerKeys(this);
        RattleSnake.getInstance().getKeyboardController().addListener(playerKeys);
    }

    public void update(float deltaTime) {
        snake.update(deltaTime);

        System.out.println("Current Score: "+getScore().getScore());
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

    public float getX() {
        return snake.getX();
    }

    public float getY() {
        return snake.getY();
    }

    public float getSnakeRadius() {
        return snake.getSnakeRadius();
    }

    public void eatFood(IAmFood food) {
        float foodScore = food.getScore();
        snake.growSnake(foodScore);
        score.addScore(foodScore);
    }
}
