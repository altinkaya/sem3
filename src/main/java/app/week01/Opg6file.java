package app.week01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Opg6file {
    public static void main(String[] args) {
        String fileName = "src/main/java/app/week01/book.txt";

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<Book> books = lines.map(line -> {
                String[] details = line.split(","); // assuming data is comma-separated
                return new Book(
                        details[0].trim(),
                        details[1].trim(),
                        Integer.parseInt(details[2].trim()),
                        Double.parseDouble(details[3].trim()),
                        Integer.parseInt(details[4].trim())
                );
            }).collect(Collectors.toList());

            double averageRating = books.stream()
                    .mapToDouble(Book::getRating)
                    .average()
                    .orElse(0.0);
            System.out.println("Average rating: " + averageRating);

            int year = 2010;
            List<Book> booksPublishedAfterYear = books.stream()
                    .filter(book -> book.getPublicationYear() > year)
                    .collect(Collectors.toList());
            System.out.println("Books published after " + year + ": " + booksPublishedAfterYear);

            List<Book> booksSortedByRating = books.stream()
                    .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                    .collect(Collectors.toList());
            System.out.println("Books sorted by rating: " + booksSortedByRating);

            Book highestRatedBook = books.stream()
                    .max(Comparator.comparingDouble(Book::getRating))
                    .orElse(null);
            System.out.println("Highest rated book: " + highestRatedBook.getTitle());

            Map<String, Double> averageRatingByAuthor = books.stream()
                    .collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingDouble(Book::getRating)));
            System.out.println("Average rating by author: " + averageRatingByAuthor);

            int totalPages = books.stream()
                    .mapToInt(Book::getPages)
                    .sum();
            System.out.println("Total number of pages: " + totalPages);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
