package com.samchatfield.kmeans;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import java.util.List;

/**
 * Created by Sam on 04/05/2016.
 */
public class PointView extends JPanel implements Observer {

    private final KMeansModel model;
    private final Color[] colors = { Color.BLUE, Color.RED, Color.ORANGE, Color.GREEN, Color.WHITE, Color.YELLOW, Color.CYAN };

    public PointView(KMeansModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        float sf = Math.min(getWidth(), getHeight()) / (float) model.getPoints().size();

        List<Cluster> clusters = model.getClusters();

        for (Cluster c : clusters) {
            // Draw centroid
            g2d.setColor(colors[c.index()]);
            Rectangle2D centroid = new Rectangle2D.Float(c.centroid().x() * sf, c.centroid().y() * sf, 7, 7);
            g2d.fill(centroid);

            // Draw points
            for (Point p : c.points()) {
                g2d.fillOval((int) (p.x() * sf), (int) (p.y() * sf), 5, 5);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}
