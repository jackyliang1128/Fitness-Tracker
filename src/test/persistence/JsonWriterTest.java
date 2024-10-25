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

public class JsonWriterTest extends JsonTest {
    List<FitnessPlan> testFitnessPlans;

    @BeforeEach
    void runBefore() {
        testFitnessPlans = new ArrayList<>();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            FitnessPlan fp1 = new FitnessPlan("push day");
            FitnessPlan fp2 = new FitnessPlan("pull day");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFitnessPlanList() {
        try {
            FitnessPlan fp1 = new FitnessPlan("Monday workout routine");
            FitnessPlan fp2 = new FitnessPlan("Tuesday workout routine");
            testFitnessPlans.add(fp1);
            testFitnessPlans.add(fp2);

            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfFitnessPlan.json");
            writer.open();
            writer.write(testFitnessPlans);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfFitnessPlan.json");
            testFitnessPlans = reader.read();
            FitnessPlan fp3 = testFitnessPlans.get(0);
            FitnessPlan fp4 = testFitnessPlans.get(1);
            assertEquals("Monday workout routine", fp3.getPlanName());
            assertEquals(0, fp3.getDuration());
            assertEquals(0, fp3.getWorkouts().size());
            assertEquals("Tuesday workout routine", fp4.getPlanName());
            assertEquals(0, fp4.getDuration());
            assertEquals(0, fp4.getWorkouts().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    @SuppressWarnings("methodlength")
    void testWriterGeneralFitnessPlanList() {
        try {
            FitnessPlan fp1 = new FitnessPlan("Wednesday workout routine");
            FitnessPlan fp2 = new FitnessPlan("Thursday workout routine");
            Exercise e1 = new Exercise("incline dumbbell press", MuscleRegion.CHEST, 60, 3, 8);
            Exercise e2 = new Exercise("bulgarian split squat", MuscleRegion.QUADRICEPS, 60, 3, 8);
            Exercise e3 = new Exercise("cable flys", MuscleRegion.CHEST, 25, 4, 10);
            fp1.addExercise(e1, -1);
            fp1.addExercise(e3, -1);
            fp2.addExercise(e2, -1);
            testFitnessPlans.add(fp1);
            testFitnessPlans.add(fp2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfFitnessPlan.json");
            writer.open();
            writer.write(testFitnessPlans);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfFitnessPlan.json");
            testFitnessPlans = reader.read();
            FitnessPlan fp3 = testFitnessPlans.get(0);
            FitnessPlan fp4 = testFitnessPlans.get(1);
            List<Exercise> exerciseList1 = fp3.getWorkouts();
            List<Exercise> exerciseList2 = fp4.getWorkouts();
            assertEquals("Wednesday workout routine", fp3.getPlanName());
            assertEquals(0, fp3.getDuration());
            assertEquals(2, exerciseList1.size());
            assertEquals("Thursday workout routine", fp4.getPlanName());
            assertEquals(0, fp4.getDuration());
            assertEquals(1, exerciseList2.size());
            checkExercise("incline dumbbell press", MuscleRegion.CHEST, 60, 3, 8, exerciseList1.get(0));
            checkExercise("bulgarian split squat", MuscleRegion.QUADRICEPS, 60, 3, 8, exerciseList2.get(0));
            checkExercise("cable flys", MuscleRegion.CHEST, 25, 4, 10, exerciseList1.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
