# MyMovieShelf  
## A Personal Movie Tracking Logging & Rating Application


**MyMovieShelf** is a desktop Java application designed for users to log the movies they’ve watched, rate them, write personal notes, and organize them into custom collections. The application will begin as a console-based program and will eventually feature a graphical user interface. MyMovieShelf will support saving and loading the full application state, so users can return to their movie library at any time without losing data.

**This application is intended for anyone who wants a private, customizable way to track their movie-watching habits.** Instead of relying on public platforms like Letterboxd, MyMovieShelf provides users with a personalized and offline space to manage their film collection. Users can rate movies, record personal thoughts or reviews, and sort or group films in meaningful ways and view films they have watched sorted by rating, genre etc.

## Instructions for End User

- **You can generate the first required action related to the user story "adding multiple Xs to a Y" by**:
  - Clicking the **"Add Movie"** button after entering the movie details (Title, Genre, Rating, and Comment) into the provided fields. Each click will add a movie to the library.

- **You can generate the second required action related to the user story "adding multiple Xs to a Y" by**:
  - Clicking the **"Filter by Genre"** button after selecting a genre from the dropdown. This action will display only the movies in the library that match the selected genre.

- **You can locate my visual component by**:
  - Viewing the **splash screen** when the application starts. The splash screen displays the title "Movie Tracker" and a background image associated with the app.

- **You can save the state of my application by**:
  - Clicking the **"Save"** button. This will save the current movie library to a file named `movies.json`.

- **You can reload the state of my application by**:
  - Clicking the **"Load"** button. This will reload the saved movie library from the `movies.json` file, displaying the movies that were previously saved.

### Additional Instructions:

### 1. Add a Movie to the Library
- Enter the movie details (Title, Genre, Rating, and Comment) into the provided text fields.
- Click the **Add Movie** button to add the movie to the library.
- The movie will appear in the list displayed below.

### 2. Filter Movies by Genre
- Select a genre from the **Genre** dropdown menu.
- Click the **Filter by Genre** button to display only the movies of the selected genre.

### 3. Show All Movies
- To display all movies in the library (regardless of genre), click the **Show All Movies** button.

### 4. Calculate Average Rating
- Click the **Average Rating** button to calculate and display the average rating of all movies in the library.

### 5. Save the State of the Application
- Click the **Save** button to save the current movie library to a file.
- The movie library will be saved to a file named `movies.json`.

### 6. Load the State of the Application
- Click the **Load** button to load the movie library from the saved file.
- The previously saved movies will be displayed in the list.

### 7. Remove a Movie from the Library
- Select a movie from the list.
- Click the **Remove Selected Movie** button to remove the selected movie from the library.

