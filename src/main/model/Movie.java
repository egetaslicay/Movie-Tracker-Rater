package model;

import java.util.Collection;

import org.json.JSONObject;

import persistence.Writable;

// Represents a movie with a title, genre, user rating, and personal comment
public class Movie implements Writable {

    private final String title; // title for the movie (final since it will not change)
    private final String genre; // the genre the movie is in (final since it will not change)
    private int rating; // the rating the user gives the movie 1-5, can be modified
    private String comment; // the comments a user makes regarding the movie, can be modified

    // REQUIRES: title is a non-empty string, genre and comment are not null, rating
    // is an integer between 1 and 5 inclusive
    // EFFECTS: constructor sets all relevant fields of the movie according to
    // parameters
    public Movie(String title, String genre, int rating, String comment) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    // REQUIRES: newRating is an integer between 1 and 5 inclusive
    // MODIFIES: this
    // EFFECTS: updates the movie’s rating to newRating
    public void setRating(int newRating) {
        this.rating = newRating;
    }

    // REQUIRES: newComment is not null
    // MODIFIES: this
    // EFFECTS: updates the movie’s comment to newComment
    public void setComment(String newComment) {
        this.comment = newComment;
    }

    // EFFECTS: returns a string summary of the movie including title, genre,
    // rating, and comment
    @Override
    public String toString() {
        return "Movie Title: " + title + ", Genre: " + genre + ", Rating: " + rating + "/5, Comment: " + comment;
    }

    // REQUIRES: this movie must have non-null title, genre, and comment
    // EFFECTS: returns a JSONObject representing this movie, including its title,
    // genre, rating, and comment
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("genre", genre);
        json.put("rating", rating);
        json.put("comment", comment);
        return json;
    }
}
