package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Library implements Serializable {
    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }


    // testing publications
    Book excampleBok1 = new Book("Przykładowa książka 1", "Urbi", 2022, 1, "Urbi");
    Book excampleBok2 = new Book("Przykładowa książka 2", "Urbi", 2021, 11, "Urbix");
    Magazine excampleMagazine1 = new Magazine("Przykładowy magazyn 1", "Urbinox", 2022, 05, 28, "polski");

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < publicationsNumber; i++) {
//            sb.append(publications[i] + "\n");
//        }
//        return sb.toString();
//    }


    public void addTestPublication() {
        addPublication(excampleBok1);
        addPublication(excampleBok1);
        addPublication(excampleBok1);
        addPublication(excampleBok1);
        addPublication(excampleBok2);
        addPublication(excampleBok2);
        addPublication(excampleMagazine1);
    }

    // end of testing publications

    public void addUser(LibraryUser user) {
        if (users.containsKey(user.getPesel())) {
            throw new UnsupportedOperationException("Użytkownik " + user + " już isnieje");
        }
        users.put(user.getPesel(), user);

    }


    public void addPublication(Publication publication) {
        String key = getKey(publication);
        if (!publications.containsKey(key)) {
            if(publication.getQuantity() == 0)
            publication.setQuantity(1);
            publications.put(key, publication);
        } else {
            Publication tempPub = publications.get(key);
            tempPub.setQuantity(tempPub.getQuantity() + 1);
            publications.put(key, tempPub);
        }
    }

    private String getKey(Publication publication) {
        return publication.getTitle();
    }

    public void removePublication(Publication publication) {
        String key = getKey(publication);
        if (publications.containsKey(key)) {
            Set<Map.Entry<String, Publication>> entries = publications.entrySet();
            for (Map.Entry<String, Publication> entry : entries) {
                if (entry.getKey().equals(publication.getTitle())) {
                    if (publication.getQuantity() > 1) {
                        publication.setQuantity(publication.getQuantity() - 1);
                        entry.setValue(publication);
                    } else
                        publications.remove(key);
                }
            }
        }
    }
}