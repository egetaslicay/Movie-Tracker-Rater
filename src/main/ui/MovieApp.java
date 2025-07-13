package ui;
import model.Movie;
import model.MovieLibrary;
import java.util.Scanner;

public class MovieApp {

private MovieLibrary library;
private Scanner scanner; 


// EFFECTS: runs the movie tracker app
    public void run() {
        scanner = new Scanner(System.in);
        library = new MovieLibrary();
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
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        exitMessage();
    }

    // EFFECTS: prints the main menu
    private void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1 - Add a movie");
        System.out.println("2 - View all movies");
        System.out.println("3 - Search for movie by title");
        System.out.println("4 - View movies by genre");
        System.out.println("5 - Remove a movie");
        System.out.println("q - Quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a new movie to the library from user input
    private void addMovie() {
        // to be implemented
    }

    // EFFECTS: prints all movies in the library
    private void viewAllMovies() {
        // to be implemented
    }

    // EFFECTS: prints a movie matching the given title
    private void searchByTitle() {
        // to be implemented
    }

    // EFFECTS: prints all movies that match a genre
    private void viewByGenre() {
        // to be implemented
    }

    // MODIFIES: this
    // EFFECTS: removes a movie with the given title, if it exists
    private void removeMovie() {
        // to be implemented
    }

    // EFFECTS: displays exit message
    private void exitMessage() {
        System.out.println("Goodbye! Hope you enjoyed your movies twin!");
    }
}


