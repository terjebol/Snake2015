package org.nardogames.fastmath;

public class Vector2d
{
        public double x, y;

        public Vector2d(double x, double y)
        {
            this.x = x;
            this.y = y;
        }

        public static Vector2d FromAngle(double angle)
        {
            double radian = angle * (Math.PI / 180);
            return FromRadian(radian);
        }

        public static Vector2d FromRadian(double radian)
        {
            double vx = Math.cos(radian);
            double vy = Math.sin(radian);

            double length = Math.sqrt(vx * vx + vy * vy);
            vx = vx / length;
            vy = vy / length;

            return new Vector2d(vx, vy);
        }
}
