package app.week01;

import java.util.List;
import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double rating;
    private int pages;

    public Book(String title, String author, int publicationYear, double rating, int pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = rating;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public double getRating() {
        return rating;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", rating=" + rating +
                ", pages=" + pages +
                '}';
    }
}
