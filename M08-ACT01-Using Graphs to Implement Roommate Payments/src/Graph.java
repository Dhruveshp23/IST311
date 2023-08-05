import java.util.*;

// Graph class
class Graph {
    List<Vertex> vertices;

    public Graph() {
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.edges) {
                sb.append("[").append(vertex).append(", ").append(edge).append("], ");
            }
        }
        // Remove the trailing comma and space
        return sb.length() > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }
}
