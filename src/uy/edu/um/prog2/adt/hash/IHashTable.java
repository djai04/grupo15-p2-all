package uy.edu.um.prog2.adt.hash;

public interface IHashTable<K, V> {
    public void put(K key, V value);
    public boolean contains(K key);
    public void remove(K key);

    public V get(K key);
}
