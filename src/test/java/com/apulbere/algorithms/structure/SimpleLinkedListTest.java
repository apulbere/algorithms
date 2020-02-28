package com.apulbere.algorithms.structure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.apulbere.algorithms.structure.SimpleLinkedList.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleLinkedListTest {

    @Test
    void shouldInsertAtTheEnd() {
        var linkedList = new SimpleLinkedList<String>();

        linkedList.append("A");
        linkedList.append("B");
        linkedList.append("C");

        assertEquals(3L, linkedList.size());
        assertEquals(List.of("A", "B", "C"), linkedList.toCollection(ArrayList::new));
    }

    @Test
    void shouldInsertAtTheBeginning() {
        var linkedList = new SimpleLinkedList<String>();
        linkedList.append("T");
        linkedList.prepend("P");
        linkedList.prepend("O");

        assertEquals(List.of("O", "P", "T"), linkedList.toCollection(ArrayList::new));
    }

    @Test
    void shouldNotFailForEmptyCase() {
        var linkedList = new SimpleLinkedList<String>();

        assertEquals(0L, linkedList.size());
        assertEquals(Collections.emptyList(), linkedList.toCollection(ArrayList::new));
    }

    @Test
    void search() {
        var linkedList = new SimpleLinkedList<String>(List.of("Z", "Y", "Z"));

        assertTrue(linkedList.contains("Z"));
        assertTrue(linkedList.contains("Y"));
        assertTrue(linkedList.contains("Z"));
        assertFalse(linkedList.contains("U"));
    }

    @Test
    void deleteOnAnEmptyList() {
        new SimpleLinkedList<String>().delete("67");
    }

    @Test
    void delete() {
        var linkedList = new SimpleLinkedList<>(List.of("Y", "P"));
        assertTrue(linkedList.delete("P"));
        assertEquals(1, linkedList.size());

        assertFalse(linkedList.delete("U"));
        assertEquals(1, linkedList.size());

        assertEquals(List.of("Y"), linkedList.toCollection(ArrayList::new));

        assertTrue(linkedList.delete("Y"));
        assertEquals(0, linkedList.size());

        assertFalse(linkedList.delete("Y"));
        assertEquals(0, linkedList.size());

        var secondLinkedList = new SimpleLinkedList<>(List.of("Y"));
        assertEquals(1, secondLinkedList.size());
        assertTrue(secondLinkedList.delete("Y"));
        assertEquals(0, linkedList.size());

        assertFalse(linkedList.delete("OPP"));

        assertEquals(Collections.emptyList(), Collections.emptyList());
    }

    @Test
    void findMiddle() {
        var linkedList = new SimpleLinkedList<>(List.of("T", "C", "A"));
        assertEquals(new Node<>("C", new Node<>("A")), linkedList.findMiddle());
    }

    @Test
    @DisplayName("find middle in an odd sized linked list")
    void findMiddleInEvenSizedLinkedList() {
        var linkedList = new SimpleLinkedList<>(List.of(5, 7, 9, 8));

        assertEquals(7, linkedList.findMiddle().data);
    }

    @Test
    void copyConstructor() {
        var firstLinkedList = new SimpleLinkedList<>(List.of("I", "L", "A"));
        var copyLinkedList = new SimpleLinkedList<>(firstLinkedList);

        assertEquals(firstLinkedList, copyLinkedList);

        firstLinkedList.append("T");
        assertNotEquals(firstLinkedList, copyLinkedList);
    }
}
