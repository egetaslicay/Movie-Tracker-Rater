package model;

import java.util.List;
import java.util.ArrayList;

// Represents a library of movies
public class MovieLibrary {
    private List<Movie> movies; // collection of movies added by the user

    // EFFECTS: constructor that creates an empty movie library 
    public MovieLibrary() {
        movies = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given movie to the library
    public void addMovie(Movie m) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes the movie with the given title if it exists in movies, returns true if removed, false otherwise
    public boolean removeMovie(String title) {
        return false; // stub
    }

   
    public List<Movie> getAllMovies() {
        return null; // stub
    }

    // EFFECTS: returns the movie with the given title, or null if not found
    public Movie findMovieByTitle(String title) {
        return null; // stub
    }

    // EFFECTS: returns list of all movies that match the given genre
    public List<Movie> getMoviesByGenre(String genre) {
        return null; // stub
    }

    // EFFECTS: returns the average rating of all movies in the library
    public double getAverageRating() {
        return 0.0; // stub
    }
}