package com.eda.gerenciadortarefas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(
                "Estudar",
                "Revisar BST",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(1)
        );
    }

    @Test
    void deveCriarTarefaComValoresCorretos() {

        assertEquals("Estudar", task.getTitle());
        assertEquals("Revisar BST", task.getDescription());
        assertEquals(Category.ESTUDOS, task.getCategory());
        assertFalse(task.isCompleted());
        assertNotNull(task.getCreationDate());
    }

    @Test
    void deveMarcarTarefaComoConcluida() {

        task.markAsCompleted();

        assertTrue(task.isCompleted());
    }

    @Test
    void deveMarcarTarefaComoPendente() {

        task.markAsCompleted();
        task.markAsPending();

        assertFalse(task.isCompleted());
    }

    @Test
    void tarefaFuturaNaoDeveEstarAtrasada() {

        assertFalse(task.isOverdue());
    }

    @Test
    void deveIdentificarTarefaAtrasada() {

        Task atrasada = new Task(
                "Entrega",
                "Projeto",
                Category.TRABALHO,
                LocalDateTime.now().minusDays(1)
        );

        assertTrue(atrasada.isOverdue());
    }

    @Test
    void tarefaConcluidaNaoDeveSerAtrasada() {

        Task atrasada = new Task(
                "Entrega",
                "Projeto",
                Category.TRABALHO,
                LocalDateTime.now().minusDays(1)
        );

        atrasada.markAsCompleted();

        assertFalse(atrasada.isOverdue());
    }

    @Test
    void tarefasComIdsDiferentesNaoSaoIguais() {

        Task outra = new Task(
                "Estudar",
                "Revisar",
                Category.ESTUDOS,
                LocalDateTime.now()
        );

        assertNotEquals(task, outra);
    }

    @Test
    void tarefaComPrazoMaisProximoDeveVirPrimeiro() {

        Task t1 = new Task(
                "A",
                "desc",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(1)
        );

        Task t2 = new Task(
                "B",
                "desc",
                Category.ESTUDOS,
                LocalDateTime.now().plusDays(5)
        );

        assertTrue(t1.compareTo(t2) < 0);
    }

    @Test
    void hashCodeDeveSerConsistente() {

        int hash1 = task.hashCode();
        int hash2 = task.hashCode();

        assertEquals(hash1, hash2);
    }

    @Test
    void toStringDeveConterInformacoesDaTarefa() {

        String texto = task.toString();

        assertTrue(texto.contains("Estudar"));
        assertTrue(texto.contains("Revisar BST"));
    }

}