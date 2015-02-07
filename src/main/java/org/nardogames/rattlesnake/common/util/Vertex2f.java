package org.nardogames.rattlesnake.common.util;

public class Vertex2f
{
    private float[] values;
    public Vertex2f(float x, float y)
    {
        values = new float[] { x, y };
    }

    public float[] getValues() {
        return values;
    }

    public void translate(float x, float y) {
        values[0] += x;
        values[1] += y;
    }


}