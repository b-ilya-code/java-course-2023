package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    public void testParseContactsInNaturalOrder() {
        String[] contacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};

        Contact[] contactsSorted = Task5.parseContacts(contacts, "ASC");

        // Aquinas (A) < Descartes (D) < Hume (H) < Locke (L)
        assertThat(contactsSorted[0]).isEqualTo(new Contact("Thomas", "Aquinas"));
        assertThat(contactsSorted[1]).isEqualTo(new Contact("Rene", "Descartes"));
        assertThat(contactsSorted[2]).isEqualTo(new Contact("David", "Hume"));
        assertThat(contactsSorted[3]).isEqualTo(new Contact("John", "Locke"));
    }

    @Test
    public void testParseContactsInReversedOrder() {
        String[] contacts = new String[] {"Paul Erdos", "Carl Gauss", "Leonhard Euler"};

        Contact[] contactsSorted = Task5.parseContacts(contacts, "DESC");

        // Gauss (G) > Erdos (ER) > Euler (EU)
        assertThat(contactsSorted[0]).isEqualTo(new Contact("Carl", "Gauss"));
        assertThat(contactsSorted[1]).isEqualTo(new Contact("Leonhard", "Euler"));
        assertThat(contactsSorted[2]).isEqualTo(new Contact("Paul", "Erdos"));
    }

    @Test
    public void testParseContactsWithNoSurname() {
        String[] contacts = new String[] {"Paul", "Carl Gauss", "Leonhard Euler"};

        Contact[] contactsSorted = Task5.parseContacts(contacts, "DESC");

        // Paul (P) > Gauss (G) > Euler (EU)
        assertThat(contactsSorted[0]).isEqualTo(new Contact("Paul", ""));
        assertThat(contactsSorted[1]).isEqualTo(new Contact("Carl", "Gauss"));
        assertThat(contactsSorted[2]).isEqualTo(new Contact("Leonhard", "Euler"));
    }

    @Test
    public void testParseContactsWithEmptyInput() {
        String[] contacts = new String[] {};

        Contact[] contactsSorted = Task5.parseContacts(contacts, "ASC");

        assertThat(contactsSorted.length).isEqualTo(0);
    }
}
