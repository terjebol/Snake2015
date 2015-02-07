package org.nardogames.rattlesnake.common.util;

public class LoopTime {
    public double lastTime;

    public LoopTime() {
        lastTime = ms();
    }

    public double getDeltaTime() {
        double curr = ms();
        double delta = curr - lastTime;
        lastTime = curr;
        return delta;
    }

    private double ms() {
        return System.nanoTime() * 1e-6;
    }
}
