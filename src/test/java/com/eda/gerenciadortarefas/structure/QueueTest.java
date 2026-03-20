package com.eda.gerenciadortarefas.structure;

import com.eda.gerenciadortarefas.structures.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(queue.isEmpty());

        queue.enqueue(10);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    @Test
    void testEnqueue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
        assertEquals(10, queue.head());
    }

    @Test
    void testHead() {
        queue.enqueue(5);
        queue.enqueue(15);

        assertEquals(5, queue.head());
        assertEquals(2, queue.size());
    }

    @Test
    void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.size());
    }

    @Test
    void testFIFOOrder() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, queue.size());

        queue.enqueue(7);
        queue.enqueue(8);

        assertEquals(2, queue.size());

        queue.dequeue();

        assertEquals(1, queue.size());
    }

    @Test
    void testToString() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        String result = queue.toString();

        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
        assertEquals("[1 2 3]", result);
    }
}
