package edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {
    @Test
    public void testHeapifyup() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.heap.add(11);
        maxHeap.heap.add(5);
        maxHeap.heap.add(8);
        maxHeap.heap.add(3);
        maxHeap.heap.add(4);
        maxHeap.heap.add(15);
        maxHeap.size = 6;

        maxHeap.heapifyUp(5);
        assertEquals(15, maxHeap.heap.get(0));
    }


    @Test
    public void testInsert() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.insert(0);
        maxHeap.insert(100);
        maxHeap.insert(40);
        maxHeap.insert(1);
        maxHeap.insert(75);
        maxHeap.insert(50);

        assertEquals("[100, 75, 50, 0, 1, 40]", maxHeap.heap.toString());
    }

    @Test
    public void testHeapifyDown() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.insert(1);
        maxHeap.insert(11);
        maxHeap.insert(5);
        maxHeap.insert(8);
        maxHeap.insert(3);
        maxHeap.insert(4);

        maxHeap.size = 6;
        maxHeap.heapifyDown(0);

        assertEquals(11, maxHeap.heap.get(0));
        assertEquals("[11, 8, 5, 1, 3, 4]", maxHeap.heap.toString());
    }
    @Test
    public void testExtractFullHeap() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.heap.add(11);
        maxHeap.heap.add(5);
        maxHeap.heap.add(8);
        maxHeap.heap.add(3);
        maxHeap.heap.add(4);
        maxHeap.heap.add(1);

        maxHeap.size = 6;

        assertEquals(11,maxHeap.extract());
        assertEquals(5,maxHeap.size);
        assertEquals(8,maxHeap.extract());
        assertEquals(5,maxHeap.extract());
        assertEquals(4,maxHeap.extract());
        assertEquals(3,maxHeap.extract());
        assertEquals(1,maxHeap.extract());
        assertEquals(null,maxHeap.extract());
    }

    @Test
    public void testSort() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.insert(0);
        maxHeap.insert(100);
        maxHeap.insert(40);
        maxHeap.insert(1);
        maxHeap.insert(75);
        maxHeap.insert(50);

        ArrayList<Integer> sorted = maxHeap.sort();
        assertEquals("[0, 1, 40, 50, 75, 100]", sorted.toString());
    }
}
