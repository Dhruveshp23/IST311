package edu;

import java.util.ArrayList;
import java.util.Collections;

public class MaxHeap<T extends Comparable<T>> {
    protected ArrayList<T> heap;
    protected int size;

    public MaxHeap(){
        this.heap = new ArrayList<>();
        this.size = 0;
    }
    // insert
    public void insert(T data) {
        this.heap.add(data);
        this.size = this.size + 1;
        this.heapifyUp(this.size - 1);

    }

    // extract
    protected T extract(){
        if (this.size > 0) {
            T temp = this.heap.get(0);

            this.heap.set(0, this.heap.get(this.size - 1));

            this.heap.remove(this.size - 1);
            this.size = this.size - 1;
            if (this.size > 1) {
                this.heapifyDown(0);
            }
            return temp;
        }
        return null;

    }

    // heapifyUp
    protected void heapifyUp(int index) {
        int parentIndex = (int) Math.floor((index - 1) / 2);
        T parent = this.heap.get(parentIndex);
        T child = this.heap.get(index);
        if (parent.compareTo(child) < 0) {
            this.heap.set(parentIndex, child);
            this.heap.set(index, parent);
            this.heapifyUp(parentIndex);
        }
    }

    //heapifyDown
    protected void heapifyDown(int index){
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        int maxIndex = index;
        if (leftIndex < this.size && this.heap.get(leftIndex).compareTo(this.heap.get(maxIndex)) > 0){
            maxIndex = leftIndex;
        }

        if (rightIndex < this.size && this.heap.get(rightIndex).compareTo(this.heap.get(maxIndex)) > 0){
            maxIndex = rightIndex;
        }

        if (maxIndex != index){

            T temp = this.heap.get(index);
            this.heap.set(index, this.heap.get(maxIndex));
            this.heap.set(maxIndex, temp);

            this.heapifyDown(maxIndex);
        }

    }
    // sort
    public ArrayList<T> sort() {
        ArrayList<T> sorted = new ArrayList<>();
        while (this.size > 0) {
            sorted.add(this.extract());
        }
        Collections.reverse(sorted);
        return sorted;
    }

}
