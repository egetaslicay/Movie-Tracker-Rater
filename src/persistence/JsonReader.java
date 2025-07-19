
package persistence;

import model.Movie;
import model.MovieLibrary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads MovieLibrary from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads MovieLibrary from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieLibrary read() throws IOException {
        // no implementation yet
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        // no implementation yet
        return null;
    }

    // EFFECTS: parses MovieLibrary from JSON object and returns it
    private MovieLibrary parseMovieLibrary(JSONObject jsonObject) {
        // no implementation yet
        return null;
    }

    // MODIFIES: library
    // EFFECTS: parses movies from JSON object and adds them to MovieLibrary
    private void addMovies(MovieLibrary library, JSONObject jsonObject) {
        // no implementation yet
    }

    // MODIFIES: library
    // EFFECTS: parses movie from JSON object and adds it to MovieLibrary
    private void addMovie(MovieLibrary library, JSONObject jsonObject) {
        // no implementation yet
    }
}