package ui;

import model.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FitnessApp {
    private List<FitnessPlan> fitnessLog;
    private Scanner input;
    private FitnessPlan currFitnessPlan;

    public FitnessApp() {
        fitnessLog = new ArrayList<>();
        runFitness();
    }

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

    private void processCommand(String command) {
        if (command.equals("c")) {
            createFitnessPlan();
        } else if (command.equals("m")) {
            displayChoosePlanMenu();
        } else {
            System.out.println("Selection not valid...");
        }

    }

    private void displayInitialMenu() {
        System.out.println("\nWelcome to the Fitness Tracker");
        System.out.println("\nPlease select from the following options:");
        System.out.println("\tc -> create a new fitness plan");
        System.out.println("\tm -> go to an existing fitness plan");
        System.out.println("\tq -> quit the application");

    }

    private void createFitnessPlan() {
        FitnessPlan currFitnessPlan;

        System.out.println("\nChoose a name for your fitness plan");
        currFitnessPlan = new FitnessPlan(input.next());
        fitnessLog.add(currFitnessPlan);
    }

    private void displayChoosePlanMenu() {
        if (fitnessLog.isEmpty()) {
            System.out.println("\nThere are no fitness plans! Go create one!");
        } else {
            System.out.println("\nSelect the fitness plan to modify:");
            int counter = 0;
            for (FitnessPlan i : fitnessLog) {
                System.out.println(counter + " -> " + i.getPlanName());
            }
            currFitnessPlan = fitnessLog.get(input.nextInt());
            displayModifyMenu();
        }

    }

    private void displayModifyMenu() {
        System.out.println("What would you like to do to the plan?");
        System.out.println("\ta -> add an exercise");
        System.out.println("\tr -> remove an exercise");
        System.out.println("\tv -> view the remanining exercise you still have to complete");
        System.out.println("\tt -> add time to the current plan");
        System.out.println("\ts -> get a summary of your current plan");

    }

}
