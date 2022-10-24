package model;

import java.time.MonthDay;

public class Magazine extends Publication {

    public static final String TYPE = "Magazyn";

    private MonthDay monthDay;
    private String language;

    public Magazine(String title, String publisher, int releaseYear, int relaeaseMonth, int releaseDay, String language) {
        super(title, publisher, releaseYear);
        this.monthDay = MonthDay.of(relaeaseMonth, releaseDay);
        this.language = language;
    }

    public Magazine(String title, String publisher, int releaseYear, int relaeaseMonth, int releaseDay, String language, int quantity) {
        super(releaseYear, title, publisher, quantity);
        this.monthDay = MonthDay.of(relaeaseMonth, releaseDay);
        this.language = language;
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                getPublisher() + ";" +
                getReleaseYear() + ";" +
                monthDay.getMonthValue() + ";" +
                monthDay.getDayOfMonth() + ";" +
                language + ";" +
                getQuantity();
    }

    @Override
    public String toString() {
        return "Magazyn: " +
                "Tytuł: " + getTitle() +
                ", wydawca: " + getPublisher() +
                ", data wydania: " + getReleaseYear() +
                "/" + monthDay.getMonthValue() +
                "/" + monthDay.getDayOfMonth() +
                ", język: " + language +
                ", ilość: " + getQuantity();
    }


}
