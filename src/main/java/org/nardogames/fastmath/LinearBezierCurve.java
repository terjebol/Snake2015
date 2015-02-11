package org.nardogames.fastmath;

public class LinearBezierCurve extends Path
{
    public LinearBezierCurve(double fromx, double fromy, double tox, double toy, int points) {
        super(createCurve(fromx, fromy, tox, toy, points));        
    }

    public static PointD[] createCurve(double fromx, double fromy, double tox, double toy, int points)
    {
        PointD[] pds = new PointD[points + 1];

        double t;
        double dv = points;
        for (int i = 0; i <= points; i++)
        {
            t = (i / dv);
            pds[i] = new PointD
                    (
                        ((1 - t) * fromx) + (t * tox),
                        ((1 - t) * fromy) + (t * toy)
                    );
        }
        return pds;
    }

    public static PointD[] createCurve(PointD from, PointD to, int points)
    {
        return createCurve(from.getX(), from.getY(), to.getX(), to.getY(), points);
    }
}
