package uy.edu.um.prog2.adt.bst;

public interface IBST <K extends Comparable<K>, T> {
    T find(K key);

    void insert(K key, T data);

    void delete(K key);
}
