package uy.edu.um.prog2.adt.bst;

import uy.edu.um.prog2.adt.linkedlist.*;

public class BST <K extends Comparable<K>, T> implements IBST<K, T> {
    private NodeBST<K, T> root;

    public BST() {
        this.root = null;
    }

    public NodeBST<K, T> getRoot() {
        return root;
    }

    public void setRoot(NodeBST<K, T> root) {
        this.root = root;
    }

    @Override
    public T find(K keyToFind) {
        return this.root.nodeFind(keyToFind);
    }

    @Override
    public void insert(K keyToAdd, T dataToAdd) {
        NodeBST<K, T> nodeToAdd = new NodeBST<>(keyToAdd, dataToAdd);

        if (this.root == null) {
            this.root = nodeToAdd;
        } else {
            this.root.nodeInsert(keyToAdd, dataToAdd);
        }

    }

    @Override
    public void delete(K keyToDelete) {
        this.root = this.root.nodeDelete(this.root, keyToDelete);
    }

    public LinkedList<K> inOrder(LinkedList<K> list) {
        this.root.nodeInOrder(list, this.root);
        return list;
    }

    public LinkedList<K> postOrder(LinkedList<K> list) {
        this.root.nodePostOrder(list, this.root);
        return list;
    }

    public LinkedList<K> preOrder(LinkedList<K> list) {
        this.root.nodePreOrder(list, this.root);
        return list;
    }

    public NodeBST<K, T> get(K keyToGet) {
        return this.root.nodeGet(keyToGet);
    }
}
