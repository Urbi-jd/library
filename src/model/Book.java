package model;

import java.util.Objects;

public class Book extends Publication {
    public static final String TYPE = "Książka";
    private String author;
    private int pages;
    private String isbn;

    public Book(String title, String author, int releaseDate, int pages, String publisher, String isbn) {
        this(title, author, releaseDate, pages, publisher);
        this.isbn = isbn;
    }

    public Book(String title, String author, int releaseDate, int pages, String publisher) {
        super(title, publisher, releaseDate);
        this.author = author;
        this.pages = pages;
    }

    public Book(String title, String author, int releaseDate, int pages, String publisher, String isbn, int quantity) {
        super(releaseDate, title, publisher, quantity);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                getPublisher() + ";" +
                getYear() + ";" +
                author + ";" +
                pages + ";" +
                isbn + ";" +
                getQuantity();

    }

    @Override
    public String toString() {
        String info = "tytuł: " + getTitle() +
                ", autor: " + author +
                ", data wydania: " + getReleaseYear() +
                ", ilość stron: " + pages +
                ", wydawca: " + getPublisher() +
                ", rok wydania: " + getYear();
        if (isbn != null) {
            info += ", isbn: " + isbn;
        }
        info += ", ilość szt: " + getQuantity();
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return pages == book.pages && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, pages, isbn);
    }
}
