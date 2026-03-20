package tests.java.br.com.gerenciadorDeTarefas.structures;

import main.java.br.com.gerenciadorDeTarefas.structures.HeapSort;
import main.java.br.com.gerenciadorDeTarefas.structures.SingleLinkedList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapSortTest {

    @Test
    public void testEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();

        HeapSort.sort(list);

        assertTrue(list.isEmpty());
    }

    @Test
    public void testSingleElement() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10));

        HeapSort.sort(list);

        assertEquals(Arrays.asList(10), list);
    }

    @Test
    public void testSortedList() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        HeapSort.sort(list);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testReverseList() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));

        HeapSort.sort(list);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testRandomList() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2));

        HeapSort.sort(list);

        assertEquals(Arrays.asList(1, 1, 2, 3, 4, 5, 9), list);
    }

    @Test
    public void testDuplicates() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 2, 2, 2));

        HeapSort.sort(list);

        assertEquals(Arrays.asList(2, 2, 2, 2), list);
    }

    @Test
    public void testNegativeNumbers() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-3, -1, -4, -2));

        HeapSort.sort(list);

        assertEquals(Arrays.asList(-4, -3, -2, -1), list);
    }

    @Test
    public void testStringSorting() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("banana", "maçã", "abacaxi", "uva"));

        HeapSort.sort(list);

        assertEquals(Arrays.asList("abacaxi", "banana", "maçã", "uva"), list);
    }

    @Test
    public void testLinkedListEmpty() {
        SingleLinkedList<Integer> ll = new SingleLinkedList<>();

        ArrayList<Integer> result = HeapSort.sort(ll);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testLinkedListSingleElement() {
        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.addLast(10);

        ArrayList<Integer> result = HeapSort.sort(ll);

        assertEquals(Arrays.asList(10), result);
    }

    @Test
    public void testLinkedListReverse() {
        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.addLast(5);
        ll.addLast(4);
        ll.addLast(3);
        ll.addLast(2);
        ll.addLast(1);

        ArrayList<Integer> result = HeapSort.sort(ll);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testLinkedListRandom() {
        SingleLinkedList<Integer> ll = new SingleLinkedList<>();
        ll.addLast(3);
        ll.addLast(1);
        ll.addLast(4);
        ll.addLast(1);
        ll.addLast(5);

        ArrayList<Integer> result = HeapSort.sort(ll);

        assertEquals(Arrays.asList(1, 1, 3, 4, 5), result);
    }
}