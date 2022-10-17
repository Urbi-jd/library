package io;

import model.Book;
import model.LibraryUser;
import model.Magazine;
import model.Publication;

import java.util.Collection;

public class ConsolePrinter {


    public void printBooks(Collection<Publication> publications) {
        publications.stream()
                .filter(publication -> publication instanceof Book)
                .forEach(pub -> printLine(pub.toString()));
    }

    public void printMagazines(Collection<Publication> publications) {
        for (Publication publication : publications) {
            if(publication instanceof Magazine)
                printLine(publication.toString());
        }
    }

    public void printAll(Collection<Publication> publications) {
        for (Publication publication : publications) {
            printLine(publication.toString());
        }
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

