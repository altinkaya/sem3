package app.week01;

import app.week01.Book;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Opg6 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Book1", "Author1", 2000, 4.5, 300),
                new Book("Book2", "Author2", 2005, 4.0, 350),
                new Book("Book3", "Author1", 2010, 4.7, 400),
                new Book("Book4", "Author3", 2015, 4.2, 450),
                new Book("Book5", "Author2", 2020, 4.8, 500)
        );

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
    }
}
