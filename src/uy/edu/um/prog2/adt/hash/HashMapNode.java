package uy.edu.um.prog2.adt.hash;

public class HashMapNode<K, V> {
    private K key;
    private V value;
    private boolean isDeleted;

    public HashMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.isDeleted = false;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
