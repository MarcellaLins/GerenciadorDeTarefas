package tests.java.br.com.gerenciadorDeTarefas.model;

import main.java.br.com.gerenciadorDeTarefas.model.Tarefa;
import main.java.br.com.gerenciadorDeTarefas.model.Categoria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    private Tarefa tarefa;

    @BeforeEach
    void setUp() {
        tarefa = new Tarefa(
                "Estudar",
                "Revisar BST",
                Categoria.ESTUDOS,
                LocalDateTime.now().plusDays(1)
        );
    }

    @Test
    void deveCriarTarefaComValoresCorretos() {

        assertEquals("Estudar", tarefa.getTitle());
        assertEquals("Revisar BST", tarefa.getDescription());
        assertEquals(Categoria.ESTUDOS, tarefa.getCategory());
        assertFalse(tarefa.isCompleted());
        assertNotNull(tarefa.getCreationDate());
    }

    @Test
    void deveMarcarTarefaComoConcluida() {

        tarefa.markAsCompleted();

        assertTrue(tarefa.isCompleted());
    }

    @Test
    void deveMarcarTarefaComoPendente() {

        tarefa.markAsCompleted();
        tarefa.markAsPending();

        assertFalse(tarefa.isCompleted());
    }

    @Test
    void tarefaFuturaNaoDeveEstarAtrasada() {

        assertFalse(tarefa.isOverdue());
    }

    @Test
    void deveIdentificarTarefaAtrasada() {

        Tarefa atrasada = new Tarefa(
                "Entrega",
                "Projeto",
                Categoria.TRABALHO,
                LocalDateTime.now().minusDays(1)
        );

        assertTrue(atrasada.isOverdue());
    }

    @Test
    void tarefaConcluidaNaoDeveSerAtrasada() {

        Tarefa atrasada = new Tarefa(
                "Entrega",
                "Projeto",
                Categoria.TRABALHO,
                LocalDateTime.now().minusDays(1)
        );

        atrasada.markAsCompleted();

        assertFalse(atrasada.isOverdue());
    }

    @Test
    void tarefasComIdsDiferentesNaoSaoIguais() {

        Tarefa outra = new Tarefa(
                "Estudar",
                "Revisar",
                Categoria.ESTUDOS,
                LocalDateTime.now()
        );

        assertNotEquals(tarefa, outra);
    }

    @Test
    void tarefaComPrazoMaisProximoDeveVirPrimeiro() {

        Tarefa t1 = new Tarefa(
                "A",
                "desc",
                Categoria.ESTUDOS,
                LocalDateTime.now().plusDays(1)
        );

        Tarefa t2 = new Tarefa(
                "B",
                "desc",
                Categoria.ESTUDOS,
                LocalDateTime.now().plusDays(5)
        );

        assertTrue(t1.compareTo(t2) < 0);
    }

    @Test
    void hashCodeDeveSerConsistente() {

        int hash1 = tarefa.hashCode();
        int hash2 = tarefa.hashCode();

        assertEquals(hash1, hash2);
    }

    @Test
    void toStringDeveConterInformacoesDaTarefa() {

        String texto = tarefa.toString();

        assertTrue(texto.contains("Estudar"));
        assertTrue(texto.contains("Revisar BST"));
    }

}