package com.samchatfield.kmeans;

import java.util.*;

/**
 * Created by Sam on 04/05/2016.
 */
public class KMeansModel extends Observable {

    private int iteration;
    private List<Cluster> clusters;
    private Set<Point> points;

    public KMeansModel() {
        reset(4, 100);
    }

    public void reset(int means, int pointsNum) {
        iteration = 0;
        clusters = new ArrayList<>(means);

        Random rand = new Random();

        // Generate initial means
        for (int i = 0; i < means; i++) {
            float x = rand.nextFloat() * 100.0f;
            float y = rand.nextFloat() * 100.0f;
            Cluster cluster = new Cluster(new Point(x, y), i);
            clusters.add(cluster);
        }

        points = new HashSet<>();

        for (int i = 0; i < pointsNum; i++) {
            int x = (int) (rand.nextFloat() * pointsNum);
            int y = (int) (rand.nextFloat() * pointsNum);
            points.add(new Point(x, y));
        }

        step();
    }

    public void step() {
        iteration++;
        for (Cluster c : clusters) {
            c.points().clear();
        }

        for (Point p : points) {
            Cluster nearest = nearest(p);
            nearest.points().add(p);
        }

        clusters.forEach(Cluster::computeCentroid);

        setChanged();
        notifyObservers();
    }

    private Cluster nearest(Point p) {
        Cluster nearest = clusters.get(0);
        float nearestDist = Point.eucDist(nearest.centroid(), p);

        for (int i = 1; i < clusters.size(); i++) {
            float currentDist = Point.eucDist(clusters.get(i).centroid(), p);

            if (currentDist < nearestDist) {
                nearestDist = currentDist;
                nearest = clusters.get(i);
            }
        }
        return nearest;
    }

    public int getIteration() {
        return iteration;
    }

    public Set<Point> getPoints() {
        return points;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }
}
