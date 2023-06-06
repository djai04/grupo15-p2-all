package uy.edu.um.prog2.adt.stack;

public interface IStack<T> {
    void push(T value);
    void pop();
    T top();
    int length();
    boolean isEmpty();
    void makeEmpty();
}
