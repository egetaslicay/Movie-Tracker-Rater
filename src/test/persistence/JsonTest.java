package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;


//Helper class for tests defines the checkMovie(...) method that JsonReaderTest uses.

public class JsonTest {
    protected void checkMovie(String title, String genre, int rating, String comment, Movie movie) {
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(rating, movie.getRating());
        assertEquals(comment, movie.getComment());
    }
}