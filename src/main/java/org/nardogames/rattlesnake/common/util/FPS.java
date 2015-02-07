package org.nardogames.rattlesnake.common.util;

import java.util.ArrayList;
import java.util.List;

public class FPS {
    private static int frames;
    private static float timeElapsed;

    private static List<FPSListener> listeners = new ArrayList<>();

    public static void increment(float deltaTime) {
        timeElapsed += deltaTime;
        frames++;

        if(timeElapsed > 1000f) {
            for(FPSListener listener : listeners) {
                listener.updateFPS(frames);
            }
            timeElapsed -= 1000f;
            frames = 0;
        }
    }

    public static void addFPSListener(FPSListener listener) {
        listeners.add(listener);
    }

    public static void removeFPSListener(FPSListener listener) {
        listeners.remove(listener);
    }

}
