// package model;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class ExerciseTest {
//     private Exercise testExercise;

//     @BeforeEach
//     void runBefore() {
//         testExercise = new Exercise("Bench Press", MuscleRegion.CHEST, 225, 5, 5);

//     }

//     @Test
//     void testConstructor() {
//         assertEquals("Bench Press", testExercise.getName());
//         assertEquals(MuscleRegion.CHEST, testExercise.getType());
//         assertEquals(225, testExercise.getTargetWeight());
//         assertEquals(5, testExercise.getTargetSet());
//         assertEquals(5, testExercise.getTargetRepetition());

//         assertFalse(testExercise.isExerciseCompleted());
//     }

//     @Test
//     void testAddExerciseAmount() {
//         testExercise.addExerciseAmount(185, 2, 4);
//         assertFalse(testExercise.getIsComplete());
//         assertEquals(185, testExercise.getTargetWeight());
//         assertEquals(7, testExercise.getTargetSet());
//         assertEquals(4, testExercise.getTargetRepetition());

//     }

//     @Test
//     void testRemoveExerciseAmount() {
//         testExercise.reduceExerciseAmount(3);
//         assertFalse(testExercise.getIsComplete());
//         assertEquals(2, testExercise.getTargetSet());

//     }

//     @Test
//     void testRemoveAllExerciseAmount() {
//         testExercise.reduceExerciseAmount(5);
//         assertTrue(testExercise.getIsComplete());
//     }

//     @Test
//     void testCalculateVolume() {
//         assertEquals(0, testExercise.calculateVolume());
//         testExercise.doExercise();
//         testExercise.doExercise();
//         assertEquals(2250, testExercise.calculateVolume());

//     }

//     @Test
//     void testExerciseNotComplete() {
//         assertFalse(testExercise.isExerciseCompleted());
//         testExercise.doExercise();
//         testExercise.doExercise();
//         assertFalse(testExercise.isExerciseCompleted());
//     }

//     @Test
//     void testExerciseComplete() {
//         assertFalse(testExercise.isExerciseCompleted());
//         testExercise.doExercise();
//         testExercise.doExercise();
//         assertFalse(testExercise.isExerciseCompleted());
//         testExercise.doExercise();
//         testExercise.doExercise();
//         testExercise.doExercise();
//         assertTrue(testExercise.isExerciseCompleted());

//     }

//     @Test
//     void testChangingCurrentSet() {
//         assertEquals(0, testExercise.getCurrentSet());
//         testExercise.setCurrentSet(21);
//         assertEquals(21, testExercise.getCurrentSet());
//     }

// }
