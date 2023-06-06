package uy.edu.um.prog2.adt.queue;

public interface IQueue<T> {
    void enqueue(T value);
    T dequeue();
    int length();
    boolean isEmpty();
}
