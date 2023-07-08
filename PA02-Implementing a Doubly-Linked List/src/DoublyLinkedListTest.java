import static org.junit.Assert.*;
import org.junit.Test;

public class DoublyLinkedListTest {

    @Test
    public void testEmptyConstructor() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals("NULL", list.toString());
    }

    @Test
    public void testAppend() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals("1 -> 2 -> 3 -> NULL", list.toString());
    }

    @Test
    public void testInsert() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.insert(0, "a");
        list.insert(1, "b");
        list.insert(1, "c");

        assertEquals("a -> c -> b -> NULL", list.toString());
    }

    @Test
    public void testDelete() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);

        list.delete(1);

        assertEquals("1 -> 3 -> NULL", list.toString());
    }

    @Test
    public void testGetIndex() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.append("a");
        list.append("b");
        list.append("c");

        int index = list.getIndex("b");

        assertEquals(1, index);
    }

    @Test
    public void testShuffle() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        list.shuffle();

        // Since the shuffling logic is randomized, we can only assert on the size
        assertEquals(4, list.size());
    }

    @Test
    public void testPartition() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        DoublyLinkedList<Integer> newList = list.partition(3);

        assertEquals("1 -> 2 -> NULL", list.toString());
        assertEquals("3 -> 4 -> NULL", newList.toString());
    }

    @Test
    public void testToString() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.append("a");
        list.append("b");
        list.append("c");

        assertEquals("a -> b -> c -> NULL", list.toString());
    }
}
