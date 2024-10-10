package model;

// Representes an exercise having a name, type, weight, set, and repetition
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
    private double duration; // time in minutes

    /*
     * REQUIRES: exerciseName has a non-zero length
     * weight > 0, set > 0, rep > 0
     * EFFECTS: instantiate an exercise with a name, type, weight to use
     * set to accomplish, rep per set.
     * exercise is not completed
     */
    public Exercise(String exerciseName, MuscleRegion exerciseType, double weight, int set, int rep) {
        name = exerciseName;
        type = exerciseType;
        targetWeight = weight;
        targetSet = set;
        targetRepetition = rep;

        isComplete = false;

    }

    /*
     * REQUIRES: isComplete = true, weight > 0, set > 0, rep > 0
     * MODIFIES: this
     * EFFECTS: add more sets and repetitions when the exercise is completed
     */
    public void addExercise(double weight, int set, int rep) {

    }

    /*
     * REQUIRES: isComplete = false, set <= targetSet - currentSet
     * MODIFIES: this
     * EFFECTS: reduce amount of set left in the execise
     */
    public void reduceExercise(int set) {

    }

    /*
     * MODIFIES: this
     * EFFECTS: check whether the exercise has been completed
     */
    public void isCompleted() {

    }

    /*
     * REQUIRES: isComplete = true
     * MODIFIES: this
     * EFFECTS: calculate the time taken to complete the exercise
     */
    public void calculateCompletionTime(){

    }

    /*
     * EFFECTS: return the current total volume performed
     */
    public double calculateVolume() {
        return 0;
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

    public void setTargetWeight(double weight) {
        this.targetWeight = weight;
    }

    public int getTargetSet() {
        return targetSet;
    }

    public void setTargetSet(int set) {
        this.targetSet = set;
    }

    public int getTargetRepetition() {
        return targetRepetition;
    }

    public void setTargetRepetition(int repetition) {
        this.targetRepetition = repetition;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

}
