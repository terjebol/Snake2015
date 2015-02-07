package org.nardogames.rattlesnake.domain;

public class Score {
    private double currentScore;
    private double scoreModifier;

    public Score() {
        currentScore = 0.0;
        scoreModifier = 1.0;
    }

    public double getScore() {
        return currentScore;
    }

    public double getScoreModifier() {
        return scoreModifier;
    }

    public void addScore(double score) {
        currentScore += (score * getScoreModifier());
    }

    public void incrementModifier() {
        scoreModifier += 0.1;
    }

    public void decrementModifier() {
        scoreModifier -= 0.1;
        if(scoreModifier < 0.5) {
            scoreModifier = 0.5;
        }
    }
}
