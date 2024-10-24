package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReaderTest extends JsonTest {
    List<FitnessPlan> testFitnessPlans;

    @BeforeEach
    void runBefore() {
        testFitnessPlans = new ArrayList<>();

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            testFitnessPlans = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFitnessPlanList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfFitnessPlan.json");
        try {
            testFitnessPlans = reader.read();
            FitnessPlan fp1 = testFitnessPlans.get(0);
            FitnessPlan fp2 = testFitnessPlans.get(1);

            assertEquals("Monday workout routine", fp1.getPlanName());
            assertEquals(0, fp1.getDuration());
            assertEquals(0, fp1.getWorkouts().size());

            assertEquals("Tuesday workout routine", fp2.getPlanName());
            assertEquals(0, fp2.getDuration());
            assertEquals(0, fp2.getWorkouts().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFitnessPlanList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfFitnessPlan.json");
        try {
            testFitnessPlans = reader.read();
            FitnessPlan fp1 = testFitnessPlans.get(0);
            FitnessPlan fp2 = testFitnessPlans.get(1);

            assertEquals("Monday workout routine", fp1.getPlanName());
            assertEquals(10, fp1.getDuration());
            assertEquals(2, fp1.getWorkouts().size());

            assertEquals("Tuesday workout routine", fp2.getPlanName());
            assertEquals(20, fp2.getDuration());
            assertEquals(2, fp2.getWorkouts().size());

            List<Exercise> fp1Exercises = fp1.getWorkouts();
            List<Exercise> fp2Exercises = fp2.getWorkouts();

            checkExercise("bench press", MuscleRegion.CHEST, 225, 5, 5, fp1Exercises.get(0));
            checkExercise("tricep pushdown", MuscleRegion.TRICEPS, 75, 3, 10, fp1Exercises.get(1));

            checkExercise("lat pulldowns", MuscleRegion.BACK, 140, 3, 8, fp2Exercises.get(0));
            checkExercise("hammer curls", MuscleRegion.BICEPS, 35,3, 10, fp2Exercises.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
