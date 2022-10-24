package io;

import model.Book;
import model.LibraryUser;
import model.Magazine;
import model.Publication;

import java.util.Collection;
import java.util.Objects;

 public class ConsolePrinter {


    public long printBooks(Collection<Publication> publications) {
        long count = publications.stream()
                .filter(publication -> publication instanceof Book)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if(count == 0)
            printLine("Brak magazynów do wyświetlenia");
        return count;
    }

    public long printMagazines(Collection<Publication> publications) {
//        for (Publication publication : publications) {
//            if(publication instanceof Magazine)
//                printLine(publication.toString());
//        }
        long count = publications.stream()
                .filter(p -> p instanceof Magazine)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if(count == 0)
            printLine("Brak książek do wyświetlenia");
        return count;
    }

    public long printAll(Collection<Publication> publications) {

        long count = publications.stream()
                .filter(Objects::nonNull)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if(count == 0)
            printLine("Brak książek do wyświetlenia");
        return count;


    }

    public void printUsers(Collection<LibraryUser> libraryUsers){
        for (LibraryUser libraryUser : libraryUsers) {
            printLine(libraryUser.toString());
        }
    }

    public void printLine(String text){
        System.out.println(text.toUpperCase());
    }
}

