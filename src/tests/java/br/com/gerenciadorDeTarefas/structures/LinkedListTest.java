package tests.java.br.com.gerenciadorDeTarefas.structures;

import main.java.br.com.gerenciadorDeTarefas.structures.LinkedRec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedRec list; // private LinkedRec<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedRec(); // list = new LinkedRec<>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testAdd() {
        list.add(10);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertTrue(list.research(10));
    }

    @Test
    void testAddLast() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(3, list.size());
        assertTrue(list.research(10));
        assertTrue(list.research(20));
        assertTrue(list.research(30));
    }

    @Test
    void testAddIndex() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(50);
        list.add(60);
        list.add(70);
        list.addIndex(40, 3);

        assertEquals(7, list.size());
        assertEquals("70 60 50 40 30 20 10", list.toString());
    }

    @Test
    void testAddInvalidIndex() {
        list.add(20);
        list.add(30);
        list.addIndex(10, -1);

        assertEquals(2, list.size());
        assertEquals("30 20", list.toString());
    }

    @Test
    void testResearch() {
        list.add(5);
        list.add(15);
        list.add(25);

        assertTrue(list.research(15));
        assertFalse(list.research(100));
    }

    @Test
    void testRemoveFirst() {
        list.add(10);
        list.add(20);

        list.remove(10);

        assertEquals(1, list.size());
        assertFalse(list.research(10));
    }

    @Test
    void testRemoveMiddle() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(20);

        assertEquals(2, list.size());
        assertFalse(list.research(20));
    }

    @Test
    void testRemoveLast() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(30);

        assertEquals(2, list.size());
        assertFalse(list.research(30));
    }

    @Test
    void testRemoveValueNotFound() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(40);

        assertEquals(3, list.size());
        assertFalse(list.research(40));
        assertTrue(list.research(10));
        assertTrue(list.research(20));
        assertTrue(list.research(30));
    }

    @Test
    void testToString() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.add(5);

        assertEquals("5 10 20 30", list.toString());
    }

    @Test
    void testToStringEmpty() {
        assertEquals("", list.toString());
    }

    @Test
    void testSizeEmpty() {
        assertEquals(0, list.size());
    }

    @Test
    void testSizeNotEmpty() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(2, list.size());
    }

}