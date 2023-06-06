package uy.edu.um.prog2.adt.stack;

import uy.edu.um.prog2.adt.linkedlist.LinkedList;

public class Stack<T> implements IStack<T> {
    LinkedList<T> list = new LinkedList<T>();

    @Override
    public void push(T value) {
        list.add(value);
    }

    @Override
    public void pop() {
        list.remove(this.length() - 1);
    }

    @Override
    public T top() {
        return list.get(this.length() - 1);
    }

    @Override
    public int length() {
        return this.list.length();
    }

    @Override
    public boolean isEmpty() {
        return this.length() == 0;
    }

    @Override
    public void makeEmpty() {
        while (this.length() != 0) {
            this.pop();
        }
    }
}
