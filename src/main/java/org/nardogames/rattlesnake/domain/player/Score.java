package org.nardogames.rattlesnake.domain.player;

public class Score {
    private float currentScore;
    private float scoreModifier;

    public Score() {
        currentScore = 0.0f;
        scoreModifier = 1.0f;
    }

    public float getScore() {
        return currentScore;
    }

    public float getScoreModifier() {
        return scoreModifier;
    }

    public void addScore(float score) {
        currentScore += (score * getScoreModifier());
    }

    public void incrementModifier() {
        scoreModifier += 0.1f;
    }

    public void decrementModifier() {
        scoreModifier -= 0.1f;
        if(scoreModifier < 0.5f) {
            scoreModifier = 0.5f;
        }
    }
}
