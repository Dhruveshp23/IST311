import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationGraphTest {

    @Test
    void testAddLocation() {
        LocationGraph graph = new LocationGraph();
        assertTrue(graph.addLocation("A"));
        assertTrue(graph.addLocation("B"));
        assertFalse(graph.addLocation("A")); // Location A already exists
    }

    @Test
    void testAddDistance() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("A");
        graph.addLocation("B");
        graph.addLocation("C");

        assertTrue(graph.addDistance("A", "B", 5.0));
        assertTrue(graph.addDistance("B", "C", 10.0));
        assertFalse(graph.addDistance("A", "B", 7.0)); // Edge already exists
    }

    @Test
    void testFindDistanceBreadthFirst() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("A");
        graph.addLocation("B");
        graph.addLocation("C");
        graph.addLocation("D");
        graph.addDistance("A", "B", 5.0);
        graph.addDistance("B", "C", 10.0);

        assertEquals(15.0, graph.findDistanceBreadthFirst("A", "C"));
        assertEquals(-1.0, graph.findDistanceBreadthFirst("A", "D")); // Location D doesn't exist
        assertEquals(-1.0, graph.findDistanceBreadthFirst("A", "B")); // There is no direct edge between A and C
    }


    @Test
    void testFindDistanceDepthFirst() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("A");
        graph.addLocation("B");
        graph.addLocation("C");
        graph.addLocation("D");
        graph.addDistance("A", "B", 5.0);
        graph.addDistance("B", "C", 10.0);

        assertEquals(15.0, graph.findDistanceDepthFirst("A", "C"));
        assertEquals(-1.0, graph.findDistanceDepthFirst("A", "D")); // Location D doesn't exist
        assertEquals(-1.0, graph.findDistanceDepthFirst("A", "B")); // There is no direct edge between A and C
    }


    @Test
    void testDetectCycle() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("A");
        graph.addLocation("B");
        graph.addLocation("C");
        graph.addLocation("D");
        graph.addDistance("A", "B", 5.0);
        graph.addDistance("B", "C", 10.0);
        graph.addDistance("C", "D", 8.0);

        assertFalse(graph.detectCycle());

        // Create a cycle
        graph.addDistance("A", "D", 15.0);
        assertTrue(graph.detectCycle());
    }

    @Test
    void testFindMinimumPath() {
        LocationGraph graph = new LocationGraph();
        graph.addLocation("A");
        graph.addLocation("B");
        graph.addLocation("C");
        graph.addLocation("D");
        graph.addDistance("A", "B", 5.0);
        graph.addDistance("B", "C", 10.0);
        graph.addDistance("C", "D", 8.0);
        graph.addDistance("A", "D", 15.0);

        List<String> minimumPath = graph.findMinimumPath("A", "D");
        assertEquals(3, minimumPath.size());
        assertEquals("A", minimumPath.get(0));
        assertEquals("B", minimumPath.get(1));
        assertEquals("D", minimumPath.get(2));

        List<String> noPath = graph.findMinimumPath("A", "E"); // Location E doesn't exist
        assertTrue(noPath.isEmpty());
    }
}
