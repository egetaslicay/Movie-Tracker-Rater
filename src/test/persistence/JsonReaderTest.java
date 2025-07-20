package persistence;

import model.Movie;
import model.MovieLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        assertThrows(IOException.class, reader::read);
    }

    @Test
    void testReaderEmptyLibrary() throws IOException {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        MovieLibrary ml = reader.read();
        assertEquals(0, ml.getAllMovies().size());
    }

    @Test
    void testReaderGeneralLibrary() throws IOException {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        MovieLibrary ml = reader.read();
        List<Movie> movies = ml.getAllMovies();
        assertEquals(2, movies.size());
        checkMovie("Inception", "Sci-Fi", 9, "Mind bending", movies.get(0));
        checkMovie("The Notebook", "Romance", 8, "Sad", movies.get(1));

    }
}