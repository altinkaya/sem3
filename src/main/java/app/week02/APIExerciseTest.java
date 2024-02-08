package app.week02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class APIExerciseTest {
    @Test
    public void testTheGodfather() throws IOException {
        APIExercise.MovieService movieService = new APIExercise.MovieService();
        APIExercise.MovieDTO movie = movieService.getMovieByImdbId("tt0068646"); // IMDB id for The Godfather

        APIExercise.MovieController movieController = new APIExercise.MovieController(Collections.singletonList(movie));

        List<APIExercise.MovieDTO> highRatedMovies = movieController.getByRating(8.5);
        System.out.println(movie.getTitle()); // print the title of the fetched movie
        System.out.println(highRatedMovies.size()); // print the size of the highRatedMovies list
        assertEquals(1, highRatedMovies.size()); // check if the list has only one movie

        List<APIExercise.MovieDTO> sortedMovies = movieController.getSortedByReleaseDate();
        assertEquals(1, sortedMovies.size());
        assertEquals("The Godfather", sortedMovies.get(0).getTitle());
    }
    // Add similar test methods for the other movie titles
}
