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

        // testa inserção e busca de elemento
        hashTable.insert(10);

        boolean found = hashTable.search(10);

        assertTrue(found);
    }

    @Test
    @DisplayName("Should return false when searching for a non-existing element")
    void shouldReturnFalseWhenElementDoesNotExist() {

        // testa busca de elemento inexistente
        boolean found = hashTable.search(99);

        assertFalse(found);
    }

    @Test
    @DisplayName("Should remove an existing element from the hash table")
    void shouldRemoveExistingElement() {

        // testa remoção de elemento existente
        hashTable.insert(30);

        boolean removed = hashTable.remove(30);

        assertTrue(removed);
        assertFalse(hashTable.search(30));
    }

    @Test
    @DisplayName("Should include inserted element in the string representation")
    void shouldContainElementInStringRepresentation() {

        // testa se elemento aparece no toString
        hashTable.insert(5);

        String result = hashTable.toString();

        assertTrue(result.contains("5"));
    }

    @Test
    @DisplayName("Should handle collision and still find both elements")
    void shouldHandleCollision() {

        // testa tratamento de colisão
        hashTable.insert(1);
        hashTable.insert(7);

        assertTrue(hashTable.search(1));
        assertTrue(hashTable.search(7));
    }

    @Test
    @DisplayName("Should remove element from collision chain without affecting others")
    void shouldRemoveElementFromCollisionChain() {

        // testa remoção em lista de colisão
        hashTable.insert(1);
        hashTable.insert(7);

        hashTable.remove(1);

        assertFalse(hashTable.search(1));
        assertTrue(hashTable.search(7));
    }

    @Test
    @DisplayName("Should return false when removing non-existing element")
    void shouldReturnFalseWhenRemovingNonExistingElement() {

        // testa remoção de elemento inexistente
        boolean removed = hashTable.remove(100);

        assertFalse(removed);
    }

    @Test
    @DisplayName("Should handle multiple insertions")
    void shouldHandleMultipleInsertions() {

        // testa múltiplas inserções
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
    @DisplayName("Should allow duplicate insertions and keep at least one after removal")
    void shouldHandleDuplicateInsertions() {

        // testa inserção de duplicados
        hashTable.insert(10);
        hashTable.insert(10);

        hashTable.remove(10);

        assertTrue(hashTable.search(10));
    }

    // TESTES COM TASK

    @Test
    @DisplayName("Should insert and find a task")
    void shouldInsertAndFindTask() {

        // testa inserção e busca de tarefa
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

        // testa colisão entre tarefas
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

        // testa remoção de tarefa
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

        // testa remoção em colisão de tarefas
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

        // testa busca de tarefa inexistente
        Task task = new Task(
                "Treinar academia",
                "Cardio",
                Category.SAUDE,
                LocalDateTime.now().plusDays(1)
        );

        assertFalse(tarefaHashTable.search(task));
    }
}