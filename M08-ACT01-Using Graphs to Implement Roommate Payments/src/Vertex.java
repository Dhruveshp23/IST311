import java.util.*;
class Vertex {
    char label; // Roommate name (A, B, C, etc.)
    List<Edge> edges;

    public Vertex(char label) {
        this.label = label;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}