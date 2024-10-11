package ui;

import model.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Fitness Tracker application
public class FitnessApp {
    private List<FitnessPlan> fitnessLog;
    private Scanner input;
    private FitnessPlan currFitnessPlan;

    // EFFECTS: runs the application
    public FitnessApp() {
        fitnessLog = new ArrayList<>();
        runFitness();
    }

    // MODIFIES: this
    // EFFECTS: process user input from the "home page"
    // *NOTE*: references TellerApp from lecture
    private void runFitness() {
        boolean appRunning = true;
        String command = null;

        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");

        while (appRunning) {
            displayInitialMenu();
            command = input.next();
            // command = command.toLowerCase();

            if (command.equals("q")) {
                appRunning = false;
            } else {
                processCommand(command);
            }

        }
        System.out.println("Thank you for using the Fitness Tracker!");

    }

    // MODIFIES: this
    // EFFECTS: processes user command of the homepage
    private void processCommand(String command) {
        if (command.equals("c")) {
            createFitnessPlan();
        } else if (command.equals("m")) {
            displayChoosePlanMenu();
        } else {
            System.out.println("Selection not valid...");
        }

    }
    
    // EFFECTS: display the homepage menu
    private void displayInitialMenu() {
        System.out.println("\nPlease select from the following options:");
        System.out.println("\tc -> create a new fitness plan");
        System.out.println("\tm -> go to an existing fitness plan");
        System.out.println("\tq -> quit the application");

    }

    // MODIFIES: this
    // EFFECTS: create a fitness plan and add it to the fitness plan log
    private void createFitnessPlan() {
        FitnessPlan currFitnessPlan;

        System.out.println("\nChoose a name for your fitness plan");
        currFitnessPlan = new FitnessPlan(input.next());
        fitnessLog.add(currFitnessPlan);
    }

    // MODIFIES: this
    // EFFEFCTS: display the menu to choose current fitness plan
    private void displayChoosePlanMenu() {

        if (fitnessLog.isEmpty()) {
            System.out.println("\nThere are no fitness plans! Go create one!");
        } else {
            System.out.println("\nSelect the fitness plan to modify:");
            int counter = 0;
            for (FitnessPlan i : fitnessLog) {
                System.out.println(counter + " -> " + i.getPlanName());
                counter++;
            }
            currFitnessPlan = fitnessLog.get(input.nextInt());
            displayModifyMenu();
            processModifyCommand(input.next());

        }

    }

    // EFFECTS: display the menu that modify the current fitness plan
    private void displayModifyMenu() {
        System.out.println("What would you like to do to the plan?");
        System.out.println("\ta -> add an exercise");
        System.out.println("\tr -> remove an exercise");
        System.out.println("\tv -> view the remanining exercise you still have to complete");
        System.out.println("\tt -> add time to the current plan");
        System.out.println("\ts -> get a summary of your current plan");

    }

    // MODIFIES: this
    // EFFECTS: process the command from user on the display the modify the current fitness plan
    private void processModifyCommand(String command) {
        if (command.equals("a")) {
            doAddExercise();
        } else if (command.equals("r")) {
            doRemoveExercise();
        } else if (command.equals("v")) {
            doViewExerciseLeft();
        } else if (command.equals("t")) {
            doAddTime();
        } else if (command.equals("s")) {
            doSummary();
        } else {
            System.out.println("Selection not valid...");
        }

    }
    
    // MODIFIES: this, FitnessPlan
    // EFFECTS: add an exercise to the selected fitness plan
    private void doAddExercise() {
        Exercise currExercise;

        System.out.println("exercise name?");
        String name = input.next();
        System.out.println(("muscle targeted?"));
        MuscleRegion type = selectMuscle();
        System.out.println(("weight?"));
        int weight = input.nextInt();
        System.out.println("how mant sets?");
        int set = input.nextInt();
        System.out.println("how many repetitions per set?");
        int rep = input.nextInt();
        System.out.println("where would you like to add it?");
        int location = input.nextInt();

        currExercise = new Exercise(name, type, weight, set, rep);

        currFitnessPlan.addExercise(currExercise, location);

    }

    // MODIFIES: this
    // EFFECTS: process user input on selecting the type of exercise
    private MuscleRegion selectMuscle() {
        MuscleRegion selectedMuscle;
        MuscleRegion[] allMuscle = MuscleRegion.values();
        // int counter = 0;

        for (MuscleRegion m : allMuscle) {
            // System.out.println(counter + " -> " + m);
            System.out.println(m);
        }

        selectedMuscle = MuscleRegion.valueOf(input.next());
        return selectedMuscle;
    }

    // MODIFIES: this, FitnessPlan
    // EFFECTS: remove an exercise from the selected fitnessplan
    private void doRemoveExercise() {
        List<Exercise> currWorkouts = currFitnessPlan.getWorkouts();
        int counter = 0;

        for (Exercise e : currWorkouts) {
            System.out.println(counter + " -> " + e.getName());
        }

        System.out.println("exercise to remove?");
        currFitnessPlan.removeExercise(input.nextInt());

    }

    // MODIFIES: this
    // EFFECTS: shows all the exercises in the selected plan and the remaining exercises
    private void doViewExerciseLeft() {
        List<Exercise> allWorkouts = currFitnessPlan.getWorkouts();
        List<Exercise> remaningWorkouts = currFitnessPlan.viewRemaningExercise();

        System.out.println("This is all the exercises in the plan!");
        for (Exercise w : allWorkouts) {
            System.out.println(w.getName());
        }

        System.out.println("This is the exercises you still have to do!");
        for (Exercise w : remaningWorkouts) {
            System.out.println(w.getName());
        }

    }

    // MODIFIES: FitnessPlan
    // EFFECTS: add time to the fitness plan
    private void doAddTime() {
        double timeToAdd;

        System.out.println("how much time would you like to add to your plan (minutes):");

        timeToAdd = input.nextDouble();
        currFitnessPlan.incrementExerciseTime(timeToAdd);

    }

    // EFFECTS: get a summary of the fitness plan stats
    private void doSummary() {
        int totalWeight = currFitnessPlan.calculateTotalVolume();
        double totalTime = currFitnessPlan.getDuration();

        System.out.println(
                "you have spent " + totalTime + " minutes at the gym, lifting " + totalWeight + " pounds in total!");

    }

}
