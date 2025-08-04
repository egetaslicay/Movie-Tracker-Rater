package ui;

import model.Event;
import model.EventLog;
import javax.swing.*;
import java.awt.*;

import model.EventLog;
import model.Movie;
import model.MovieLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MovieAppGUI extends JFrame {
    private MovieLibrary library;
    private DefaultListModel<String> movieListModel;
    private JList<String> movieList;
    private JTextField titleField;
    private JTextField ratingField;
    private JTextField commentField;
    private JComboBox<String> genreField;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "data/movies.json";
    private static final String IMAGE_PATH = "data/splash.jpg";

    // MODIFIES: this
    // EFFECTS: initializes the MovieAppGUI window with a splash screen and movie
    // library
    public MovieAppGUI() {
        library = new MovieLibrary();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Movie Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        showTitleScreen();
        setPreferredSize(new Dimension(800, 600));
        pack();
        setVisible(true);

        setupWindowCloseLogging();
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: displays the splash/title screen with an image and start button
    private void showTitleScreen() {
        JPanel splashPanel = new JPanel();
        splashPanel.setLayout(new BoxLayout(splashPanel, BoxLayout.Y_AXIS));
        splashPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        splashPanel.setBackground(new Color(24, 26, 34));

        JLabel splashLabel = loadSplashImage();
        splashLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        splashPanel.add(splashLabel);
        splashPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel title = new JLabel("🎬 Movie Tracker");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        splashPanel.add(title);
        splashPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton startButton = createButton("Enter Library", () -> {
            getContentPane().removeAll();
            setupInputPanel();
            setupMovieList();
            revalidate();
            repaint();
        });
        startButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        splashPanel.add(startButton);

        add(splashPanel, BorderLayout.CENTER);
    }

    // EFFECTS: sets up printing of the EventLog when the app is closed
    private void setupWindowCloseLogging() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("\n--- Event Log ---");
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
                System.out.println("--- End of Event Log ---");
            }
        });
    }

    // EFFECTS: returns a JLabel containing the splash image scaled to size;
    // if the image fails to load, returns a JLabel with an error message.
    private JLabel loadSplashImage() {
        try {
            BufferedImage splashImage = ImageIO.read(new File(IMAGE_PATH));
            if (splashImage == null) {
                throw new IOException("Invalid image format");
            }
            Image scaledImage = splashImage.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (IOException e) {
            JLabel errorLabel = new JLabel("Failed to load image");
            errorLabel.setForeground(Color.RED);
            return errorLabel;
        }
    }

    // REQUIRES: action is not null
    // EFFECTS: returns a JButton with given text and an attached action listener
    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(102, 0, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(e -> action.run());
        return button;
    }

    // EFFECTS: returns a GridBagConstraints object configured with the given grid
    // position (x, y)
    private GridBagConstraints gbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: sets up input panel for adding/filtering/loading/saving movies
    private void setupInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(34, 36, 44));

        titleField = new JTextField();
        ratingField = new JTextField();
        commentField = new JTextField();
        genreField = new JComboBox<>(new String[] {
                "Action", "Drama", "Comedy", "Horror", "Sci-Fi", "Romance", "Thriller"
        });

        int y = 0;
        inputPanel.add(makeLabel("Title:"), gbc(0, y));
        inputPanel.add(titleField, gbc(1, y++));
        inputPanel.add(makeLabel("Genre:"), gbc(0, y));
        inputPanel.add(genreField, gbc(1, y++));
        inputPanel.add(makeLabel("Rating (1–5):"), gbc(0, y));
        inputPanel.add(ratingField, gbc(1, y++));
        inputPanel.add(makeLabel("Comment:"), gbc(0, y));
        inputPanel.add(commentField, gbc(1, y++));

        inputPanel.add(createButton("Add Movie", this::handleAddMovie), gbc(0, y));
        inputPanel.add(createButton("Filter by Genre", this::filterByGenre), gbc(1, y++));
        inputPanel.add(createButton("Show All Movies", this::refreshMovieList), gbc(0, y));
        inputPanel.add(createButton("Average Rating", this::displayAverageRating), gbc(1, y++));
        inputPanel.add(createButton("Save", this::saveLibrary), gbc(0, y));
        inputPanel.add(createButton("Load", this::loadLibrary), gbc(1, y++));

        add(inputPanel, BorderLayout.NORTH);
    }

    // EFFECTS: returns a JLabel with the given text styled in white foreground
    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    // MODIFIES: this
    // EFFECTS: sets up the movie list display and remove button
    private void setupMovieList() {
        movieListModel = new DefaultListModel<>();
        movieList = new JList<>(movieListModel);
        movieList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        movieList.setBackground(new Color(24, 26, 34));
        movieList.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(movieList);
        scrollPane.getViewport().setBackground(new Color(24, 26, 34));
        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(24, 26, 34));
        southPanel.add(createButton("Remove Selected Movie", this::removeSelectedMovie));
        add(southPanel, BorderLayout.SOUTH);
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: handles Add Movie button click
    private void handleAddMovie() {
        String title = titleField.getText();
        String genre = (String) genreField.getSelectedItem();
        int rating;

        try {
            rating = Integer.parseInt(ratingField.getText());
            if (rating < 1 || rating > 5) {
                JOptionPane.showMessageDialog(this, "Rating must be between 1 and 5.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Rating must be an integer.");
            return;
        }

        String comment = commentField.getText();
        if (title.isEmpty() || comment.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        Movie movie = new Movie(title, genre, rating, comment);
        library.addMovie(movie);
        movieListModel.addElement(movie.toDisplayString());

        titleField.setText("");
        ratingField.setText("");
        commentField.setText("");
        genreField.setSelectedIndex(0);
    }

    // MODIFIES: this
    // EFFECTS: filters movies in library by selected genre and updates display
    private void filterByGenre() {
        String selectedGenre = (String) genreField.getSelectedItem();
        displayMovies(library.getMoviesByGenre(selectedGenre));
    }

    // MODIFIES: this
    // EFFECTS: refreshes the movie list display with all movies in the library
    private void refreshMovieList() {
        displayMovies(library.getAllMovies());
    }

    // MODIFIES: this
    // EFFECTS: removes selected movie from library and updates display
    private void removeSelectedMovie() {
        int selectedIndex = movieList.getSelectedIndex();
        if (selectedIndex != -1) {
            String entry = movieListModel.getElementAt(selectedIndex);
            String title = entry.substring(0, entry.indexOf(" ("));
            if (library.removeMovie(title)) {
                movieListModel.remove(selectedIndex);
                JOptionPane.showMessageDialog(this, "Movie '" + title + "' removed.");
            } else {
                JOptionPane.showMessageDialog(this, "Movie not found in library.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a movie to remove.");
        }
    }

    // MODIFIES: file at JSON_STORE
    // EFFECTS: saves the movie library to JSON_STORE
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved movies to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movie library from JSON_STORE
    private void loadLibrary() {
        try {
            library = jsonReader.read();
            refreshMovieList();
            JOptionPane.showMessageDialog(this, "Loaded movies from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays average rating of movies in library
    private void displayAverageRating() {
        double avgRating = library.getAverageRating();
        String message = (avgRating == 0.0)
                ? "No movies in the library."
                : "Average Rating: " + String.format("%.2f", avgRating);
        JOptionPane.showMessageDialog(this, message);
    }

    // MODIFIES: this
    // EFFECTS: displays a given list of movies
    private void displayMovies(List<Movie> movies) {
        movieListModel.clear();
        for (Movie m : movies) {
            movieListModel.addElement(m.toDisplayString());
        }
    }
}