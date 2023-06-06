package uy.edu.um.prog2.test.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.prog2.adt.stack.*;

public class StackTest {
    private IStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPush() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assert.assertEquals(3, stack.length());
        Assert.assertEquals(Integer.valueOf(3), stack.top());
    }

    @Test
    public void testPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();

        Assert.assertEquals(2, stack.length());
        Assert.assertEquals(Integer.valueOf(2), stack.top());
    }

    @Test
    public void testTop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assert.assertEquals(Integer.valueOf(3), stack.top());
    }

    @Test
    public void testLength() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assert.assertEquals(3, stack.length());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(stack.isEmpty());

        stack.push(1);

        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void testMakeEmpty() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.makeEmpty();

        Assert.assertEquals(0, stack.length());
        Assert.assertTrue(stack.isEmpty());
    }
}
