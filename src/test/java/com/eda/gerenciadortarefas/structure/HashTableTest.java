package com.eda.gerenciadortarefas.structure;

import com.eda.gerenciadortarefas.model.Category;
import com.eda.gerenciadortarefas.model.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<Integer> hashTable;
    private HashTable<Task> tarefaHashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>(6, key -> key);
        tarefaHashTable = new HashTable<>(6, task -> task.getCategory().getCode());
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

    @Test
    @DisplayName("Should insert and find a task")
    void shouldInsertAndFindTask() {

        Task task = new Task(
                "Estudar ED",
                "Revisar HashTable",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(2)
        );

        tarefaHashTable.insert(task);

        assertTrue(tarefaHashTable.search(task));
    }

    @Test
    @DisplayName("Should handle collision between tasks of same category")
    void shouldHandleTaskCollision() {

        Task t1 = new Task(
                "Estudar ED",
                "Lista de exercícios",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(2)
        );

        Task t2 = new Task(
                "Revisar provas antigas",
                "Praticar BST",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(3)
        );

        tarefaHashTable.insert(t1);
        tarefaHashTable.insert(t2);

        assertTrue(tarefaHashTable.search(t1));
        assertTrue(tarefaHashTable.search(t2));
    }

    @Test
    @DisplayName("Should remove a specific task")
    void shouldRemoveTask() {

        Task task = new Task(
                "Estudar Hash",
                "Implementar testes",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(1)
        );

        tarefaHashTable.insert(task);

        tarefaHashTable.remove(task);

        assertFalse(tarefaHashTable.search(task));
    }

    @Test
    @DisplayName("Should remove one task without affecting another in same bucket")
    void shouldRemoveOneTaskFromCollision() {

        Task t1 = new Task(
                "Estudar ED",
                "Lista 1",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(2)
        );

        Task t2 = new Task(
                "Estudar Grafos",
                "Lista 2",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(3)
        );

        tarefaHashTable.insert(t1);
        tarefaHashTable.insert(t2);

        tarefaHashTable.remove(t1);

        assertFalse(tarefaHashTable.search(t1));
        assertTrue(tarefaHashTable.search(t2));
    }

    @Test
    @DisplayName("Should return false when task is not in the table")
    void shouldNotFindNonExistingTask() {

        Task task = new Task(
                "Treinar academia",
                "Cardio",
                Category.SAUDE,
                LocalDateTime.now().plusDays(1)
        );

        assertFalse(tarefaHashTable.search(task));
    }



}
