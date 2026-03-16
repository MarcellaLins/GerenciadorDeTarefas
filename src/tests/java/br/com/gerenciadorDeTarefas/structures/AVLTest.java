package tests.java.br.com.gerenciadorDeTarefas.structures;

import main.java.br.com.gerenciadorDeTarefas.structures.AVL;
import main.java.br.com.gerenciadorDeTarefas.structures.AVLNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTest {

    // Verifica se a árvore inicia vazia e deixa de estar vazia após inserção
    @Test
    public void testIsEmpty() {
        AVL<Integer> avl = new AVL<>();
        assertTrue(avl.isEmpty());
        assertTrue(avl.getRoot().isNil());

        avl.insert(1);

        assertFalse(avl.isEmpty());
        assertFalse(avl.getRoot().isNil());
    }

    // Testa atualização correta do tamanho após inserção e remoção
    @Test
    public void testSize() {
        AVL<Integer> avl = new AVL<>();
        assertEquals(0, avl.size());

        avl.insert(1);
        assertEquals(1, avl.size());

        avl.insert(2);
        assertEquals(2, avl.size());

        avl.remove(1);
        assertEquals(1, avl.size());
    }

    // Verifica cálculo correto da altura da árvore
    @Test
    public void testHeight() {
        AVL<Integer> avl = new AVL<>();

        assertEquals(-1, avl.height());

        avl.insert(1);
        assertEquals(0, avl.height());

        avl.insert(2);
        assertEquals(1, avl.height());

        avl.insert(3);
        assertEquals(1, avl.height());
    }

    // Testa inserção básica de um elemento
    @Test
    public void testInsert() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);

        assertEquals(1, avl.size());
        assertFalse(avl.isEmpty());
        assertEquals(10, avl.getRoot().getData());

        AVLNode<Integer> node = avl.search(10);
        assertFalse(node.isNil());
        assertEquals(10, node.getData());
    }

    // Verifica que elementos duplicados não são inseridos
    @Test
    public void testInsertDuplicate() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(10);

        assertEquals(1, avl.size());
        assertEquals(10, avl.getRoot().getData());
    }

    // Testa remoção de nó da árvore
    @Test
    public void testRemove() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(5);
        avl.insert(15);

        assertEquals(3, avl.size());

        avl.remove(10);

        assertEquals(2, avl.size());
        assertTrue(avl.search(10).isNil());
        assertEquals(List.of(5, 15), avl.inOrder());
    }

    // Testa remoção de nó folha
    @Test
    public void testRemoveLeaf() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(5);

        avl.remove(5);

        assertEquals(1, avl.size());
        assertTrue(avl.getRoot().getLeft().isNil());
        assertTrue(avl.search(5).isNil());
    }

    // Testa remoção de nó com dois filhos
    @Test
    public void testRemoveWithTwoChildren() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(5);
        avl.insert(15);
        avl.insert(3);
        avl.insert(7);

        avl.remove(5);

        assertEquals(4, avl.size());
        assertTrue(avl.search(5).isNil());
        assertEquals(List.of(3,7,10,15), avl.inOrder());
        assertFalse(avl.search(7).isNil());
    }

    // Verifica busca de elemento existente e inexistente
    @Test
    public void testSearch() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(5);
        avl.insert(15);

        AVLNode<Integer> node = avl.search(10);
        assertFalse(node.isNil());
        assertEquals(10, node.getData());

        node = avl.search(20);
        assertTrue(node.isNil());
    }

    // Testa percurso em ordem (in-order)
    @Test
    public void testInOrder() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(5);
        avl.insert(15);
        avl.insert(3);
        avl.insert(7);

        List<Integer> inOrder = avl.inOrder();

        assertEquals(List.of(3, 5, 7, 10, 15), inOrder);
    }

    // Testa percurso pré-ordem (pre-order)
    @Test
    public void testPreOrder() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(5);
        avl.insert(15);
        avl.insert(3);
        avl.insert(7);

        List<Integer> preOrder = avl.preOrder();

        assertEquals(List.of(10, 5, 3, 7, 15), preOrder);
    }

    // Testa percurso pós-ordem (post-order)
    @Test
    public void testPostOrder() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        avl.insert(5);
        avl.insert(15);
        avl.insert(3);
        avl.insert(7);

        List<Integer> postOrder = avl.postOrder();

        assertEquals(List.of(3, 7, 5, 15, 10), postOrder);
    }

    // Verifica se rotações mantêm a árvore balanceada após inserções
    @Test
    public void testBalanceAfterInsert() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        assertEquals(1, avl.getRoot().getData());

        avl.insert(2);
        assertEquals(1, avl.getRoot().getData());

        avl.insert(3);
        assertEquals(2, avl.getRoot().getData());

        assertEquals(List.of(1,2,3), avl.inOrder());
        assertEquals(1, avl.height());
    }

    // Verifica se a árvore continua balanceada após remoção
    @Test
    public void testBalanceAfterRemove() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(1);
        avl.insert(2);
        avl.insert(3);
        avl.insert(4);

        avl.remove(1);

        assertTrue(avl.search(1).isNil());
        assertEquals(List.of(2,3,4), avl.inOrder());
        assertTrue(avl.height() <= 2);
    }

    // Verifica se as alturas dos nós são atualizadas corretamente
    @Test
    public void testNodeHeights() {
        AVL<Integer> avl = new AVL<>();

        avl.insert(10);
        assertEquals(0, avl.getRoot().getHeight());

        avl.insert(5);
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(-1, avl.getRoot().getRight().getHeight());

        avl.insert(15);
        assertEquals(1, avl.getRoot().getHeight());
        assertEquals(0, avl.getRoot().getLeft().getHeight());
        assertEquals(0, avl.getRoot().getRight().getHeight());
    }
}