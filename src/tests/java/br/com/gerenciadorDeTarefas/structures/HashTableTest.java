package tests.java.br.com.gerenciadorDeTarefas.structures;

import main.java.br.com.gerenciadorDeTarefas.structures.HashTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    private HashTable<Integer> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>(6);
    }

    @Test
    @DisplayName("Should insert an element and allow it to be found")
    void shouldInsertElementIntoHashTable() {

        hashTable.insert(10);

        boolean found = hashTable.search(10);

        assertTrue(found);
    }

    @Test
    @DisplayName("Should return true when searching for an existing element")
    void shouldFindExistingElement() {

        hashTable.insert(10);

        boolean result = hashTable.search(10);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when searching for a non-existing element")
    void shouldReturnFalseWhenElementDoesNotExist() {

        boolean found = hashTable.search(99);

        assertFalse(found);
    }

    @Test
    @DisplayName("Should remove an existing element from the hash table")
    void shouldRemoveExistingElement() {

        hashTable.insert(30);

        boolean removed = hashTable.remove(30);

        assertTrue(removed);
    }

    @Test
    @DisplayName("Should include inserted element in the string representation")
    void shouldContainElementInStringRepresentation() {

        hashTable.insert(5);

        String result = hashTable.toString();

        assertTrue(result.contains("5"));
    }

    @Test
    @DisplayName("Should handle collision and still find both elements")
    void shouldHandleCollision() {

        hashTable.insert(1);
        hashTable.insert(7); // mesma posição se capacity = 6

        assertTrue(hashTable.search(1));
        assertTrue(hashTable.search(7));
    }

    @Test
    @DisplayName("Should remove element from collision chain without affecting others")
    void shouldRemoveElementFromCollisionChain() {

        hashTable.insert(1);
        hashTable.insert(7);

        hashTable.remove(1);

        assertFalse(hashTable.search(1));
        assertTrue(hashTable.search(7));
    }

    @Test
    @DisplayName("Should return false when removing non-existing element")
    void shouldReturnFalseWhenRemovingNonExistingElement() {

        boolean removed = hashTable.remove(100);

        assertFalse(removed);
    }

    @Test
    @DisplayName("Should handle multiple insertions")
    void shouldHandleMultipleInsertions() {

        hashTable.insert(1);
        hashTable.insert(2);
        hashTable.insert(3);
        hashTable.insert(4);
        hashTable.insert(5);

        assertTrue(hashTable.search(1));
        assertTrue(hashTable.search(2));
        assertTrue(hashTable.search(3));
        assertTrue(hashTable.search(4));
        assertTrue(hashTable.search(5));
    }

    @Test
    @DisplayName("Should allow duplicate insertions")
    void shouldAllowDuplicateInsertions() {

        hashTable.insert(10);
        hashTable.insert(10);

        assertTrue(hashTable.search(10));
    }

}