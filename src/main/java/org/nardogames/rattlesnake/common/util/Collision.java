package org.nardogames.rattlesnake.common.util;

import org.fastmath.Distance;

/**
 * Created by Terje on 26.01.2015.
 */
public class Collision {

    public static boolean circleContainsPoint(float circleX, float circleY, float radius, float pointX, float pointY) {
        float dx = Math.abs(pointX - circleX);
        float dy = Math.abs(pointY - circleY);

        if(dx > radius) {
            return false;
        }
        if(dy > radius) {
            return false;
        }
        if(dx + dy <= radius) {
            return true;
        }
        if((dx*dx) + (dy*dy) <= (radius*radius)) {
            return true;
        }
        return false;
    }

    public static boolean circleIntersectsCircle(float circleX1, float circleY1, float radius1, float circleX2, float circleY2, float radius2) {
        return Distance.getDistance(circleX1, circleY1, circleX2, circleY2) < (radius1 + radius2);
    }
}
