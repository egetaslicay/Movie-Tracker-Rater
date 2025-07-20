package persistence;

import model.Movie;
import model.MovieLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        MovieLibrary library = new MovieLibrary();
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");

        assertThrows(IOException.class, writer::open);
    }

    @Test
    void testWriterEmptyMovieLibrary() throws IOException {

        MovieLibrary library = new MovieLibrary();
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieLibrary.json");
        writer.open();
        writer.write(library);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterEmptyMovieLibrary.json");
        library = reader.read();
        assertEquals(0, library.getAllMovies().size());
    }

    @Test
    void testWriterGeneralMovieLibrary() throws IOException {

        MovieLibrary library = new MovieLibrary();
        Movie m1 = new Movie("Inception", "Sci-Fi", 9, "Mind-bending thriller");
        Movie m2 = new Movie("The Notebook", "Romance", 8, "Classic love story");
        library.addMovie(m1);
        library.addMovie(m2);

        JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieLibrary.json");
        writer.open();
        writer.write(library);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterGeneralMovieLibrary.json");
        library = reader.read();
        List<Movie> movies = library.getAllMovies();
        assertEquals(2, movies.size());
        checkMovie("Inception", "Sci-Fi", 9, "Mind-bending thriller", movies.get(0));
        checkMovie("The Notebook", "Romance", 8, "Classic love story", movies.get(1));

    }
}