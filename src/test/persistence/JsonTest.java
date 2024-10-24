package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {
    protected void checkExercise(String name, MuscleRegion exerciseType, double weight, int set, int rep, Exercise e) {
        assertEquals(name, e.getName());
        assertEquals(exerciseType, e.getType());
        assertEquals(weight, e.getTargetWeight());
        assertEquals(set, e.getTargetSet());
        assertEquals(rep, e.getTargetRepetition());

    }

}
