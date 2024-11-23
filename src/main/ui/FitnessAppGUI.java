package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
// import java.lang.reflect.Array;

import javax.swing.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;
import java.util.List;

// Referenced from the AlarmSystem and C3-LectureLabStarter (TrafficLight)
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
// https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
public class FitnessAppGUI extends JFrame {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final String JSON_STORE = "./data/fitnessplans.json";

    private List<FitnessPlan> fitnessLog;
    private FitnessPlan currFitnessPlan;
    private Exercise currExercise;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel startUpPanel;
    private JPanel mainPanel;
    private JPanel displayPanel;
    private JPanel plansPanel;
    private JPanel modifyPanel;

    private ImageIcon startUpImage;
    private ImageIcon addExerciseImage;
    private ImageIcon removeExerciseImage;
    private ImageIcon saveLoadImage;

    /**
     * sets up button panel and display area.
     */
    public FitnessAppGUI() {

        fitnessLog = new ArrayList<>();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Fitness App Tracker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadImages();
        createStartUpPanel();
        createMainPanel();
        createModifyPanel();

        getContentPane().add(startUpPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: display the start up image for the app
    private void createStartUpPanel() {
        startUpPanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel(startUpImage);

        imageLabel.addMouseListener(new StartUpAction());

        startUpPanel.add(imageLabel, BorderLayout.CENTER);
    }

    private class StartUpAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            switchToMainPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: create and display the main menu
    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the left panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        addButton(buttonPanel, "Add Fitness Plan");
        addButton(buttonPanel, "Save Fitness Plans");
        addButton(buttonPanel, "Load Fitness Plans");

        // Create the right panel with fitness plan buttons
        plansPanel = new JPanel();
        plansPanel.setLayout(new GridLayout(0, 1));
        JLabel descriptionLabel = new JLabel("Select a fitness plan from this area", SwingConstants.CENTER);
        plansPanel.add(descriptionLabel, BorderLayout.NORTH);

        // Create the split pane and add the panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, plansPanel);
        splitPane.setDividerLocation(WIDTH / 4);

        mainPanel.add(splitPane, BorderLayout.CENTER);

    }

    // MODIFIES: this
    // EFFECTS: modify and display the edit menu after selecting a fitness plan
    private void createModifyPanel() {
        modifyPanel = new JPanel();
        modifyPanel.setLayout(new GridLayout(0, 1));

        addModifyButton(modifyPanel, "Add Exercise");
        addModifyButton(modifyPanel, "Remove Exercise");
        addModifyButton(modifyPanel, "Modify Exercise");
        addModifyButton(modifyPanel, "Add Time");
        addModifyButton(modifyPanel, "Get Summary");
        addModifyButton(modifyPanel, "Go Back To Main Menu");

        displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());

        // Create the split pane and add the panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modifyPanel, displayPanel);
        splitPane.setDividerLocation(WIDTH / 4);

        // Add the split pane to the content pane
        // Add the split pane to the modify panel
        modifyPanel = new JPanel(new BorderLayout());
        modifyPanel.add(splitPane, BorderLayout.CENTER);

    }

    // MODIFIES: this
    // EFFECTS: update the view of the display panel in the edit menu
    private void updateDisplayPanel() {
        displayPanel.removeAll();
        String[] columnNames = { "Index", "Name", "Type", "Weight (Lbs)", "Sets", "Reps" };
        List<Exercise> exercises = currFitnessPlan.getWorkouts();

        Object[][] data = new Object[exercises.size()][6];
        for (int i = 0; i < exercises.size(); i++) {
            Exercise e = exercises.get(i);
            data[i][0] = i; // Index
            data[i][1] = e.getName(); // Name
            data[i][2] = e.getType(); // MuscleRegion
            data[i][3] = e.getTargetWeight(); // Weight
            data[i][4] = e.getTargetSet(); // Sets
            data[i][5] = e.getTargetRepetition(); // Reps
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        displayPanel.revalidate();
        displayPanel.repaint();

    }

    /**
     * Adds a button to the given panel with the specified text.
     *
     * @param panel the panel to add the button to
     * @param text  the text of the button
     *              EFFECTS: allows user to interact with the edit menu with buttons
     */
    private void addModifyButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ModifyButtonClickListener());
        panel.add(button);
    }

    /**
     * EFFECTS: Action listener for button clicks in the edit menu.
     */
    private class ModifyButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Add Exercise":
                    doAddExerciseGUI();
                    break;
                case "Remove Exercise":
                    doRemoveExerciseGUI();
                    break;
                case "Modify Exercise":
                    doModifyExerciseGUI();
                    break;
                case "Add Time":
                    // Implement adding time
                    break;
                case "Get Summary":
                    // Implement getting summary
                    break;
                case "Go Back To Main Menu":
                    switchToMainPanel();
                    break;
            }
        }
    }

    /**
     * Adds a button to the given panel with the specified text.
     *
     * @param panel the panel to add the button to
     * @param text  the text of the button
     *              EFFECTS: allows user to interact with the main menu with buttons
     * 
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
     * EFFECTS: Action listener for button clicks in the main menu.
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

    /*
     * EFFECTS: Action listener for button clicks in the right panel of main menu.
     */
    private class PlanButtonClickListener implements ActionListener {
        private FitnessPlan plan;

        public PlanButtonClickListener(FitnessPlan plan) {
            this.plan = plan;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currFitnessPlan = plan;
            switchToModifyPanel();
            updateDisplayPanel();
        }
    }

    // EFFECTS: saves all fitness plan created to file
    private void saveAllFitnessPlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(fitnessLog);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Save completed", "Save", JOptionPane.INFORMATION_MESSAGE,
                    saveLoadImage);
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
            JOptionPane.showMessageDialog(this, "Load completed", "Load", JOptionPane.INFORMATION_MESSAGE,
                    saveLoadImage);
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

    // MODIFIES: this, FitnessPlan
    // EFFECTS: add an exercise to the selected fitness plan
    private void doAddExerciseGUI() {
        Exercise currExercise;

        String name = JOptionPane.showInputDialog("Enter name of Exercise");
        MuscleRegion type = selectMuscleGUI();
        int weight = Integer.parseInt(JOptionPane.showInputDialog("Enter weight (in Lbs)"));
        int set = Integer.parseInt(JOptionPane.showInputDialog("Enter number of sets"));
        int rep = Integer.parseInt(JOptionPane.showInputDialog("Enter number of repetitions"));
        int location = Integer
                .parseInt(JOptionPane.showInputDialog("Enter the order of the exercise (-1 to add to the end)"));

        currExercise = new Exercise(name, type, weight, set, rep);

        currFitnessPlan.addExercise(currExercise, location);

        updateDisplayPanel();
        JOptionPane.showMessageDialog(this, "Sucessfully Added", "Add Exercise", JOptionPane.INFORMATION_MESSAGE,
                addExerciseImage);

    }

    // MODIFIES: this
    // EFFECTS: process user input on selecting the type of exercise
    private MuscleRegion selectMuscleGUI() {
        MuscleRegion selectedMuscle = null;
        MuscleRegion[] allMuscle = MuscleRegion.values();

        JComboBox<MuscleRegion> muscleComboBox = new JComboBox<>(allMuscle);
        JOptionPane.showOptionDialog(this, muscleComboBox, "Select Muscle Region", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        int i = muscleComboBox.getSelectedIndex();
        selectedMuscle = muscleComboBox.getItemAt(i);

        return selectedMuscle;
    }

    // MODIFIES: this, FitnessPlan
    // EFFECTS: remove an exercise from the selected fitnessplan
    private void doRemoveExerciseGUI() {
        List<Exercise> currWorkouts = currFitnessPlan.getWorkouts();
        JComboBox<String> currWorkoutsOption = new JComboBox<>();

        for (Exercise e : currWorkouts) {
            currWorkoutsOption.addItem(e.getName());
        }

        JOptionPane.showOptionDialog(this, currWorkoutsOption, "Select exercise to remove",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        int i = currWorkoutsOption.getSelectedIndex();
        currFitnessPlan.removeExercise(i);

        updateDisplayPanel();
        JOptionPane.showMessageDialog(this, "Sucessfully Removed", "Remove Exercise", JOptionPane.INFORMATION_MESSAGE,
                removeExerciseImage);

    }

    // MODIFIES: this, FitnessPlan, Exercise
    // EFFECTS: modify an exercise from the selected fitnessplan
    private void doModifyExerciseGUI() {
        List<Exercise> currWorkouts = currFitnessPlan.getWorkouts();
        JComboBox<String> currWorkoutsOption = new JComboBox<>();

        for (Exercise e : currWorkouts) {
            currWorkoutsOption.addItem(e.getName());
        }

        JOptionPane.showOptionDialog(this, currWorkoutsOption, "Select exercise to remove",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        int i = currWorkoutsOption.getSelectedIndex();
        currExercise = currWorkouts.get(i);

        changeExerciseContent();
        updateDisplayPanel();
    }

    // MODIFIES: this, FitnessPlan, Exercise
    // EFFECTS: increase the difficulty of the exercise
    private void changeExerciseContent() {
        int weight = Integer.parseInt(JOptionPane.showInputDialog("Enter weight change to (in Lbs)"));
        int addSet = Integer.parseInt(JOptionPane.showInputDialog("Enter sets to add"));
        int rep = Integer.parseInt(JOptionPane.showInputDialog("Enter target repetitions"));

        currExercise.addExerciseAmount(weight, addSet, rep);

    }

    // REQUIRES: currently not in the edit menu
    // MODIFIES: this
    // EFFECTS: switches to the edit menu
    private void switchToModifyPanel() {
        getContentPane().removeAll();
        getContentPane().add(modifyPanel);
        revalidate();
        repaint();
    }

    // REQUIRES: currently not in the main menu
    // MODIFIES: this
    // EFFECTS: switches to the edit menu
    private void switchToMainPanel() {
        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: load all images
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        startUpImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "startup.jpg");
        addExerciseImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "addexercise.png");
        removeExerciseImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "removeexercise.png");
        saveLoadImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "saveload.jpg");

    }

}
