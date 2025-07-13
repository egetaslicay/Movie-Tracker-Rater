package model;

import java.util.ArrayList;
import java.util.List;

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
        movies.add(m);
    }

    // MODIFIES: this
    // EFFECTS: removes the movie with the given title if it exists in movies,
    // returns true if removed, false otherwise
    public boolean removeMovie(String title) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equals(title)) {
                movies.remove(i);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns a *copy* of all movies in the library (not sure why i made this to be honest?)
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    // EFFECTS: returns the movie with the given title, or null if not found
    public Movie findMovieByTitle(String title) {
        for (Movie m : movies) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }

    // EFFECTS: returns list of all movies that match the given genre
    public List<Movie> getMoviesByGenre(String genre) {
        List<Movie> matches = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getGenre().equalsIgnoreCase(genre)) {
                matches.add(m);
            }
        }
        return matches;
    }

    // EFFECTS: returns the average rating of all movies in the library
    public double getAverageRating() {
        if (movies.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (Movie m : movies) {
            total += m.getRating();
        }
        return (double) total / movies.size();
    }
}