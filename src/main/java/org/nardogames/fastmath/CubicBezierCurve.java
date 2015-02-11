package org.nardogames.fastmath;


public class CubicBezierCurve extends Path {

    public CubicBezierCurve(
            double fromx, double fromy,
            double throughx1, double throughy1,
            double throughx2, double throughy2,
            double tox, double toy,
            int points) {

        super(createCurve(fromx, fromy, throughx1, throughy1, throughx2, throughy2, tox, toy, points));
    }

    public static PointD[] createCurve(double fromx, double fromy,
            double throughx1, double throughy1,
            double throughx2, double throughy2,
            double tox, double toy,
            int points)
    {
        PointD[] pds = new PointD[points + 1];

        double t;
        double dv = points;

        for (int i = 0; i <= points; i++)
        {
            t = i / dv;

            pds[i] = new PointD
                    (
                            (1 - t) * (1 - t) * (1 - t) * fromx + (3 * (1 - t) * (1 - t) * t * throughx1) + (3 * (1 - t) * t * t * throughx2) + (t * t * t * tox),
                            (1 - t) * (1 - t) * (1 - t) * fromy + (3 * (1 - t) * (1 - t) * t * throughy1) + (3 * (1 - t) * t * t * throughy2) + (t * t * t * toy)
                            );
        }
        return pds;
    }

    public static PointD[] createCurve(PointD from, PointD through1, PointD through2, PointD to, int points)
    {
        return createCurve(from.getX(), from.getY(), through1.getX(), through1.getY(), through2.getX(), through2.getY(), to.getX(), to.getY(), points);
    }
}
