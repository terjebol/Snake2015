package org.nardogames.rattlesnake.common.content;

public class Bounds {
    private float x, y, width, height;

    public Bounds(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getLeft()
    {
        return x - (.5f*width);
    }

    public float getRight()
    {
        return x + (.5f*width);
    }

    public float getTop()
    {
        return y + (.5f*height);
    }

    public float getBottom()
    {
        return y - (.5f*height);
    }

    public void translate(float dx, float dy) {
        x += dx;
        y += dy;
    }

    public boolean intersectsWith(Bounds otherBounds) {
        if(otherBounds.getRight() < getLeft())
            return false;
        if(otherBounds.getLeft() > getRight())
            return false;
        if(otherBounds.getTop() < getBottom())
            return false;
        if(otherBounds.getBottom() > getTop())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Bounds{x=%s, y=%s, width=%s, height=%s}", x, y, width, height);
    }
}
