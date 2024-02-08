package app.week02;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class APIExercise {

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class MovieDTO {
        private String title;
        private String overview;
        @JsonProperty("release_date")
        private LocalDate releaseDate;
        private String releaseYear;
        private double rating;

    }

    public static class MovieService {
        private static final String API_KEY = "b94a765e9bf1141f8887913ee49920cb";
        private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

        private final OkHttpClient client;
        private final ObjectMapper mapper;

        public MovieService() {
            this.client = new OkHttpClient();
            this.mapper = new ObjectMapper();
            this.mapper.registerModule(new JavaTimeModule());
        }

        public MovieDTO getMovieByImdbId(String imdbId) throws IOException {
            Request request = new Request.Builder()
                    .url(BASE_URL + imdbId + "?api_key=" + API_KEY)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                String responseBody = response.body().string();
                MovieDTO movie = mapper.readValue(responseBody, MovieDTO.class);

                return movie;
            }
        }
    }

    public interface MediaController<T> {
        List<T> getByRating(double rating);
        List<T> getSortedByReleaseDate();
    }

    public static class MovieController implements MediaController<MovieDTO> {
        private final List<MovieDTO> movies;

        public MovieController(List<MovieDTO> movies) {
            this.movies = movies;
        }

        @Override
        public List<MovieDTO> getByRating(double rating) {
            return movies.stream()
                    .filter(movie -> movie.getRating() >= rating)
                    .collect(Collectors.toList());
        }

        @Override
        public List<MovieDTO> getSortedByReleaseDate() {
            return movies.stream()
                    .sorted(Comparator.comparing(MovieDTO::getReleaseDate).reversed())
                    .collect(Collectors.toList());
        }
    }
}
