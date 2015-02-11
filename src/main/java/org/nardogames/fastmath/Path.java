package org.nardogames.fastmath;

import java.awt.Point;

public class Path {

    private PointD[] _points;
    private Point[] _intPoints;
    private boolean intsAreDirty;

    public Path() {
        this(new PointD[] { });
    }

    public Path(PointD[] points)
    {
        _points = points;
        _intPoints = null;
        intsAreDirty = true;
    }

    public PointD[] getPoints()
    {
        return _points;
    }

    public PointD getPoint(int index)
    {
        return _points[index];
    }

    public Point[] getPointsAsInts()
    {
        if (intsAreDirty)
        {
            int sz = _points.length;
            _intPoints = new Point[sz];
            for (int i = 0; i < sz; i++)
                _intPoints[i] = new Point((int)Math.round(_points[i].getX()), (int)Math.round(_points[i].getY()));
            intsAreDirty = false;
        }
        return _intPoints;
    }

    public boolean containsPoint(Point p)
    {
        Point[] points = getPointsAsInts();
        int sz = points.length;
        for (int i = 0; i < sz; i++)
        {
            if (points[i].x == p.x && points[i].y == p.y)
                return true;
        }
        return false;
    }

    public boolean containsPoint(PointD p) {
        for (int i = 0; i < _points.length; i++) {
            if (_points[i].getX() == p.getX() && _points[i].getY() == p.getY())
                return true;
        }
        return false;
    }

    public int getPathLength()
    {
        return _points.length;
    }

    /** Appends a path to this path, adding all the points from Path p
     *  to this one.
     * @param p
     * @return this
     */
     public Path append(Path p)
    {
        // Get the points from the path we are appending to this one.
         PointD[] otherPoints = p.getPoints();
         // Check if there actually are any points.
         if (otherPoints.length > 0)
         {
             // Determine if the first point on the path we are appending matches the last point
             // of this Path
             boolean endsMeet = otherPoints[0].equals(_points[_points.length - 1]);
             // Set the length of the combined paths
             int newLength = otherPoints.length + _points.length;
             // If the ends meet, we do not have to add the same point twice.
             if (endsMeet)
                 newLength--;

             // Create the array to hold the combined paths
             PointD[] newPoints = new PointD[newLength];

             // Copy data
             System.arraycopy(_points,  0,  newPoints, 0, _points.length);
             if (endsMeet)
                 System.arraycopy(otherPoints, 1, newPoints, _points.length, otherPoints.length - 1);
             else
                 System.arraycopy(otherPoints, 0, newPoints, _points.length, otherPoints.length);

             _points = newPoints;
             // Mark the int array as dirty
             intsAreDirty = true;
         }
         return this;
    }


     /** Combines two paths, forming one path consisting of points from both paths.
      * @param p1 
      * @param p2
      * @return A path with points from p1, appended with points from p2
      */
     public static Path combine(Path p1, Path p2)
     {
         if(p1 != null)
             return p1.append(p2);
         return p2;
     }
}
