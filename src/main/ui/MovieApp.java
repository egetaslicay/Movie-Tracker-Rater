package ui;

import model.Movie;
import model.MovieLibrary;

import java.util.List;
import java.util.Scanner;

public class MovieApp {
    private MovieLibrary library;
    private Scanner scanner;

    @SuppressWarnings("methodlength")
    // EFFECTS: runs the movie tracker app
    public void run() {
        scanner = new Scanner(System.in);
        library = new MovieLibrary();
        System.out.println();
        System.out.println();
        System.out.println("Welcome to your Movie Tracker!");
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
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
            }
        }

        exitMessage();
    }

    // EFFECTS: prints the main menu
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1 - Add a movie");
        System.out.println("2 - View all movies");
        System.out.println("3 - Search for movie by title");
        System.out.println("4 - View movies by genre");
        System.out.println("5 - Remove a movie");
        System.out.println("6 - Check average movie rating");
        System.out.println("q - Quit");
        System.out.print("\nEnter your choice: ");
    }

    // MODIFIES: this
    // EFFECTS: adds a new movie to the library from user input
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

    // EFFECTS: prints all movies in the library
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

    // EFFECTS: prints a movie matching the given title
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

    // EFFECTS: prints movies that match a genre
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
    // EFFECTS: removes a movie with the given title, if it exists
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

    // EFFECTS: displays the average rating of all movies
    private void checkAverageRating() {
        System.out.println();
        if (library.getAllMovies().isEmpty()) {
            System.out.println("No movies in your list yet.\n");
        } else {
            double avg = library.getAverageRating();
            System.out.printf("Average rating: %.2f out of 5\n\n", avg);
        }
    }

    // EFFECTS: displays exit message
    private void exitMessage() {
        System.out.println("\nThanks for using Movie Tracker. See you next time!\n");
    }
}