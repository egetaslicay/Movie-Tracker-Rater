

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Movie;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;



public class TestMovieLibrary {

    private MovieLibrary testLibrary;
    private Movie m1;
    private Movie m2;
    private Movie m3;
    private Movie m4;

    @BeforeEach
    void runBefore() {
        testLibrary = new MovieLibrary();
        m1 = new Movie("Inception", "Sci-Fi", 5, "Mind-bending masterpiece");
        m2 = new Movie("The Godfather", "Crime", 5, "Classic.");
        m3 = new Movie("Barbie", "Comedy", 3, "Colorful and fun");
        m4 = new Movie("Tenet", "Sci-Fi", 2, "Confusing but ambitious");

        testLibrary.addMovie(m1);
        testLibrary.addMovie(m2);
        testLibrary.addMovie(m3);
        testLibrary.addMovie(m4);
    }

    @Test
    void testMovieLibrary() {
        List<Movie> all = testLibrary.getAllMovies();
        assertEquals(4, all.size());
        assertTrue(all.contains(m1));
        assertTrue(all.contains(m2));
        assertTrue(all.contains(m3));
        assertTrue(all.contains(m4));
    }

    @Test
    void testRemoveMovieSuccess() {
        boolean removed = testLibrary.removeMovie("Barbie");
        assertTrue(removed);
        assertEquals(3, testLibrary.getAllMovies().size());
        assertNull(testLibrary.findMovieByTitle("Barbie"));
    }

    @Test
    void testRemoveMovieFail() {
        boolean removed = testLibrary.removeMovie("Nonexistent");
        assertFalse(removed);
        assertEquals(4, testLibrary.getAllMovies().size());
    }

    @Test
    void testFindMovieByTitleFound() {
        Movie found = testLibrary.findMovieByTitle("Inception");
        assertNotNull(found);
        assertEquals("Inception", found.getTitle());
    }

    @Test
    void testFindMovieByTitleNotFound() {
        assertNull(testLibrary.findMovieByTitle("Oppenheimer"));
    }

    @Test
    void testGetMoviesByGenre() {
        List<Movie> sciFi = testLibrary.getMoviesByGenre("sci-fi");
        assertEquals(2, sciFi.size());
        assertTrue(sciFi.contains(m1));
        assertTrue(sciFi.contains(m4));
    }

    @Test
    void testAverageRating() {
        double avg = testLibrary.getAverageRating();
        double expected = (5 + 5 + 3 + 2) / 4.0;
        assertEquals(expected, avg, 0.001);
    }

    @Test
    void testAverageRatingEmpty() {
        MovieLibrary emptyLib = new MovieLibrary();
        assertEquals(0.0, emptyLib.getAverageRating(), 0.01);
    }
}