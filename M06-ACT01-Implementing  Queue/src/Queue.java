public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;


    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return item;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
