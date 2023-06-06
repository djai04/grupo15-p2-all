package uy.edu.um.prog2.adt.linkedlist;

public class LinkedList<T> implements IList<T> {
    private Node<T> head;
    private Node<T> tail;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void add(T value) {
        Node<T> node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public void addFirst(T value) {
        Node<T> node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    @Override
    public void remove(int position) {
        if (position == 0) {
            head = head.getNext();
        } else {
            Node<T> node = head;
            int i = 0;
            while (i < position - 1) {
                node = node.getNext();
                i++;
            }
            node.setNext(node.getNext().getNext());
        }
    }

    @Override
    public T get(int position) {
        if (position == 0) {
            return head.getValue();
        } else {
            Node<T> node = head;
            int i = 0;
            while (i < position) {
                node = node.getNext();
                i++;
            }
            return node.getValue();
        }
    }

    @Override
    public int length() {
        Node<T> node = head;
        int length = 0;
        while (node != null) {
            length++;
            node = node.getNext();
        }

        return length;
    }

    @Override
    public void displayList() {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    public boolean isInList(T value) {
        Node<T> node = head;
        while (node != null) {
            if (node.getValue() == value) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }
}
