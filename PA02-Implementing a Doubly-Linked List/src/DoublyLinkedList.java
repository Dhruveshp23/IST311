import java.util.ArrayList;
import java.util.List;

/**
 * Represents a doubly linked list.
 *
 * @param <T> the type of elements stored in the list
 */
public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Returns the size of the linked list.
     *
     * @return the size of the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Represents a node in the doubly linked list.
     *
     * @param <T> the type of data stored in the node
     */
    private static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        /**
         * Constructs a new node with the specified data.
         *
         * @param data the data to be stored in the node
         */
        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Checks if the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends a new node with the given data to the end of the linked list.
     * @param data the data to be appended
     * @return the newly created node
     */
    public Node<T> append(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * Inserts a new node with the given data at the specified location in the linked list.
     * @param location the location at which to insert the new node
     * @param data     the data to be inserted
     * @return the newly created node
     * @throws IllegalArgumentException if the location is invalid
     */
    public Node<T> insert(int location, T data) {
        if (location < 0 || location > size) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
        Node<T> newNode = new Node<>(data);
        if (location == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode; // Set tail to newNode when the list is empty
            }
        } else if (location == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < location - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * Deletes the node at the specified location in the linked list.
     * @param location the location of the node to be deleted
     * @return the deleted node
     * @throws IllegalArgumentException if the location is invalid
     */
    public Node<T> delete(int location) {
        if (location < 0 || location >= size) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
        Node<T> deletedNode;
        if (location == 0) {
            deletedNode = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            if (deletedNode == tail) {
                tail = null;
            }
        } else if (location == size - 1) {
            deletedNode = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> current = head;
            for (int i = 0; i < location; i++) {
                current = current.next;
            }
            deletedNode = current;
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        deletedNode.prev = null;
        deletedNode.next = null;
        size--;
        return deletedNode;
    }

    /**
     * Returns the index of the first occurrence of the specified data in the linked list.
     * @param data the data to search for
     * @return the index of the first occurrence of the data, or -1 if not found
     */
    public int getIndex(T data) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Returns a string representation of the linked list.
     * @return a string representation of the linked list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    /**
     * Shuffles the linked list by swapping adjacent nodes.
     * @return the head node of the shuffled linked list
     */
    public Node<T> shuffle() {
        if (size <= 1) {
            return head;
        }

        List<Node<T>> nodes = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }

        for (int i = 0; i < size - 1; i += 2) {
            Node<T> first = nodes.get(i);
            Node<T> second = nodes.get(i + 1);

            Node<T> temp = first.next;
            first.next = second.next;
            if (second.next != null) {
                second.next.prev = first;
            }
            second.next = temp;
            if (temp != null) {
                temp.prev = second;
            }

            first.prev = second;
            second.prev = first.prev.prev;
            if (second.prev != null) {
                second.prev.next = second;
            }

            if (i == 0) {
                head = second;
            } else if (i == size - 2) {
                tail = first;
            }
        }

        return head;
    }

    /**
     * Partitions the linked list by moving nodes with data greater than or equal to the given data
     * to a new linked list and removing them from the original list.
     *
     * @param data the data used for partitioning
     * @return a new linked list containing the partitioned nodes
     */
    public DoublyLinkedList<T> partition(T data) {
        DoublyLinkedList<T> newList = new DoublyLinkedList<>();

        Node<T> current = head;
        while (current != null) {
            if (current.data instanceof Comparable && ((Comparable<T>) current.data).compareTo(data) >= 0) {
                newList.append(current.data);
                if (current == head) {
                    head = current.next;
                } else {
                    current.prev.next = current.next;
                }
                if (current == tail) {
                    tail = current.prev;
                } else {
                    current.next.prev = current.prev;
                }
                size--;
            }
            current = current.next;
        }

        newList.tail = tail;
        if (tail != null) {
            tail.next = null;
        }
        return newList;
    }
}
