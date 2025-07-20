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
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses movie library from JSON object and returns it
    private MovieLibrary parseMovieLibrary(JSONObject jsonObject) {
        MovieLibrary library = new MovieLibrary();
        addMovies(library, jsonObject);
        return library;
    }

    // MODIFIES: library
    // EFFECTS: parses movies from JSON object and adds them to movie library
    private void addMovies(MovieLibrary library, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(library, nextMovie);
        }
    }

    // MODIFIES: library
    // EFFECTS: parses movie from JSON object and adds it to movie library
    private void addMovie(MovieLibrary library, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String genre = jsonObject.getString("genre");
        int rating = jsonObject.getInt("rating");
        String comment = jsonObject.getString("comment");

        Movie movie = new Movie(title, genre, rating, comment);
        library.addMovie(movie);
    }
}