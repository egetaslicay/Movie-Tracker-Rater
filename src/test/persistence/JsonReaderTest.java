package persistence;

import model.Movie;
import model.MovieLibrary;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import persistence.JsonReader;



import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieLibrary ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            MovieLibrary ml = reader.read();
            assertEquals(0, ml.getAllMovies().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            MovieLibrary ml = reader.read();
            List<Movie> movies = ml.getAllMovies();
            assertEquals(2, movies.size());
            checkMovie("Inception", "Sci-Fi", 9, "Mind bending", movies.get(0));
            checkMovie("The Notebook", "Romance", 8, "Sad", movies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}