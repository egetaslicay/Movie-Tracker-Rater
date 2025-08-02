package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

class TestMovie {
    private Movie testMovieInterstellar;
    private Movie testMovie2;

    @BeforeEach
    void runBefore() {
        testMovieInterstellar = new Movie("Interstellar", "Sci-Fi", 4, "Nolan's best");
        testMovie2 = new Movie("Inception", "Sci-Fi", 9, "Amazing movie");
    }

    @Test
    void testConstructor() {
        assertEquals("Interstellar", testMovieInterstellar.getTitle());
        assertEquals("Sci-Fi", testMovieInterstellar.getGenre());
        assertEquals(4, testMovieInterstellar.getRating());
        assertEquals("Nolan's best", testMovieInterstellar.getComment());
    }

    @Test
    void testGetTitle() {
        assertEquals("Interstellar", testMovieInterstellar.getTitle());
    }

    @Test
    void testGetGenre() {
        assertEquals("Sci-Fi", testMovieInterstellar.getGenre());
    }

    @Test
    void testGetRating() {
        assertEquals(4, testMovieInterstellar.getRating());
    }

    @Test
    void testGetComment() {
        assertEquals("Nolan's best", testMovieInterstellar.getComment());
    }

    @Test
    void testSetRating() {
        testMovieInterstellar.setRating(2);
        assertEquals(2, testMovieInterstellar.getRating());
    }

    @Test
    void testSetComment() {
        testMovieInterstellar.setComment("Masterpiece");
        assertEquals("Masterpiece", testMovieInterstellar.getComment());
    }

    @Test
    void testToString() {
        String result = testMovieInterstellar.toString();
        assertTrue(result.contains("Interstellar"));
        assertTrue(result.contains("Sci-Fi"));
        assertTrue(result.contains("4"));
        assertTrue(result.contains("Nolan's best"));
    }

    @Test
    public void testToDisplayString() {
        assertEquals("Inception (Sci-Fi, Rating: 9) - Amazing movie", testMovie2.toDisplayString());
    }
}