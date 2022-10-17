package model;

import java.util.Objects;

public class Magazine extends Publication{

    public static final String TYPE = "Magazyn";

    private int relaeaseMonth;
    private int releaseDay;
    private String language;

    public Magazine(String title, String publisher, int releaseYear, int relaeaseMonth, int releaseDay, String language){
        super(title, publisher, releaseYear);
        this.relaeaseMonth = relaeaseMonth;
        this.releaseDay = releaseDay;
        this.language = language;
    }

    public Magazine(String title, String publisher, int releaseYear, int relaeaseMonth, int releaseDay, String language, int quantity) {
        super(releaseYear, title, publisher, quantity);
        this.relaeaseMonth = relaeaseMonth;
        this.releaseDay = releaseDay;
        this.language = language;
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                getPublisher() + ";" +
                getReleaseYear() + ";" +
                relaeaseMonth + ";" +
                releaseDay + ";" +
                language + ";" +
                getQuantity();
    }

    @Override
    public String toString() {
        return  "Magazyn: " +
                "Tytuł: " + getTitle() +
                ", wydawca: " + getPublisher() +
                ", data wydania: " + getReleaseYear() +
                "/" + relaeaseMonth +
                "/" + releaseDay +
                ", język: " + language +
                ", ilość: " + getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return relaeaseMonth == magazine.relaeaseMonth && releaseDay == magazine.releaseDay && Objects.equals(language, magazine.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), relaeaseMonth, releaseDay, language);
    }
}
