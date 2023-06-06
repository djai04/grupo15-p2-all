package uy.edu.um.prog2.test.hash;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uy.edu.um.prog2.adt.hash.*;

public class HashTableTest {
    private IHashTable<String, Integer> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    public void testPutAndGet() {
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        Assert.assertEquals((Integer) 1, hashTable.get("A"));
        Assert.assertEquals((Integer) 2, hashTable.get("B"));
        Assert.assertEquals((Integer) 3, hashTable.get("C"));
    }

    @Test
    public void testContains() {
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        Assert.assertTrue(hashTable.contains("A"));
        Assert.assertTrue(hashTable.contains("B"));
        Assert.assertTrue(hashTable.contains("C"));
        Assert.assertFalse(hashTable.contains("D"));
    }

    @Test
    public void testRemove() {
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        hashTable.remove("B");

        Assert.assertFalse(hashTable.contains("B"));
        Assert.assertNull(hashTable.get("B"));
    }
}
