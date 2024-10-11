package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

public class FitnessPlanTest {

    private FitnessPlan testFitnessPlan;
    private Exercise e1;
    private Exercise e2;
    private Exercise e3;
    private List<Exercise> exerciseList;

    private Exercise e4;


    @BeforeEach
    void runBefore() {
        testFitnessPlan = new FitnessPlan("Monday Workout");

        e1 = new Exercise("Bench Press", MuscleRegion.CHEST, 225, 5, 5);
        e2 = new Exercise("Shoulder Press", MuscleRegion.SHOULDER, 60, 3, 8);
        e3 = new Exercise("Lateral Raise", MuscleRegion.SHOULDER, 20, 3, 12);

        exerciseList.add(e1);
        exerciseList.add(e2);
        exerciseList.add(e3);

        testFitnessPlan.setWorkouts(exerciseList);

        e4 = new Exercise("Bicep Curls", MuscleRegion.BICEPS, 30, 4, 10);

    }

    @Test
    void testConstructor() {
        assertEquals("Monday Workout", testFitnessPlan.getPlanName());
        assertEquals(0, testFitnessPlan.getDuration());
        assertEquals(0, testFitnessPlan.getWorkouts().size());
    }

    @Test
    void testBeginExercise() {
        testFitnessPlan.beginExercise();
        assertEquals(e1, testFitnessPlan.getCurrentExercise());
        e1.setIsComplete(true);
        e2.setIsComplete(true);
        testFitnessPlan.beginExercise();
        assertEquals(e3, testFitnessPlan.getCurrentExercise());
    }

    @Test
    void testAddExercise(){
        testFitnessPlan.addExercise(e4, 1);
        assertEquals(e2, testFitnessPlan.getWorkouts().get(2));

    }

    @Test
    void testRemoveExercise(){
        testFitnessPlan.removeExercise(2);
        assertEquals(2, testFitnessPlan.getWorkouts().size());

    }

    @Test
    void testViewRemaniningExercise() {
        List<Exercise> testExerciseList = new ArrayList<>();

        testExerciseList.add(e2);
        testExerciseList.add(e3);

        e1.setIsComplete(true);

        assertEquals(testExerciseList, testFitnessPlan.viewRemaningExercise());
    
        
    }
    @Test
    void testIncrementTime(){
        testFitnessPlan.incrementExerciseTime(20);
        assertEquals(20, testFitnessPlan.getDuration());
    
    }


    @Test
    void calculateTotalVolume(){
        e1.setIsComplete(true);
        e2.setIsComplete(true);
        e3.setIsComplete(true);

        assertEquals(7785, testFitnessPlan.calculateTotalVolume());
        

    }

}
