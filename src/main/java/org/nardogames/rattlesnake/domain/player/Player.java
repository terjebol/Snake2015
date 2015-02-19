package org.nardogames.rattlesnake.domain.player;

import org.nardogames.rattlesnake.domain.RattleSnake;
import org.nardogames.rattlesnake.domain.enemies.IAmEnemy;
import org.nardogames.rattlesnake.domain.food.IAmFood;

public class Player {
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

//        System.out.println("Current Score: "+getScore().getScore());
    }

    public Snake getSnake() {
        return snake;
    }

    public Score getScore() {
        return score;
    }

    public boolean isInvulnerable() {
        return snake.isInvulnerable();
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

    public void hitByEnemy(IAmEnemy enemy) {
        snake.hitByEnemy(enemy);
        if(snake.isAlive()) {
            snake.makeInvulnerable(2000f);
            System.out.println("Made invulnerable!");
        }

    }
}
