import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testEnqueue() {
        Queue<Integer> queue = new Queue<>();
        assertTrue(queue.isEmpty());

        queue.enqueue(1);
        assertFalse(queue.isEmpty());

        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, (int) queue.dequeue());
        assertEquals(2, (int) queue.dequeue());
        assertEquals(3, (int) queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeue() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("1");
        queue.enqueue("2");

        assertEquals("1", queue.dequeue());
        assertFalse(queue.isEmpty());
        assertEquals("2", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testDequeueEmptyQueue() {
        Queue<Integer> queue = new Queue<>();
        queue.dequeue(); // Attempting to dequeue an empty queue should throw an exception
    }
}
