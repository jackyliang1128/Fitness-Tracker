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
    public List<FitnessPlan> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFitnessPlan(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: FitnessApp maybe
    // EFFECTS: parses all the fitness plans from JSON object and returns it;
    private List<FitnessPlan> parseFitnessPlan(JSONObject jsonObject) {
        List<FitnessPlan> fpList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("fitnessplans");

        for (Object json : jsonArray) {
            JSONObject nextFitnessPlan = (JSONObject) json;
            addFitnessPlan(fpList, nextFitnessPlan);
        }

        return fpList;
    }

    // MODIFIES: FitnessPlan
    // EFFECTS: parses fitness plans from JSON object and adds them to ArrayList in
    // ui
    private void addFitnessPlan(List<FitnessPlan> fpList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double duration = jsonObject.getDouble("duration");

        FitnessPlan fp = new FitnessPlan(name);
        fp.incrementExerciseTime(duration);
        fpList.add(fp);
        addExercises(fp, jsonObject);

    }

    // MODIFIES: FitnessPlan
    // EFFECTS: parses exercises from JSON object and adds them to FitnessPlan
    private void addExercises(FitnessPlan fp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(fp, nextExercise);
        }

    }

    // MODIFIES: FitnessPlan
    // EFFECTS: parses exercise from JSON object and adds it to FitnessPlan
    private void addExercise(FitnessPlan fp, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MuscleRegion type = MuscleRegion.valueOf(jsonObject.getString("type"));
        double weight = jsonObject.getDouble("weight");
        int set = jsonObject.getInt("set");
        int rep = jsonObject.getInt("rep");

        Exercise exercise = new Exercise(name, type, weight, set, rep);

        fp.addExercise(exercise, -1);

    }

}
