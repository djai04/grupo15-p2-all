package uy.edu.um.prog2.test.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.prog2.adt.queue.*;

public class QueueTest {
    private IQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        Assert.assertEquals(3, queue.length());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        int dequeued = queue.dequeue();

        Assert.assertEquals(1, dequeued);
        Assert.assertEquals(2, queue.length());
    }

    @Test
    public void testLength() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        Assert.assertEquals(3, queue.length());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(queue.isEmpty());

        queue.enqueue(1);

        Assert.assertFalse(queue.isEmpty());
    }
}
