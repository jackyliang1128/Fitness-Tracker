package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

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
        addButton(buttonPanel, "Edit Fitness Plans");
        addButton(buttonPanel, "Save Fitness Plans");
        addButton(buttonPanel, "Load Fitness Plans");
        

        // Create the right panel for display
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());

        // Create the split pane and add the panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, displayPanel);
        splitPane.setDividerLocation(WIDTH/4);

        // Add the split pane to the content pane
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setVisible(true);
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
    //  * Represents action to be taken when user clicks desktop
    //  * to switch focus. (Needed for key handling.)
    //  */
    // private class DesktopFocusAction extends MouseAdapter {
    //     @Override
    //     public void mouseClicked(MouseEvent e) {
    //         FitnessAppGUI.this.requestFocusInWindow();
    //     }
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
                case "Edit Fitness Plans":
                    displayChoosePlanGUI();
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
    
}
