package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibraryUser extends User {


    private List<Publication> publicationHistory = new ArrayList<>();
    private List<Publication> borrowedPublications = new ArrayList<>();

    @Override
    public String toCsv() {
        return getFirstName() + ";" +
                getLastName() + ";" +
                getPesel();
    }

    public LibraryUser(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }

    public void setPublicationHistory(List<Publication> publicationHistory) {
        this.publicationHistory = publicationHistory;
    }

    public void setBorrowedPublications(List<Publication> borrowedPublications) {
        this.borrowedPublications = borrowedPublications;
    }

    public List<Publication> getPublicationHistory() {
        return publicationHistory;
    }

    public List<Publication> getBorrowedPublications() {
        return borrowedPublications;
    }

    private void addPublicationToHistory(Publication publication) {
        publicationHistory.add(publication);
    }

    public void borrowedPublication(Publication publication) {
        borrowedPublications.add(publication);
    }

    public boolean returnPublication(Publication publication) {
        if (borrowedPublications.contains(publication)) {
            borrowedPublications.remove(publication);
            publicationHistory.add(publication);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LibraryUser that = (LibraryUser) o;
        return Objects.equals(publicationHistory, that.publicationHistory) && Objects.equals(borrowedPublications, that.borrowedPublications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationHistory, borrowedPublications);
    }
}
