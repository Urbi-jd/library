package app;

import exceptions.DataImportException;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManagerBuilder;
import model.Book;
import model.Library;
import model.Magazine;

public class LibraryApp {
    private static final String APP_NAME = "Biblioteka v1.8";

    public static void main(String[] args) {
        System.out.println(APP_NAME);

        LibraryControl libControl = new LibraryControl();

        libControl.contlolLoop();


    }
}
