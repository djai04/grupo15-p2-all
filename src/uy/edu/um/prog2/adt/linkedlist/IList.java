package uy.edu.um.prog2.adt.linkedlist;

public interface IList<T> {
    void add(T value);
    void remove(int position);
    T get(int position);

    int length();

    void displayList();
}
