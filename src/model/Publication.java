package model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication implements Serializable, Comparable<Publication>, CsvConvertible {


    @Override
    public int compareTo(Publication p){
        return title.compareToIgnoreCase(p.title);
    }
    private int releaseYear;
    private String title;
    private String publisher;

    private int quantity;

    public Publication(String title, String publisher, int releaseYear) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.publisher = publisher;
    }

    public Publication(int releaseYear, String title, String publisher, int quantity) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getYear() {
        return releaseYear;
    }

    public void setYear(int releaseDate) {
        this.releaseYear = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return title + ", " + publisher + ", " + releaseYear + ", " + quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return releaseYear == that.releaseYear && Objects.equals(title, that.title) && Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(releaseYear, title, publisher);
    }
}


