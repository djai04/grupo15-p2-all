package uy.edu.um.prog2.adt.heap;

public interface IHeap <K extends Comparable<K>, V> {

    void push(K keyToPush, V valueToPush);

    V peek();

    V pop();
}
