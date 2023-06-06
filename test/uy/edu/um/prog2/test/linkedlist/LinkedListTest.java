package uy.edu.um.prog2.test.linkedlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.prog2.adt.linkedlist.*;

public class LinkedListTest {
    private IList<Integer> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void testAdd() {
        list.add(10);
        list.add(20);
        list.add(30);

        Assert.assertEquals(3, list.length());
        Assert.assertEquals((Integer) 10, list.get(0));
        Assert.assertEquals((Integer) 20, list.get(1));
        Assert.assertEquals((Integer) 30, list.get(2));
    }

    @Test
    public void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(1);

        Assert.assertEquals(2, list.length());
        Assert.assertEquals((Integer) 10, list.get(0));
        Assert.assertEquals((Integer) 30, list.get(1));
    }

    @Test
    public void testGet() {
        list.add(10);
        list.add(20);
        list.add(30);

        Assert.assertEquals((Integer) 20, list.get(1));
    }

    @Test
    public void testLength() {
        list.add(10);
        list.add(20);
        list.add(30);

        Assert.assertEquals(3, list.length());

        list.remove(1);

        Assert.assertEquals(2, list.length());
    }

}
