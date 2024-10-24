package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads fitness from JSON data stored in file
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: read fitness from file and returns it
    // throws IOException if an error occurs reading data from file
    public FitnessPlan read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string an returns it
    private String readFile(String source) throws IOException {
        return "";
    }

    // MODIFIES: FitnessApp maybe
    // EFFECTS: parses all the fitness plans from JSON object and returns it;
    private List<FitnessPlan> parseFitnessPlan (JSONObject jsonObject){
        List fp = new ArrayList<>();
        return fp;
    }

    // MODIFIES: FitnessPlan
    // EFFECTS: parses fitness plans from JSON object and adds them to ArrayList in ui
    private void addFitnessPlan(FitnessPlan fp, JSONObject jsonObject) {

    }

    // MODIFIES: FitnessPlan
    // EFFECTS: parses exercises from JSON object and adds them to FitnessPlan
    private void addExercises(FitnessPlan fp, JSONObject jsonObject) {

    }

    // MODIFIES: FitnessPlan
    // EFFECTS: parases exercise from JSON object and adds it to FitnessPlan
    private void addExercise(FitnessPlan fp, JSONObject jsonObject) {

    }

}



