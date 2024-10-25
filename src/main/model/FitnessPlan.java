package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;

// Represents a fitness plan that has a name and contains a list of exercises for the session
// to accomplish in the order that it is added
public class FitnessPlan implements Writable {

    private List<Exercise> workouts;

    private String planName;
    private double duration;

    /*
     * EFFECTS: create a fitness plan with am empty list of workouts with no time
     * done
     */
    public FitnessPlan(String name) {
        planName = name;
        duration = 0;
        workouts = new ArrayList<>();

    }

    /*
     * REQUIRES: workouts.size() > 0
     * EFFECTS: return the next exercise to be completed
     * if all exercise is complete, return null
     */
    public Exercise beginExercise() {
        for (Exercise e : workouts) {
            if (!e.isExerciseCompleted()) {
                return e;
            }
        }
        return null;
    }

    /*
     * MODIFIES: this
     * EFFECTS: add an exercise to the desired location in the fitness plan
     */
    public void addExercise(Exercise e, int location) {
        if (location == -1) {
            workouts.add(e);
        } else {
            workouts.add(location, e);
        }
    }

    /*
     * REQUIRES: workouts.size() > 0 and exercise is not completed
     * MODIFIES: this
     * EFFECTS: remove an existing exercise in the fitness plan
     */
    public void removeExercise(int location) {
        workouts.remove(location);
    }

    /*
     * REQUIRES: workouts.size() > 0
     * MODIFIES: this
     * EFFECTS: returns a list of exercise that is not completed, null if all
     * exercise completed
     */
    public List<Exercise> viewRemaningExercise() {
        List<Exercise> remainExercise = new ArrayList<>();

        for (Exercise e : workouts) {
            if (!e.isExerciseCompleted()) {
                remainExercise.add(e);
            }
        }

        return remainExercise;
    }

    /*
     * REQUIRES: currentExercise is completed
     * MODIFIES: this
     * EFFECTS: increment the time taken to complete the exercise
     */
    public void incrementExerciseTime(double time) {
        duration += time;

    }

    // /*
    // * REQUIRES:
    // * MODIFIES:
    // * EFFECTS: return total running time of fitness plan
    // */
    // public double calculateTotalTime() {
    // return 0;
    // }

    /*
     * EFFECTS: return the total volume performed in the fitness plan
     */
    public int calculateTotalVolume() {
        int totalVolume = 0;

        for (Exercise e : workouts) {
            totalVolume += e.calculateVolume();

        }
        return totalVolume;
    }

    public void setWorkouts(List<Exercise> workouts) {
        this.workouts = workouts;
    }

    public List<Exercise> getWorkouts() {
        return workouts;
    }

    public String getPlanName() {
        return planName;
    }

    public double getDuration() {
        return duration;
    }

    // EFFECTS: return this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", planName);
        json.put("duration", duration);
        json.put("exercises", ExercisestoJson());
        return json;
    }

    // EFFECTS: return exercises in this fitnessplan as a JSON array
    public JSONArray ExercisestoJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : workouts) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }


}
