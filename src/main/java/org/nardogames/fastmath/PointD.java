package org.nardogames.fastmath;

public class PointD {
    private double _x;
    private double _y;
    
    public PointD(double x, double y) {
        _x = x;
        _y = y;
    }
    
    public double getX() { return _x; }
    public double getY() { return _y; }
    
 
    /** Gets the point between two points. Assumes "to" param is larger than "from".
     * @param from
     * @param to
     * @return new PointD - the middlepoint between the two.
     */
    public static PointD getPointBetween(PointD from, PointD to) {
        return new PointD(
                ((to.getX() - from.getX()) * 0.5) + from.getX(),
                ((to.getY() - from.getY()) * 0.5) + from.getY());
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PointD) {
            PointD other = (PointD)obj;
            return other.getX() == _x && other.getY() == _y;
        }
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return (int) (31 + _x * _y);
    }

}
