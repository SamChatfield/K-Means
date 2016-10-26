package com.samchatfield.kmeans;

/**
 * Created by Sam on 04/05/2016.
 */
public class Point {

    private final float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Float.compare(point.x, x) != 0) return false;
        return Float.compare(point.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    public static float eucDist(Point a, Point b) {
        return (float) (Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2));
    }

}
