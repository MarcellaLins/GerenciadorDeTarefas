package tests.java.br.com.gerenciadorDeTarefas.structures;

import main.java.br.com.gerenciadorDeTarefas.structures.MaxHeap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HeapTest {

    private MaxHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new MaxHeap<>();
    }

    @Test
    void testIsEmpty(){
        assertTrue(heap.isEmpty());
    }

    @Test
    void testInsert(){
        assertTrue(heap.isEmpty());
        int[] values = {9,61,6,59,60};

        for(int i : values){
            heap.insert(i);
        }

        assertFalse(heap.isEmpty());
        assertEquals(5, heap.size());
        assertEquals(61, heap.max());
    }

    @Test
    void testRemove(){
        int[] values = {9,61,6,59,60,31,68}; // na heap: 68,60,61,9,59,6,31

        for(int i : values){
            heap.insert(i);
        }

        int[] valuesRemove = {68,61,60,59,31,9,6};

        for (int j : valuesRemove) {
            assertEquals(j, heap.remove());
        }

        assertTrue(heap.isEmpty());
    }

    @Test
    void testSingleElement() {
        heap.insert(42);

        assertEquals(Integer.valueOf(42), heap.remove());
    }

    @Test
    void testOrderDescending() {

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
    void testReverseInput() {

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
    void testWithDuplicates() {

        for (int n : Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3)) {
            heap.insert(n);
        }

        int[] expected = {9, 6, 5, 5, 4, 3, 3, 2, 1, 1};

        for (int value : expected) {
            assertEquals(Integer.valueOf(value), heap.remove());
        }
    }

    @Test
    void testStrings() {
        MaxHeap<String> heap = new MaxHeap<>();

        heap.insert("banana");
        heap.insert("maçã");
        heap.insert("chocolate");

        assertEquals("maçã", heap.remove());
        assertEquals("chocolate", heap.remove());
        assertEquals("banana", heap.remove());
    }

    @Test
    void constructorWithArrayInt() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(9,61,6,59,60));
        MaxHeap<Integer> builtHeap = new MaxHeap<>(arr);

        assertFalse(builtHeap.isEmpty());
        assertEquals(5, builtHeap.size());
        assertEquals(61, builtHeap.max());
    }

    @Test
    void constructorWithArrayStr() {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList("banana","maçã","chocolate"));
        MaxHeap<String> builtHeap = new MaxHeap<>(arr);

        assertFalse(builtHeap.isEmpty());
        assertEquals(3, builtHeap.size());

        assertEquals("maçã", builtHeap.remove());
        assertEquals("chocolate", builtHeap.remove());
        assertEquals("banana", builtHeap.remove());

        assertTrue(builtHeap.isEmpty());
    }

}