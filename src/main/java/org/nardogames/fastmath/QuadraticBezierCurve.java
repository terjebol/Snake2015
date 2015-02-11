package org.nardogames.fastmath;

public class QuadraticBezierCurve extends Path
{

    public QuadraticBezierCurve(
            double fromx,
            double fromy,
            double throughx,
            double throughy,
            double tox,
            double toy,
            int points) {
        super(createCurve(fromx, fromy, throughx, throughy, tox, toy, points));
    }

    public static PointD[] createCurve(double fromx, double fromy, double throughx, double throughy, double tox, double toy, int points)
    {
        PointD[] pds = new PointD[points + 1];

        double t;
        double dv = points;

        for (int i = 0; i <= points; i++)
        {
            t = (i / dv);
            pds[i] = new PointD
                    (
                        ((1 - t) * (1 - t)) * fromx + (2 * (1 - t) * t * throughx) + (t * t) * tox,
                        ((1 - t) * (1 - t)) * fromy + (2 * (1 - t) * t * throughy) + (t * t) * toy
                    );
        }
        return pds;
    }

    public static PointD[] createCurve(PointD from, PointD through, PointD to, int points)
    {
        return createCurve(from.getX(), from.getY(), through.getX(), through.getY(), to.getX(), to.getY(), points);
    }
}
