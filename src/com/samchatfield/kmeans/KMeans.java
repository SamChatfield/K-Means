package com.samchatfield.kmeans;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sam on 04/05/2016.
 */
public class KMeans extends JPanel {

    public KMeans() {
        setPreferredSize(new Dimension(800, 800));

        KMeansModel model = new KMeansModel();
        PointView pointView = new PointView(model);
        ControlView controlView = new ControlView(model);

        model.addObserver(pointView);
        model.addObserver(controlView);

        setLayout(new BorderLayout());
        add(pointView, BorderLayout.CENTER);
        add(controlView, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("K-Means Clustering");
        f.setSize(900, 900);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.add(new KMeans());
        f.pack();
        f.setVisible(true);
    }

}
