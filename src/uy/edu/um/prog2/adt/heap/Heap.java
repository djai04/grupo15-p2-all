package uy.edu.um.prog2.adt.heap;

public class Heap  <K extends Comparable<K>, V>  implements IHeap <K, V> {
    private HeapNode<K, V>[] heapList;
    private boolean isMin;
    private int size;

    public Heap() {
        this.heapList = new HeapNode[8];
        this.isMin = true;
        this.size = 0;
    }
    public Heap(boolean isMin) {
        this.heapList = new HeapNode[8];
        this.isMin = isMin;
        this.size = 0;
    }

    @Override
    public void push(K keyToPush, V valueToPush) {
        // Elementos iguales?
        // HeapMax?
        // Clones vs =?
        HeapNode<K, V> nodeToPush = new HeapNode<>(keyToPush, valueToPush);

        if (this.size == this.heapList.length) {
            this.resize();
        }

        if (this.size == 0) {
            this.heapList[size] = nodeToPush;
            this.size++;
        } else if (this.heapList[this.size] == null) {
            this.heapList[this.size] = nodeToPush;

            int positionToPush = this.size;
            int parentPosition = (positionToPush - 1) / 2;

            while (this.heapList[positionToPush].getKey().compareTo(this.heapList[parentPosition].getKey()) < 0) {
                this.swap(positionToPush, parentPosition);
                positionToPush = parentPosition;
                if (positionToPush == 0) {
                    break;
                }
                parentPosition = (positionToPush - 1) / 2;
            }
            this.size++;
        }
    }

    @Override
    public V peek() {
        if (this.size > 0) {
            return this.heapList[0].getValue();
        } else {
            return null;
        }
    }

    @Override
    public V pop() {
        // Clone issues?

        if (this.size == 0) {
            return null;
        }

        HeapNode<K, V> poppedNode = this.heapList[0];
        this.swap(0, this.size - 1);
        this.heapList[size - 1] = null;
        this.size--;

        int positionToFix = 0;
        int leftChild = (2 * positionToFix) + 1;
        int rightChild = (2 * positionToFix) + 2;

        // Muy raro, revisar
        if (rightChild >= this.size && leftChild < this.size) { // Tiene hijo izquierdo y no derecho
            if (this.heapList[leftChild].getKey().compareTo(this.heapList[positionToFix].getKey()) < 0) { // Si el izquierdo es mas chico
                this.swap(positionToFix, leftChild);
            }
            return poppedNode.getValue();
        } else if (rightChild >= this.size && leftChild >= this.size) { // No tiene hijos
            return poppedNode.getValue();
        }
        // Fin de lo raro

        while (this.heapList[positionToFix].getKey().compareTo(this.heapList[leftChild].getKey()) > 0
                || this.heapList[positionToFix].getKey().compareTo(this.heapList[rightChild].getKey()) > 0) {
            if (this.heapList[leftChild].getKey().compareTo(this.heapList[rightChild].getKey()) < 0) { // Si el hijo izquierdo es mas chico
                this.swap(positionToFix, leftChild);
                positionToFix = leftChild;
            } else if (this.heapList[rightChild].getKey().compareTo(this.heapList[leftChild].getKey()) < 0) { // Si el hijo derecho es mas chico
                this.swap(positionToFix, rightChild);
                positionToFix = rightChild;
            } else {
                System.err.println("Error inesperado en el pop del heap");
            }

            leftChild = (2 * positionToFix) + 1;
            rightChild = (2 * positionToFix) + 2;

            if (rightChild >= this.size && leftChild < this.size) { // Tiene hijo izquierdo y no derecho
                if (this.heapList[leftChild].getKey().compareTo(this.heapList[positionToFix].getKey()) < 0) { // Si el izquierdo es mas chico
                    this.swap(positionToFix, leftChild);
                }
                break;
            } else if (rightChild >= this.size && leftChild >= this.size) { // No tiene hijos
                break;
            }
        }
        return poppedNode.getValue();
    }

    public void swap(int position1, int position2) {
        // Capaz da problemas por el clone.
        HeapNode<K, V> pivot = this.heapList[position1];
        this.heapList[position1] = this.heapList[position2];
        this.heapList[position2] = pivot;

    }

    public void resize() {
        HeapNode<K, V>[] newHeapList = new HeapNode[this.heapList.length * 2];
        System.arraycopy(this.heapList, 0, newHeapList, 0, this.heapList.length);
        this.heapList = newHeapList;
    }
}
