package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a library of movies
public class MovieLibrary implements Writable {
    private List<Movie> movies; // collection of movies added by the user

    // EFFECTS: constructor that creates an empty movie library
    public MovieLibrary() {
        movies = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Created a new MovieLibrary"));
    }

    // MODIFIES: this
    // EFFECTS: adds the given movie to the library
    public void addMovie(Movie m) {
        movies.add(m);
        EventLog.getInstance().logEvent(new Event("Added movie: " + m.getTitle()));
    }

    // MODIFIES: this
    // EFFECTS: removes the movie with the given title if it exists in movies,
    // returns true if removed, false otherwise
    public boolean removeMovie(String title) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equals(title)) {
                movies.remove(i);
                EventLog.getInstance().logEvent(new Event("Removed movie: " + title));
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns a *copy* of all movies in the library (not sure why i made
    // this to be honest?)
    public List<Movie> getAllMovies() {
        EventLog.getInstance().logEvent(new Event("Viewed all movies"));
        return new ArrayList<>(movies);
    }

    // EFFECTS: returns the movie with the given title, or null if not found
    public Movie findMovieByTitle(String title) {
        for (Movie m : movies) {
            if (m.getTitle().equals(title)) {
                EventLog.getInstance().logEvent(new Event("Found movie by title: " + title));
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
        EventLog.getInstance().logEvent(new Event("Filtered movies by genre: " + genre
                + " (" + matches.size() + " match(es))"));
        return matches;
    }

    // EFFECTS: returns the average rating of all movies in the library
    public double getAverageRating() {
        if (movies.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("Attempted to calculate average rating but library was empty"));
            return 0.0;
        }
        int total = 0;
        for (Movie m : movies) {
            total += m.getRating();
        }
        double avg = (double) total / movies.size();
        EventLog.getInstance().logEvent(new Event("Calculated average rating: " + avg));
        return avg;
    }

    // EFFECTS: returns a JSONObject that contains a list of all movies in the
    // library
    @Override
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        for (Movie movie : movies) {
            jsonArray.put(movie.toJson());
        }

        JSONObject json = new JSONObject();
        json.put("movies", jsonArray);
        EventLog.getInstance().logEvent(new Event("Converted MovieLibrary to JSON with " + movies.size() + " movies"));
        return json;
    }
}