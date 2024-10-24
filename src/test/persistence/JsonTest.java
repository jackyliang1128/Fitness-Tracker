package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExercise(String name, MuscleRegion exerciseType, double weight, int set, int rep, Exercise e) {
        assertEquals(name, e.getName());
        assertEquals(exerciseType, e.getType());
        assertEquals(weight, e.getTargetWeight());
        assertEquals(set, e.getTargetSet());
        assertEquals(rep, e.getTargetRepetition());

    }

}
