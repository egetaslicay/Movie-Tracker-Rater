# MyMovieShelf  
## A Personal Movie Tracking Logging & Rating Application


**MyMovieShelf** is a desktop Java application designed for users to log the movies they’ve watched, rate them, write personal notes, and organize them into custom collections. The application will begin as a console-based program and will eventually feature a graphical user interface. MyMovieShelf will support saving and loading the full application state, so users can return to their movie library at any time without losing data.

**This application is intended for anyone who wants a private, customizable way to track their movie-watching habits.** Instead of relying on public platforms like Letterboxd, MyMovieShelf provides users with a personalized and offline space to manage their film collection. Users can rate movies, record personal thoughts or reviews, and sort or group films in meaningful ways and view films they have watched sorted by rating, genre etc.

I chose this project because I'm passionate about movies and often find myself rewatching films without remembering what I originally thought of them. I’ve always wanted a lightweight, offline tool where I can reflect on what I’ve watched and organize movies my own way. This project also gives me the opportunity to create something genuinely useful to me, while practicing clean object-oriented design and working with saving/loading state all of which will help me grow as a developer.
---

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


## User Stories

**Implemented so far for phase 1** 

- As a user, I want to add a movie to my collection with a title, genre, rating, and personal comment.
- As a user, I want to view a list of all the movies I’ve logged.
- As a user, I want to remove a movie from my collection.
- As a user, I want to view all movies within a specific genre.
- As a user, I want to see the average rating of all the movies I’ve watched.
- As a user, I want to search for a movie in my collection by title.

--------------------------------------------------------------------------------------- 

**Phase 2 user stories** 

- As a user, I want to be able to save the collection of movies I have inputted (If I choose to do so). 
- As a user, I want to be able to load in a saved collection of movies (If I choose to do so). 
- As a user, when quitting the program I would like to be reminded that I can save the collection of movies if I choose to do so. 

-------------------------------------------------------------------------------------------
## Phase 4: Task 2
## Representative sample of logged events: (Timestamps removed since I don't believe its necessary or looks good). 

- Created a new MovieLibrary
- Added movie: Inception
- Added movie: The Dark Knight
- Added movie: Titanic
- Filtered movies by genre: Action (2 match)
- Calculated average rating: 4.67
- Removed movie: Titanic
- Viewed all movies
- Converted MovieLibrary to JSON with 2 movies
- Movie not found by title: Avatar

-------------------------------------------------------------------------------------------
## Phase 4: Task 3 Refactoring changes / Paragraph 

If I had more time, I would refactor the MovieAppGUI class to improve separation of concerns. Right now, the class handles both the user interface and a lot of the core application logic. For example, it creates Movie objects in handleAddMovie, checks rating inputs, filters movies by genre, removes movies from the library, and even saves and loads data through JsonWriter and JsonReader. Because of this, the GUI is tightly connected to business logic and persistence, which makes the code harder to maintain, test, and extend. A better design would move this non‑UI logic into a dedicated controller class, so the GUI could focus only on presentation and user interactions while the controller handled the operations.

I would also look at reducing the duplicated logic between MovieApp (the console version) and MovieAppGUI. Both currently include similar features for adding, removing, and showing movies. This duplication mostly comes from the project’s design of first building a console UI and later upgrading to a GUI. Still, adding a controller would help solve this problem too, since both UIs could share the same underlying logic. That would make the system more consistent and easier to update in the future.








-------------------------------------------------------------------------------------------
**Citation:**

- Credit to Paul Carter. My persistence implementation / Phase 2 was modeled after the given sample authored by him:
- JSON Serialization Demo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo 

