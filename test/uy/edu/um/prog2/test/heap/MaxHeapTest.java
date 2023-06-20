package uy.edu.um.prog2.test.heap;

import org.junit.Test;
import uy.edu.um.prog2.adt.heap.MaxHeap;
import uy.edu.um.prog2.adt.heap.IHeap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MaxHeapTest {

    @Test
    public void testPushPeekPop() {
        IHeap<Integer, String> maxHeap = new MaxHeap<>();

        maxHeap.push(5, "Apple");
        maxHeap.push(3, "Banana");
        maxHeap.push(7, "Orange");
        maxHeap.push(2, "Grape");

        assertEquals("Orange", maxHeap.peek());
        assertEquals("Orange", maxHeap.pop());
        assertEquals("Apple", maxHeap.pop());
        assertEquals("Banana", maxHeap.peek());
        assertEquals("Banana", maxHeap.pop());
        assertEquals("Grape", maxHeap.peek());
        assertEquals("Grape", maxHeap.pop());
        assertNull(maxHeap.peek());
        assertNull(maxHeap.pop());
    }

    @Test
    public void testEmptyHeap() {
        IHeap<Integer, String> maxHeap = new MaxHeap<>();

        assertNull(maxHeap.peek());
        assertNull(maxHeap.pop());
    }
}
