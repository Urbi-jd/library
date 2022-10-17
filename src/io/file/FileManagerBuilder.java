package io.file;

import exceptions.NoSuchFileTypeException;
import io.ConsolePrinter;
import io.DataReader;

import java.util.Arrays;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader dataReader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader dataReader) {
        this.printer = printer;
        this.dataReader = dataReader;
    }

    public FileManager build() {
        printer.printLine("Wybierz format danych:");
        FileType fileType = getFileType();
        switch (fileType) {
            case SERIAL:
                return new SeriazibleFileManager();

            case CSV:
                return new CsvFileManager();

            default:
                throw new NoSuchFileTypeException("Nieobsługiwany typ danych");
        }
    }

    private FileType getFileType() {
        boolean error = true;
        FileType resoult = null;
        while (error) {
            printTypes();
            String value = dataReader.getString().toUpperCase();
            try {
                resoult = FileType.valueOf(value);
                error = false;
            } catch (IllegalArgumentException e) {
                printer.printLine("Nieobsługiwany typ danych, wybierz ponownie.");
            }
        }
        return resoult;
    }

    private void printTypes() {
        printer.printLine("Podaj nazwę typu:");
        printer.printLine(Arrays.toString(FileType.values()));
    }
}
