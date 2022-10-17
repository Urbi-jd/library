package app;

import exceptions.DataExportException;
import exceptions.DataImportException;
import exceptions.NoSuchOptionException;
import exceptions.UserAlreadyExistsException;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import model.*;

import java.util.*;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);

    public FileManager fileManager;
    private Library library;


    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("zaimportowano dane z pliku");
        } catch (DataImportException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę");
            library = new Library();
        }
    }


    void contlolLoop() {
//         testing publications
//        library.addTestPublication();
//         end of testing publications


        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_MAGAZINE:
                    printMagazines();
                    break;
                case PRINT_ALL:
                    printAllPublications();
                    break;
                case DELETE_PUBLICATION:
                    deletePublicationByTitle();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USERS:
                    printUsers();
                    break;
                case EXIT:
                    exit();
                    break;

                default:
                    printer.printLine("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while (option != Option.EXIT);
    }


//    private List<LibraryUser> getLibraryUserList() {
//        List<LibraryUser> libraryUserList = new ArrayList<>();
//        Set<Map.Entry<String, LibraryUser>> entries = library.getUsers().entrySet();
//        for (Map.Entry<String, LibraryUser> entry : entries) {
//            libraryUserList.add(entry.getValue());
//        }
//        return libraryUserList;
//    }

    private void addUser() {
        LibraryUser user = dataReader.readAndCreateLibraryUser();
        try {
            library.addUser(user);
        } catch (UserAlreadyExistsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void deletePublicationByTitle() {
        printer.printLine("Podaj tytół pozycji do usunięcia");
        String title = dataReader.getString();
        Map<Integer, Publication> erasedPubMap = erasedPublicationsMap(title);
        Publication erasedPublication = searchPublications(erasedPubMap);
        if (erasedPublication != null) {
            library.removePublication(erasedPublication);
        } else
            printer.printLine("Nie znaleziono tytułu");
    }

    private Map<Integer, Publication> erasedPublicationsMap(String title) {
        int counter = 1;
        Map<Integer, Publication> searczedPublicationsMap = new HashMap<>();
        Set<Map.Entry<Integer, Publication>> foundEntries = searczedPublicationsMap.entrySet();
        Set<Map.Entry<String, Publication>> entries = library.getPublications().entrySet();
        for (Map.Entry<String, Publication> entry : entries) {
            if (entry.getKey().contains(title)) {
                searczedPublicationsMap.put(counter, entry.getValue());
                counter++;
            }
        }
        return searczedPublicationsMap;
    }

    private Publication searchPublications(Map<Integer, Publication> erasedPubMap) {
        Publication publication = null;
        if (!erasedPubMap.isEmpty()) {
            Set<Map.Entry<Integer, Publication>> foundEntries = erasedPubMap.entrySet();
            printer.printLine("Znaleziono następujące pozycje:");
            for (Map.Entry<Integer, Publication> foundEntry : foundEntries) {
                printer.printLine(foundEntry.getKey() + " " + foundEntry.getValue());
            }
            printer.printLine("Podaj numer pozycji do skasowania:");
            int option = dataReader.getInt();
            publication = erasedPubMap.get(option);
        }
        return publication;
    }

    private Option getOption() {
        boolean optionOK = false;
        Option option = null;
        while (!optionOK) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOK = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", podaj ponownie");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:");
            }
        }
        return option;
    }

    private void printOptions() {
        for (Option option : Option.values()) {
            printer.printLine(String.valueOf(option));
//            printer.printLine(option.value +  option.description.toUpperCase());
        }
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane");
        }
    }

    private Collection<Publication> getPublicationsList() {
        return library.getPublications().values();
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć magazynu, niepoprawne dane");
        }
    }

    private void printBooks() {
        printer.printBooks(getSortedPublications(Comparator.comparing(Publication::getTitle, String::compareToIgnoreCase)));
    }

    private void printMagazines() {
        printer.printMagazines(getSortedPublications(Comparator.comparing(Publication::getTitle, String::compareToIgnoreCase)));
    }

    private void printAllPublications() {
        printer.printAll(getSortedPublications(Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)));
    }

    private List<Publication> getSortedPublications(Comparator<Publication> comparator) {
        List<Publication> publicationList = new ArrayList<>(library.getPublications().values());
        publicationList.sort(comparator);
        return publicationList;
    }

    private void printUsers() {
        printer.printUsers(getSortedUsers(Comparator.comparing(User::getLastName)));
    }

    private List<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator) {
        List<LibraryUser> sortedList = new ArrayList<>(library.getUsers().values());
        sortedList.sort(comparator);
        return sortedList;
    }


    private void exit() {
        try {
            fileManager.exportData(library);
            printer.printLine("Eksport danych do pliku zakończony powodzeniem");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        System.out.println("Do widzenia");
        dataReader.scClose();
    }

    private enum Option {
        EXIT(0, " - wyjście z programu"),
        ADD_BOOK(1, " - dodanie nowej książki"),
        PRINT_BOOKS(2, " - wyświetl dostępne książki"),
        ADD_MAGAZINE(3, " - dodanie nowego magazynu"),
        PRINT_MAGAZINE(4, " - wyświetl dostępne magazyny"),
        PRINT_ALL(5, " - wyświetl wszystkie dostępne pozycje"),
        DELETE_PUBLICATION(6, " - skasuj pozycję wedłóg tytułu"),
        ADD_USER(7, " - dodaj czytelnika"),
        PRINT_USERS(8, " - wyświetl czytelników");

        private int value;
        private String description;

        public int getValue() {
            return value;
        }

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException exception) {
                throw new NoSuchOptionException("Brak opcji id: " + option);
            }

        }

        @Override
        public String toString() {
            return value + description;
        }
    }
}
