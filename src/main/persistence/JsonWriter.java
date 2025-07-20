package persistence;

import model.MovieLibrary;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of a movie library to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        // implementation not shown
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        // implementation not shown
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of movie library to file
    public void write(MovieLibrary ml) {
        // implementation not shown
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // implementation not shown
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // implementation not shown
    }
}