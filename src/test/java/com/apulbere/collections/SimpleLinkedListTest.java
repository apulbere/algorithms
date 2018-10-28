package com.apulbere.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleLinkedListTest {

    @Test
    public void shouldInsertAtTheEnd() {
        var linkedList = new SimpleLinkedList<String>();

        linkedList.append("A");
        linkedList.append("B");
        linkedList.append("C");

        assertEquals(3L, linkedList.size());
        assertEquals(List.of("A", "B", "C"), linkedList.toCollection(ArrayList::new));
    }

    @Test
    public void shouldInsertAtTheBeginning() {
        var linkedList = new SimpleLinkedList<String>();
        linkedList.append("T");
        linkedList.prepend("P");
        linkedList.prepend("O");

        assertEquals(List.of("O", "P", "T"), linkedList.toCollection(ArrayList::new));
    }

    @Test
    public void shouldNotFailForEmptyCase() {
        var linkedList = new SimpleLinkedList<String>();

        assertEquals(0L, linkedList.size());
        assertEquals(Collections.emptyList(), linkedList.toCollection(ArrayList::new));
    }

    @Test
    public void search() {
        var linkedList = new SimpleLinkedList<String>(List.of("Z", "Y", "Z"));

        assertTrue(linkedList.contains("Z"));
        assertTrue(linkedList.contains("Y"));
        assertTrue(linkedList.contains("Z"));
        assertFalse(linkedList.contains("U"));
    }

    @Test
    public void deleteOnAnEmptyList() {
        new SimpleLinkedList<String>().delete("67");
    }

    @Test
    public void delete() {
        var linkedList = new SimpleLinkedList<String>(List.of("Y", "P"));
        linkedList.delete("P");
        linkedList.delete("U");
        assertEquals(List.of("Y"), linkedList.toCollection(ArrayList::new));

        var secondLinkedList = new SimpleLinkedList<String>(List.of("Y"));
        secondLinkedList.delete("Y");
        linkedList.delete("OPP");
        assertEquals(Collections.emptyList(), Collections.emptyList());
    }
}
