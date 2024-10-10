package model;

import java.util.List;
import java.util.ArrayList;

// Represents a fitness plan that has a name and contains a list of exercises for the session
// to accomplish in the order that it is added
public class FitnessPlan {

    private List<Exercise> workouts;
    private String planName;
    private double duration;
    private Exercise currentExercise;

    public FitnessPlan(String name) {
        planName = name;
        duration = 0;
        workouts = new ArrayList<>();

    }

    /*
     * REQUIRES: workouts.size() > 0
     * MODIFIES:
     * EFFECTS: select the current exercise to be completed
     */
    public void beginExercise (){

    }


    /*
     * REQUIRES: 
     * MODIFIES:
     * EFFECTS: add an exercise to the desired location in the fitness plan
     */
    public void addExercise(Exercise e, int location){

    }

    /*
     * REQUIRES: workouts.size() > 0 and exercise is not completed
     * MODIFIES:
     * EFFECTS: remove an existing exercise in the fitness plan
     */
    public void removeExercise(int location){

    }

    /*
     * REQUIRES: workouts.size() > 0
     * MODIFIES:
     * EFFECTS: returns a list of exercise that is not completed, null if all exercise completed
     */
    public List<Exercise> viewRemaningExercise(){
        return null;
    }

    /*
     * REQUIRES: currentExercise is completed
     * MODIFIES:
     * EFFECTS: increment the time for each exercise completed
     */
    public void incrementExerciseTime(){

    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS: return total running time of fitness plan
     */
    public double calculateTotalTime(){
        return 0;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS: return the total volume performed in the fitness plan
     */
    public double calculateTotalVolume(){
        return 0;
    }


}
