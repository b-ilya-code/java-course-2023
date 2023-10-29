package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;

public class Task5 {
    private Task5() {
    }

    static final Comparator<Contact> ASC_CONTACT_COMPARATOR = (lhs, rhs) -> {
        String lhsCopy = lhs.surname().isEmpty() ? lhs.name() : lhs.surname();
        String rhsCopy = rhs.surname().isEmpty() ? rhs.name() : rhs.surname();
        return lhsCopy.compareTo(rhsCopy);
    };
    static final Comparator<Contact> DESC_CONTACT_COMPARATOR =
        ASC_CONTACT_COMPARATOR.reversed();

    public static Contact[] parseContacts(String[] names, String order) {
        ArrayList<Contact> contacts = new ArrayList<>();

        for (String name : names) {
            contacts.add(parseName(name));
        }

        if (order.equals("ASC")) {
            contacts.sort(ASC_CONTACT_COMPARATOR);
        } else if (order.equals("DESC")) {
            contacts.sort(DESC_CONTACT_COMPARATOR);
        }

        return contacts.toArray(new Contact[0]);
    }

    private static Contact parseName(String name) {
        String[] nameSurname = name.split(" ");
        if (nameSurname.length == 2) {
            return new Contact(nameSurname[0], nameSurname[1]);
        } else if (nameSurname.length == 1) {
            return new Contact(nameSurname[0], "");
        } else {
            return new Contact("", "");
        }
    }
}
