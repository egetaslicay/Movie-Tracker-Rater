package ui;

import model.Movie;
import model.MovieLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MovieApp {
    private static final String JSON_STORE = "./data/movies.json";
    private MovieLibrary library;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: initializes scanner, library, persistence objects, and runs the app
    // loop
    public void run() {
        scanner = new Scanner(System.in);
        library = new MovieLibrary();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        System.out.println("\n\nWelcome to your Movie Tracker!");
        boolean keepGoing = true;

        while (keepGoing) {
            displayMenu();
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "1":
                    addMovie();
                    break;
                case "2":
                    viewAllMovies();
                    break;
                case "3":
                    searchByTitle();
                    break;
                case "4":
                    viewByGenre();
                    break;
                case "5":
                    removeMovie();
                    break;
                case "6":
                    checkAverageRating();
                    break;
                case "s":
                    saveLibrary();
                    break;
                case "l":
                    loadLibrary();
                    break;
                case "q":
                    askToSaveBeforeQuit();
                    keepGoing = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
            }
        }

        exitMessage();
    }

    // EFFECTS: displays main menu options
    private void displayMenu() {
        System.out.println("\n===================================");
        System.out.println("        🎬 Movie Tracker 🎬        ");
        System.out.println("===================================");
        System.out.println("1 - Add a movie");
        System.out.println("2 - View all movies");
        System.out.println("3 - Search for movie by title");
        System.out.println("4 - View movies by genre");
        System.out.println("5 - Remove a movie");
        System.out.println("6 - Check average movie rating");
        System.out.println("s - Save movie library to file");
        System.out.println("l - Load movie library from file");
        System.out.println("q - Quit");
        System.out.println("===================================");
        System.out.print("Enter your choice: ");
    }

    // MODIFIES: this
    // EFFECTS: prompts user for movie details and adds movie to library
    private void addMovie() {
        System.out.println("\nAdd a New Movie");

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();

        System.out.print("Rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Comment (optional): ");
        String comment = scanner.nextLine();

        Movie m = new Movie(title, genre, rating, comment);
        library.addMovie(m);

        System.out.println("\nMovie added successfully.\n");
    }

    // EFFECTS: prints all movies in the library, or a message if empty
    private void viewAllMovies() {
        System.out.println("\nYour Movie List:");
        List<Movie> all = library.getAllMovies();

        if (all.isEmpty()) {
            System.out.println("No movies in your list yet.\n");
        } else {
            for (Movie m : all) {
                System.out.println("- " + m);
            }
            System.out.println();
        }
    }

    // EFFECTS: prompts user for title and displays matching movie, or not found
    // message
    private void searchByTitle() {
        System.out.print("\nEnter title to search: ");
        String title = scanner.nextLine().trim();

        Movie m = library.findMovieByTitle(title);
        if (m == null) {
            System.out.println("No movie found with that title.\n");
        } else {
            System.out.println("\nFound: " + m + "\n");
        }
    }

    // EFFECTS: prompts user for genre and prints all matching movies
    private void viewByGenre() {
        System.out.print("\nEnter genre: ");
        String genre = scanner.nextLine().trim();

        List<Movie> matches = library.getMoviesByGenre(genre);
        if (matches.isEmpty()) {
            System.out.println("No movies found in that genre.\n");
        } else {
            System.out.println("\nMovies in genre '" + genre + "':");
            for (Movie m : matches) {
                System.out.println("- " + m);
            }
            System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes movie from library by title if found
    private void removeMovie() {
        System.out.print("\nEnter title to remove: ");
        String title = scanner.nextLine().trim();

        Movie m = library.findMovieByTitle(title);
        if (m == null) {
            System.out.println("No movie found with that title.\n");
        } else {
            library.removeMovie(m.getTitle());
            System.out.println("Movie removed.\n");
        }
    }

    // EFFECTS: displays the average rating of all movies, or message if library is
    // empty
    private void checkAverageRating() {
        System.out.println();
        if (library.getAllMovies().isEmpty()) {
            System.out.println("No movies in your list yet.\n");
        } else {
            double avg = library.getAverageRating();
            System.out.printf("Average rating: %.2f out of 5\n\n", avg);
        }
    }

    // EFFECTS: writes library to file; prints success or error message
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Movie library saved to: " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Unable to write to file.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads library from file; prints success or error message
    private void loadLibrary() {
        try {
            library = jsonReader.read();
            System.out.println("Movie library loaded from: " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read from file.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to save before quitting; saves if yes
    private void askToSaveBeforeQuit() {
        System.out.print("Would you like to save your movie list before quitting? (y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("y")) {
            saveLibrary();
        }
    }

    // EFFECTS: prints goodbye message
    private void exitMessage() {
        System.out.println("\nThanks for using Movie Tracker. See you next time!\n");
    }
}