package uy.edu.um.prog2.test.heap;

import org.junit.Test;
import uy.edu.um.prog2.adt.heap.*;

import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void testPushPeekPop() {
        IHeap<Integer, String> minHeap = new Heap<>();

        minHeap.push(5, "Apple");
        minHeap.push(3, "Banana");
        minHeap.push(7, "Orange");
        minHeap.push(2, "Grape");

        assertEquals("Grape", minHeap.peek());
        assertEquals("Grape", minHeap.pop());
        assertEquals("Banana", minHeap.pop());
        assertEquals("Apple", minHeap.peek());
        assertEquals("Apple", minHeap.pop());
        assertEquals("Orange", minHeap.peek());
        assertEquals("Orange", minHeap.pop());
        assertNull(minHeap.peek());
        assertNull(minHeap.pop());
    }

    @Test
    public void testEmptyHeap() {
        IHeap<Integer, String> minHeap = new Heap<>();

        assertNull(minHeap.peek());
        assertNull(minHeap.pop());
    }
}
