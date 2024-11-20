package ui;

import java.io.FileNotFoundException;

// the main code and runs the FitnessApp
public class Main {
    public static void main(String[] args) {

        // System.out.println("\nWelcome to the Fitness Tracker");
        // new FitnessApp();


        // uncomment to run console version from phase 2
        try {
            System.out.println("\nWelcome to the Fitness Tracker");
            new FitnessApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

        

    }
}
