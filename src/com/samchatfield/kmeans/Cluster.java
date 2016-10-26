package com.samchatfield.kmeans;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sam on 04/05/2016.
 */
public class Cluster {

    private Point centroid;
    private Set<Point> points;
    private int index;

    public Cluster(Point initialCentroid, int index) {
        centroid = initialCentroid;
        points = new HashSet<>();
        this.index = index;
    }

    public void computeCentroid() {
        float x = 0.0f;
        float y = 0.0f;

        for (Point p : points) {
            x += p.x();
            y += p.y();
        }

        x /= points.size();
        y /= points.size();

        centroid = new Point(x, y);
    }

    public Point centroid() {
        return centroid;
    }

    public Set<Point> points() {
        return points;
    }

    public int index() {
        return index;
    }

}
