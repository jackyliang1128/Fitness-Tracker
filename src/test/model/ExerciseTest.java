package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExerciseTest {
    private Exercise testExercise;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Bench Press", MuscleRegion.CHEST, 225, 5, 5);

    }

    @Test
    void testConstructor() {
        assertEquals("Bench Press", testExercise.getName());
        assertEquals(MuscleRegion.CHEST, testExercise.getType());
        assertEquals(225, testExercise.getTargetWeight());
        assertEquals(5, testExercise.getTargetSet());
        assertEquals(5, testExercise.getTargetRepetition());

        assertFalse(testExercise.isExerciseCompleted());
    }

    @Test
    void testAddExerciseAmount() {

    }

    @Test
    void testRemoveExerciseAmount() {

    }

    @Test
    void testCalculateCompletionTime() {

    }

    @Test
    void testCalculateVolume() {

    }

    @Test
    void testExerciseNotComplete() {

    }

    @Test
    void testExerciseComplete() {

    }

}
