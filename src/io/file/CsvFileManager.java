package io.file;

import exceptions.DataExportException;
import exceptions.DataImportException;
import exceptions.InvalidDataException;
import model.*;

import java.io.*;
import java.time.Year;
import java.util.Collection;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Library_users.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    private void importUsers(Library library) {
        try (
                FileReader fileReader = new FileReader(USERS_FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(library::addUser);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + USERS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu danych z pliku " + USERS_FILE_NAME);
        }
    }

    private void importPublications(Library library) {
        try (
                FileReader fileReader = new FileReader(FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            bufferedReader.lines()
                    .map(this::createPublicFromString)
                    .forEach(library::addPublication);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu danych z pliku " + FILE_NAME);
        }
    }

    private LibraryUser createUserFromString(String text) {
        String[] split = text.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        return new LibraryUser(firstName, lastName, pesel);
    }

    private Publication createPublicFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (type.equals(Book.TYPE)) {
            return crerateBook(split);
        } else if (Magazine.TYPE.equals(type))
            return createMagazine(split);
        else
            throw new InvalidDataException("Nieznany typ publikacji: " + type);
    }

    private Publication createMagazine(String[] split) {
        String title = split[1];
        String publisher = split[2];
        int releaseYear = Integer.parseInt(split[3]);
        int relaeaseMonth = Integer.parseInt(split[4]);
        int releaseDay = Integer.parseInt(split[5]);
        String language = split[6];
        int quantity = Integer.parseInt(split[7]);
        return new Magazine(title, publisher, releaseYear, relaeaseMonth, releaseDay, language, quantity);
    }

    private Publication crerateBook(String[] split) {
        String title = split[1];
        String publisher = split[2];
        int year = Integer.parseInt(split[3]);
        String author = split[4];
        int pages = Integer.parseInt(split[5]);
        String isbn = split[6];
        int quantity = Integer.parseInt(split[7]);
        return new Book(title, author, year, pages, publisher, isbn, quantity);
    }

    

    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications, FILE_NAME);

    }
    private void exportUsers(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCsv(users, USERS_FILE_NAME);

    }
    private <T extends CsvConvertible> void exportToCsv(Collection<T> collections, String filename) {
        try (
                var fileWriter = new FileWriter(filename);
                var bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (T element : collections) {
                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Brak pliku " + filename);
        }
    }
}
