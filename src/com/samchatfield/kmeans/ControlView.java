package com.samchatfield.kmeans;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Sam on 04/05/2016.
 */
public class ControlView extends JPanel implements Observer {

    private final KMeansModel model;
    private final JLabel iterationLabel;

    public ControlView(KMeansModel model) {
        this.model = model;

        JPanel fieldsPanel = new JPanel();

        JLabel meansLabel = new JLabel("Means:");
        JTextField meansField = new JTextField();
        meansField.setColumns(10);

        JLabel pointsLabel = new JLabel("Points:");
        JTextField pointsField = new JTextField();
        pointsField.setColumns(10);

        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> reset(meansField.getText(), pointsField.getText()));

        fieldsPanel.add(meansLabel);
        fieldsPanel.add(meansField);
        fieldsPanel.add(pointsLabel);
        fieldsPanel.add(pointsField);
        fieldsPanel.add(reset);


        JPanel iterationPanel = new JPanel();

        iterationLabel = new JLabel("0");

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(e -> model.step());

        iterationPanel.add(iterationLabel);
        iterationPanel.add(stepButton);


        setLayout(new GridLayout(2, 1));
        add(fieldsPanel);
        add(iterationPanel);
    }

    private void reset(String meansText, String pointsText) {
        try {
            int means = Integer.parseInt(meansText);
            int points = Integer.parseInt(pointsText);
            model.reset(means, points);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.getParent(), "Must be an integer amount of means and points");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        iterationLabel.setText("" + model.getIteration());
    }

}
