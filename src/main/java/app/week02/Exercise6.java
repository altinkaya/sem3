package app.week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import lombok.Data;

public class Exercise6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<String>> futures = new ArrayList<>();
        List<String> urls = List.of(
                "https://pokeapi.co/api/v2/pokemon/ditto",
                "https://icanhazdadjoke.com/",
                "https://api.chucknorris.io/jokes/random",
                "https://api.kanye.rest",
                "https://api.whatdoestrumpthink.com/api/v1/quotes/random",
                "https://api.agify.io/?name=ahmad",
                "https://dog.ceo/api/breeds/image/random",
                "https://restcountries.com/v3.1/name/India?fullText=true",
                "https://catfact.ninja/fact",
                "https://www.boredapi.com/api/activity"
        );

        for (String url : urls) {
            Future<String> future = executorService.submit(() -> getConnection(url));
            futures.add(future);
        }

        MegaDTO megaDTO = new MegaDTO();
        for (Future<String> future : futures) {
            String response = future.get();
            // Parse the response into a DTO and add it to the mega DTO
            // This depends on the structure of the response and the DTOs
        }

        System.out.println(megaDTO);

        executorService.shutdown();
    }

    private static String getConnection(String urlStr) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    @Data
    class PokemonDTO {
        private String name;
        private int baseExperience;
        private int height;
        private int weight;
        private String locationAreaEncounters;
    }

    static class MegaDTO {
        // Fields for the data from the different APIs
    }
}
