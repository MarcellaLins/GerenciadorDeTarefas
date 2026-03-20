package com.eda.gerenciadortarefas.structure;


import com.eda.gerenciadortarefas.structures.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPush() {
        stack.push(10);
        stack.push(20);

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertEquals(20, stack.peek());
    }

    @Test
    void testPeek() {
        stack.push(5);
        stack.push(15);

        assertEquals(15, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void testPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.size());
    }

    @Test
    void testLIFOOrder() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    void testToString() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        String result = stack.toString();

        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
        assertEquals("[3 2 1]", result);
    }

    @Test
    void testSize() {
        assertEquals(0, stack.size());

        stack.push(7);
        stack.push(8);

        assertEquals(2, stack.size());

        stack.pop();

        assertEquals(1, stack.size());
    }
}
