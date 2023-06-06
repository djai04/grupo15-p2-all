package uy.edu.um.prog2.adt.bst;

import uy.edu.um.prog2.adt.linkedlist.*;

public class NodeBST <K extends Comparable<K>, T> {
    private K key;
    private T data;
    private NodeBST <K, T> leftChild;
    private NodeBST <K, T> rightChild;

    public NodeBST(K key, T data) {
        this.key = key;
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeBST<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeBST<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeBST<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeBST<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    public void nodeInsert(K keyToAdd, T dataToAdd) {
        NodeBST<K, T> nodeToAdd = new NodeBST<>(keyToAdd, dataToAdd);

        if (keyToAdd.compareTo(this.key) < 0) { // Si el nuevo nodo es mas chico
            if (this.leftChild == null) {
                this.leftChild = nodeToAdd;
            } else {
                this.leftChild.nodeInsert(keyToAdd, dataToAdd);
            }
        } else if (keyToAdd.compareTo(this.key) > 0) { // Si el nuevo nodo es mas grande
            if (this.rightChild == null) {
                this.rightChild = nodeToAdd;
            } else {
                this.rightChild.nodeInsert(keyToAdd, dataToAdd);
            }
        } else { // Si son iguales
            // No lo inserta de nuevo
        }
    }

    public T nodeFind(K keyToFind) {
        try {
            if (keyToFind.compareTo(this.key) < 0) { // Si el nodo que estoy buscando es mas chico
                return this.leftChild.nodeFind(keyToFind);
            } else if (keyToFind.compareTo(this.key) > 0) { // Si el nodo que estoy buscando es mas grande
                return this.rightChild.nodeFind(keyToFind);
            } else if (keyToFind.compareTo(this.key) == 0) { // Si son iguales
                return this.data;
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    public NodeBST<K, T> nodeGet(K keyToGet) {
        try {
            if (keyToGet.compareTo(this.key) < 0) { // Si el nodo buscado es mas chico
                return this.leftChild.nodeGet(keyToGet);
            } else if (keyToGet.compareTo(this.key) > 0) { // Si el nodo buscado es mas grande
                return this.rightChild.nodeGet(keyToGet);
            } else if (keyToGet.compareTo(this.key) == 0) { // Si son iguales
                return this;
            }
        } catch (Exception e) {
            System.err.println("The element does not exist");
        }

        return null;
    }

    public NodeBST<K, T> nodeDelete(NodeBST<K, T> root, K keyToDelete) {
        if (root == null) {
            return root;
        }

        if (keyToDelete.compareTo(this.key) < 0) { // Si el nodo buscado es mas chico
            root.leftChild = root.leftChild.nodeDelete(root.leftChild, keyToDelete);
        } else if (keyToDelete.compareTo(this.key) > 0) { // Si el nodo buscado es mas grande
            root.rightChild = root.rightChild.nodeDelete(root.rightChild, keyToDelete);
        } else  { // Si son iguales
            if (root.leftChild == null) {
                return root.rightChild;
            } else if (root.rightChild == null) {
                return root.leftChild;
            } else {
                NodeBST<K, T> minRight = this.rightChild.min();
                root.key = minRight.key;
                root.data = minRight.data;

                root.rightChild = root.rightChild.nodeDelete(root.rightChild, root.key);
            }
        }
        return root;
    }

    public LinkedList<K> nodeInOrder(LinkedList<K> inOrderList, NodeBST<K, T> node) {
        if (node == null) {
            return null;
        }
        nodeInOrder(inOrderList, node.leftChild);
        inOrderList.add(node.key);
        nodeInOrder(inOrderList, node.rightChild);
        return inOrderList;
    }

    public LinkedList<K> nodePostOrder(LinkedList<K> postOrderList, NodeBST<K, T> node) {
        if (node == null) {
            return null;
        }
        nodePostOrder(postOrderList, node.leftChild);
        nodePostOrder(postOrderList, node.rightChild);
        postOrderList.add(node.key);
        return postOrderList;
    }

    public LinkedList<K> nodePreOrder(LinkedList<K> preOrderList, NodeBST<K, T> node) {
        if (node == null) {
            return null;
        }
        preOrderList.add(node.key);
        nodePreOrder(preOrderList, node.leftChild);
        nodePreOrder(preOrderList, node.rightChild);
        return preOrderList;
    }

    public NodeBST<K, T> min() {
        if (this.leftChild != null) {
            return this.leftChild.min();
        } else {
            return this;
        }
    }

    public NodeBST<K, T> max() {
        if (this.rightChild != null) {
            return this.rightChild.max();
        } else {
            return this;
        }
    }

    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild == null;
    }
}
