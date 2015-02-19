package org.nardogames.rattlesnake.common.particles;

/**
 * Created by tebaol on 01.09.2014.
 */
public class Particle {
    public float x;
    public float y;
    protected float vx;
    protected float vy;
    protected float initialWidth;
    protected float initialHeight;
    public float width;
    public float height;
    protected float red;
    protected float green;
    protected float blue;
    public float alpha;
    protected float originalTtl;
    protected float currentTtl;
    public boolean active;

    @Override
    public String toString() {
        return "Particle{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                ", width=" + width +
                ", height=" + height +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                ", originalTtl=" + originalTtl +
                ", currentTtl=" + currentTtl +
                ", active=" + active +
                '}';
    }
}
