package tests.java.br.com.gerenciadorDeTarefas.structures;

import main.java.br.com.gerenciadorDeTarefas.structures.MaxHeap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapSortTest {

    @Test
    public void testEmptyHeap() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testSingleElement() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.insert(42);

        assertEquals(Integer.valueOf(42), heap.remove());
    }

    @Test
    public void testOrderDescending() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        for (int n : Arrays.asList(1, 2, 3, 4, 5)) {
            heap.insert(n);
        }

        assertEquals(Integer.valueOf(5), heap.remove());
        assertEquals(Integer.valueOf(4), heap.remove());
        assertEquals(Integer.valueOf(3), heap.remove());
        assertEquals(Integer.valueOf(2), heap.remove());
        assertEquals(Integer.valueOf(1), heap.remove());
    }

    @Test
    public void testReverseInput() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        for (int n : Arrays.asList(5, 4, 3, 2, 1)) {
            heap.insert(n);
        }

        assertEquals(Integer.valueOf(5), heap.remove());
        assertEquals(Integer.valueOf(4), heap.remove());
        assertEquals(Integer.valueOf(3), heap.remove());
        assertEquals(Integer.valueOf(2), heap.remove());
        assertEquals(Integer.valueOf(1), heap.remove());
    }

    @Test
    public void testWithDuplicates() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        for (int n : Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3)) {
            heap.insert(n);
        }

        int[] expected = {9, 6, 5, 5, 4, 3, 3, 2, 1, 1};

        for (int value : expected) {
            assertEquals(Integer.valueOf(value), heap.remove());
        }
    }

    @Test
    public void testStrings() {
        MaxHeap<String> heap = new MaxHeap<>();

        heap.insert("banana");
        heap.insert("maçã");
        heap.insert("chocolate");

        assertEquals("maçã", heap.remove());
        assertEquals("chocolate", heap.remove());
        assertEquals("banana", heap.remove());
    }
}