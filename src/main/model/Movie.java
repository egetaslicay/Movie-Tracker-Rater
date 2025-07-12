package model;

// Represents a movie with a title, genre, user rating, and personal comment
public class Movie {

    private final String title; // title for the movie (final since it will not change)
    private final String genre; // the genre the movie is in (final since it will not change)
    private int rating; // the rating the user gives the movie 1-5, can be modified
    private String comment; // the comments a user makes regarding the movie, can be modified


//REQUIRES: title is a non-empty string, genre and comment are not null, rating is an integer between 1 and 5 inclusive
//EFFECTS: constructor sets all relevant fields of the movie according to parameters
    public Movie(String title, String genre, int rating, String comment) {
        this.title = null;
        this.genre = null;
        this.rating = 1;
        this.comment = null;
    }


    public String getTitle() {
        return null;
    }

    public String getGenre() {
        return null;
    }
    
    public int getRating() {
        return 0;
    }

    public String getComment() {
        return null;
    }

    // REQUIRES: newRating is an integer between 1 and 5 inclusive
    // MODIFIES: this
    // EFFECTS: updates the movie’s rating to newRating
    public void setRating(int newRating) {
        // stub
    }

    // REQUIRES: newComment is not null
    // MODIFIES: this
    // EFFECTS: updates the movie’s comment to newComment
    public void setComment(String newComment) {
        // stub
    }

    // EFFECTS: returns a string summary of the movie including title, genre, rating, and comment
    @Override
    public String toString() {
        return ""; // stub
    }
}

