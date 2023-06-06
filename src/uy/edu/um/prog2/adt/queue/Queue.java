package uy.edu.um.prog2.adt.queue;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class Queue<T> implements IQueue<T> {
    LinkedList<T> list = new LinkedList<T>();

    @Override
    public void enqueue(T value) {
        list.addFirst(value);
    }

    @Override
    public T dequeue() {
        T value = list.get(this.list.length() - 1);
        list.remove(this.list.length() - 1);
        return value;
    }

    @Override
    public int length() {
        return this.list.length();
    }

    @Override
    public boolean isEmpty() {
        return this.length() == 0;
    }
}
