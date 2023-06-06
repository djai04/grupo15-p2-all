package uy.edu.um.prog2.test.bst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.prog2.adt.bst.*;

public class BSTTest {
    private IBST<Integer, String> bst;

    @Before
    public void setUp() {
        bst = new BST<>();
    }

    @Test
    public void testInsertAndFind() {
        bst.insert(5, "Apple");
        bst.insert(2, "Banana");
        bst.insert(7, "Orange");
        bst.insert(1, "Mango");
        bst.insert(3, "Grapes");

        Assert.assertEquals("Apple", bst.find(5));
        Assert.assertEquals("Banana", bst.find(2));
        Assert.assertEquals("Orange", bst.find(7));
        Assert.assertEquals("Mango", bst.find(1));
        Assert.assertEquals("Grapes", bst.find(3));
    }

    @Test
    public void testDelete() {
        bst.insert(5, "Apple");
        bst.insert(2, "Banana");
        bst.insert(7, "Orange");
        bst.insert(1, "Mango");
        bst.insert(3, "Grapes");

        bst.delete(2); // Delete node with key 2

        Assert.assertNull(bst.find(2));
        Assert.assertEquals("Apple", bst.find(5));
        Assert.assertEquals("Orange", bst.find(7));
        Assert.assertEquals("Mango", bst.find(1));
        Assert.assertEquals("Grapes", bst.find(3));
    }
}
