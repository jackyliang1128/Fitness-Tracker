package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;
import java.util.List;

// Referenced from the AlarmSystem
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class FitnessAppGUI extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/fitnessplans.json";

    private List<FitnessPlan> fitnessLog;
    private FitnessPlan currFitnessPlan;
    private Exercise currExercise;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel displayPanel;
    private JPanel plansPanel;

    /**
     * Constructor sets up button panel and display area.
     */
    public FitnessAppGUI() {

        fitnessLog = new ArrayList<>();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Fitness App Tracker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create the left panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        addButton(buttonPanel, "Add Fitness Plan");
        addButton(buttonPanel, "Save Fitness Plans");
        addButton(buttonPanel, "Load Fitness Plans");

        // Create the right panel for display
        displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());

        // Create the right panel with fitness plan buttons

        plansPanel = new JPanel();
        plansPanel.setLayout(new GridLayout(1, 1));

        // Create the split pane and add the panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, plansPanel);
        splitPane.setDividerLocation(WIDTH / 4);

        // Add the split pane to the content pane
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setVisible(true);
        loadImages();
    }

    /**
     * Adds a button to the given panel with the specified text.
     *
     * @param panel the panel to add the button to
     * @param text  the text of the button
     */
    private void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ButtonClickListener());
        panel.add(button);
    }

    // /**
    // * Represents action to be taken when user clicks desktop
    // * to switch focus. (Needed for key handling.)
    // */
    // private class DesktopFocusAction extends MouseAdapter {
    // @Override
    // public void mouseClicked(MouseEvent e) {
    // FitnessAppGUI.this.requestFocusInWindow();
    // }
    // }

    /**
     * Action listener for button clicks.
     */
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Add Fitness Plan":
                    createFitnessPlan();
                    break;
                case "Save Fitness Plans":
                    saveAllFitnessPlan();
                    break;
                case "Load Fitness Plans":
                    loadAllFitnessPlan();
                    break;
            }
        }
    }

    private class PlanButtonClickListener implements ActionListener {
        private FitnessPlan plan;
    
        public PlanButtonClickListener(FitnessPlan plan) {
            this.plan = plan;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            currFitnessPlan = plan;
        }
    }
    

    // EFFECTS: saves all fitness plan created to file
    private void saveAllFitnessPlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(fitnessLog);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Save completed", "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE, "Save Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads all fitness plan from file
    private void loadAllFitnessPlan() {
        try {
            fitnessLog = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Load completed", "Load", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE, "Load Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        displayFitnessPlans();
    }

    // MODIFIES: this
    // EFFECTS: create a fitness plan and add it to the fitness plan log
    private void createFitnessPlan() {
        String name = JOptionPane.showInputDialog(this, "Enter name for your fitness plan:", "Create Fitness Plan",
                JOptionPane.PLAIN_MESSAGE);
        FitnessPlan currFitnessPlan = new FitnessPlan(name);
        fitnessLog.add(currFitnessPlan);
        JOptionPane.showMessageDialog(this, "Fitness plan added successfully", "Create Fitness Plan",
                JOptionPane.INFORMATION_MESSAGE);
        displayFitnessPlans();

    }

    // MODIFIES: this
    // EFFECTS: displays all fitness plans in the display panel as buttons
    private void displayFitnessPlans() {
        plansPanel.removeAll();
        plansPanel.setLayout(new GridLayout(0, 1)); // Set the layout to a grid with one column

        for (FitnessPlan plan : fitnessLog) {
            JButton planButton = new JButton(plan.getPlanName());
            planButton.addActionListener(new PlanButtonClickListener(plan));
            plansPanel.add(planButton);
        }

        plansPanel.revalidate();
        plansPanel.repaint();
    }

    private void loadImages() {

    }

}
