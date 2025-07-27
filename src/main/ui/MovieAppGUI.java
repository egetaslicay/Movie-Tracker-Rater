package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Movie;
import model.MovieLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MovieAppGUI extends JFrame implements ActionListener {
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
    }

    private void showTitleScreen() {
        JPanel splashPanel = new JPanel();
        splashPanel.setLayout(new BoxLayout(splashPanel, BoxLayout.Y_AXIS));
        splashPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        splashPanel.setBackground(new Color(24, 26, 34));
      
        try {
            BufferedImage splashImage = ImageIO.read(new File(IMAGE_PATH));
            if (splashImage == null) {
                throw new IOException("ImageIO.read returned null – invalid format?");
            }
            Image scaledImage = splashImage.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            splashPanel.add(imageLabel);
            splashPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        } catch (IOException e) {
            JLabel errorLabel = new JLabel("Failed to load image");
            errorLabel.setForeground(Color.RED);
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            splashPanel.add(errorLabel);
            System.err.println("Image error: " + e.getMessage());
        }

        JLabel title = new JLabel("🎬 Movie Tracker");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        splashPanel.add(title);
        splashPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton startButton = new JButton("Enter Library");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        startButton.setBackground(new Color(102, 0, 204));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            getContentPane().removeAll();
            setupInputPanel();
            setupMovieList();
            revalidate();
            repaint();
        });

        splashPanel.add(startButton);
        add(splashPanel, BorderLayout.CENTER);
    }

    private GridBagConstraints gbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    private void setupInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(34, 36, 44));

        titleField = new JTextField();
        ratingField = new JTextField();
        commentField = new JTextField();
        genreField = new JComboBox<>(new String[]{
                "Action", "Drama", "Comedy", "Horror", "Sci-Fi", "Romance", "Thriller"
        });

        JButton addButton = new JButton("Add Movie");
        JButton filterButton = new JButton("Filter by Genre");
        JButton showAllButton = new JButton("Show All Movies");
        JButton avgRatingButton = new JButton("Average Rating");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        addButton.addActionListener(this);
        filterButton.addActionListener(e -> filterByGenre());
        showAllButton.addActionListener(e -> refreshMovieList());
        saveButton.addActionListener(e -> saveLibrary());
        loadButton.addActionListener(e -> loadLibrary());
        avgRatingButton.addActionListener(e -> displayAverageRating());

        int y = 0;

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.WHITE);
        inputPanel.add(titleLabel, gbc(0, y));
        inputPanel.add(titleField, gbc(1, y++));

        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setForeground(Color.WHITE);
        inputPanel.add(genreLabel, gbc(0, y));
        inputPanel.add(genreField, gbc(1, y++));

        JLabel ratingLabel = new JLabel("Rating (1–5):");
        ratingLabel.setForeground(Color.WHITE);
        inputPanel.add(ratingLabel, gbc(0, y));
        inputPanel.add(ratingField, gbc(1, y++));

        JLabel commentLabel = new JLabel("Comment:");
        commentLabel.setForeground(Color.WHITE);
        inputPanel.add(commentLabel, gbc(0, y));
        inputPanel.add(commentField, gbc(1, y++));

        inputPanel.add(addButton, gbc(0, y));
        inputPanel.add(filterButton, gbc(1, y++));
        inputPanel.add(showAllButton, gbc(0, y));
        inputPanel.add(avgRatingButton, gbc(1, y++));
        inputPanel.add(saveButton, gbc(0, y));
        inputPanel.add(loadButton, gbc(1, y++));

        add(inputPanel, BorderLayout.NORTH);
    }

    private void setupMovieList() {
        movieListModel = new DefaultListModel<>();
        movieList = new JList<>(movieListModel);
        movieList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        movieList.setBackground(new Color(24, 26, 34));
        movieList.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(movieList);
        scrollPane.getViewport().setBackground(new Color(24, 26, 34));
        add(scrollPane, BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove Selected Movie");
        removeButton.setBackground(new Color(102, 0, 204));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(e -> removeSelectedMovie());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(24, 26, 34));
        southPanel.add(removeButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
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
        movieListModel.addElement(title + " (" + genre + ", Rating: " + rating + ") - " + comment);

        titleField.setText("");
        ratingField.setText("");
        commentField.setText("");
        genreField.setSelectedIndex(0);
    }

    private void filterByGenre() {
        String selectedGenre = (String) genreField.getSelectedItem();
        java.util.List<Movie> filtered = library.getMoviesByGenre(selectedGenre);
        movieListModel.clear();
        for (Movie m : filtered) {
            movieListModel.addElement(m.getTitle() + " (" + m.getGenre()
                    + ", Rating: " + m.getRating() + ") - " + m.getComment());
        }
    }

    private void refreshMovieList() {
        movieListModel.clear();
        for (Movie m : library.getAllMovies()) {
            movieListModel.addElement(m.getTitle() + " (" + m.getGenre()
                    + ", Rating: " + m.getRating() + ") - " + m.getComment());
        }
    }

    private void removeSelectedMovie() {
        int selectedIndex = movieList.getSelectedIndex();
        if (selectedIndex != -1) {
            String entry = movieListModel.getElementAt(selectedIndex);
            String title = entry.substring(0, entry.indexOf(" ("));
            boolean removed = library.removeMovie(title);
            if (removed) {
                movieListModel.remove(selectedIndex);
                JOptionPane.showMessageDialog(this, "Movie '" + title + "' removed.");
            } else {
                JOptionPane.showMessageDialog(this, "Movie not found in library.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a movie to remove.");
        }
    }

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

    private void loadLibrary() {
        try {
            library = jsonReader.read();
            refreshMovieList();
            JOptionPane.showMessageDialog(this, "Loaded movies from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    private void displayAverageRating() {
        double avgRating = library.getAverageRating();
        if (avgRating == 0.0) {
            JOptionPane.showMessageDialog(this, "No movies in the library.");
        } else {
            JOptionPane.showMessageDialog(this, "Average Rating: " + String.format("%.2f", avgRating));
        }
    }
}