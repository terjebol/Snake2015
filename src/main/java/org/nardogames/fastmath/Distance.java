package org.nardogames.fastmath;

public class Distance {

    public static double getDistance(PointD from, PointD to)
    {
        return Math.sqrt( getSquared(from, to) );
    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt( getSquared(x1,y1,x2,y2) );
    }
    public static double getDistance(float x1, float y1, float x2, float y2) {
        return Math.sqrt( getSquared(x1,y1,x2,y2) );
    }

    public static double getSquared(PointD from, PointD to) {
        return 
                ((to.getX() - from.getX()) * (to.getX() - from.getX())) +
                ((to.getY() - from.getY()) * (to.getY() - from.getY()));
    }
    
    public static double getSquared(int x1, int y1, int x2, int y2) {
        return
                ((x2 - x1) * (x2 - x1)) +
                ((y2 - y1) * (y2 - y1));
    }

    public static double getSquared(float x1, float y1, float x2, float y2) {
        return
                ((x2 - x1) * (x2 - x1)) +
                        ((y2 - y1) * (y2 - y1));
    }

}
