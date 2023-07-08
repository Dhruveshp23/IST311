import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int size() {
        return size;
    }

    private static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

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
