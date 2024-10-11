package model;

import java.util.List;
import java.util.ArrayList;

// Represents an exercise having a name, type, weight, set, and repetition
// Exercises are 'completed' when all sets have been accomplished
//
public class Exercise {

    private String name;
    private MuscleRegion type;
    private double targetWeight; // weight in pounds
    private int targetSet;
    private int targetRepetition;

    private boolean isComplete;
    private int currentSet;
    private List<Integer> repDone;
    // private double duration; // time in minutes

    /*
     * REQUIRES: exerciseName has a non-zero length
     * weight > 0, set > 0, rep > 0
     * EFFECTS: instantiate an exercise with a name, type, weight, set to
     * accomplish, rep per set, that is not completed
     * 
     */
    public Exercise(String exerciseName, MuscleRegion exerciseType, double weight, int set, int rep) {
        name = exerciseName;
        type = exerciseType;
        targetWeight = weight;
        targetSet = set;
        targetRepetition = rep;

        isComplete = false;
        currentSet = 0;
        repDone = new ArrayList<>();

    }

    /*
     * REQUIRES: isComplete = false
     * MODIFIES: this
     * EFFECTS: complete one set of the exercise and track the repetitions done
     * then checks if exercise is completed
     */
    public void doExercise() {
        currentSet++;
        repDone.add(targetRepetition);
        isComplete = isExerciseCompleted();
    }

    /*
     * REQUIRES: isComplete = true, weight > 0, set > 0, rep > 0
     * MODIFIES: this
     * EFFECTS: add more sets and repetitions at new weight when the exercise is
     * completed
     */
    public void addExerciseAmount(double weight, int set, int rep) {
        targetSet += set;
        targetWeight = weight;
        targetRepetition = rep;

        isComplete = isExerciseCompleted();
    }

    /*
     * REQUIRES: isComplete = false, set <= targetSet - currentSet
     * MODIFIES: this
     * EFFECTS: reduce amount of set left in the execise
     */
    public void reduceExerciseAmount(int set) {
        targetSet -= set;

        isComplete = isExerciseCompleted();
    }

    /*
     * EFFECTS: check whether the exercise has been completed
     */
    public boolean isExerciseCompleted() {
        return currentSet >= targetSet;
    }

    // /*
    // * REQUIRES: isComplete = true
    // * MODIFIES: this
    // * EFFECTS: calculate the time taken to complete the exercise
    // */
    // public void calculateCompletionTime(){

    // }

    /*
     * EFFECTS: return the current total volume performed based on the reps done
     */
    public double calculateVolume() {
        int vol = 0;
        for (int i : repDone) {
            vol += i * targetWeight;
        }
        return vol;
    }

    public String getName() {
        return name;
    }

    public MuscleRegion getType() {
        return type;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public int getTargetSet() {
        return targetSet;
    }

    public int getTargetRepetition() {
        return targetRepetition;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

}
