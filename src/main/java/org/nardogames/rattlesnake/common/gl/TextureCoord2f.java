package org.nardogames.rattlesnake.common.gl;

public class TextureCoord2f {
    private float left;
    private float top;
    private float right;
    private float bottom;

    public TextureCoord2f() {
        left = 0f;
        top = 1f;
        right = 1f;
        bottom = 0f;
    }
    public TextureCoord2f left(float left) {
        this.left = left;
        return this;
    }

    public TextureCoord2f top(float top) {
        this.top = top;
        return this;
    }

    public TextureCoord2f right(float right) {
        this.right = right;
        return this;
    }

    public TextureCoord2f bottom(float bottom) {
        this.bottom = bottom;
        return this;
    }

    public float[] getValues() {
        return new float[] {
                left,bottom,
                left,top,
                right,top,
                right,top,
                right,bottom,
                left,bottom
        };
    }

    @Override
    public String toString() {
        return "TextureCoord2f{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
