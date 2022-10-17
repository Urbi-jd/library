package io;

import model.Book;
import model.LibraryUser;
import model.Magazine;

import java.util.Scanner;

public class DataReader {
    Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public String getString() {
        return sc.nextLine();
    }

    public void scClose() {
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public Book readAndCreateBook() {
        printer.printLine("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Autor: ");
        String author = sc.nextLine();
        printer.printLine("Wydawnictwo: ");
        String publisher = sc.nextLine();
        printer.printLine("ISBN: ");
        String isbn = sc.nextLine();
        printer.printLine("Rok wydania: ");
        int releaseDate = getInt();
        printer.printLine("Ilość stron: ");
        int pages = getInt();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Wydawca: ");
        String publisher = sc.nextLine();
        printer.printLine("Rok wydania: ");
        int releaseYear = getInt();
        printer.printLine("Miesiąc wydania: ");
        int releaseMonth = getInt();
        printer.printLine("Dzień wydania: ");
        int releaseDay = getInt();
        printer.printLine("język: ");
        String language = sc.nextLine();

        return new Magazine(title, publisher, releaseYear, releaseMonth, releaseDay, language);
    }

    public LibraryUser readAndCreateLibraryUser(){
        printer.printLine("Podaj imię urzytkownika");
        String firstName = sc.nextLine();
        printer.printLine("Podaj nazwisko urzytkownika");
        String lastName = sc.nextLine();
        printer.printLine("Podaj PESEL urzytkownika");
        String pesel = sc.nextLine();

        return new LibraryUser(firstName, lastName, pesel);
    }


}
