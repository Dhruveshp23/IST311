import java.util.ArrayList;
import java.util.List;


// Implement the Vertex and Edge classes as shown in Step 2.

public class RoommatePayments {

    // Helper function to perform Depth-First Search (DFS)
    private static boolean dfs(Vertex current, Vertex target, List<Vertex> visited) {
        if (current == target) {
            return true;
        }
        visited.add(current);
        for (Edge edge : current.edges) {
            if (!visited.contains(edge.targetVertex)) {
                if (dfs(edge.targetVertex, target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper function to find all cycles in the graph
    private static List<List<Vertex>> findAllCycles(Graph graph) {
        List<List<Vertex>> cycles = new ArrayList<>();
        for (Vertex vertex : graph.vertices) {
            List<Vertex> visited = new ArrayList<>();
            for (Edge edge : vertex.edges) {
                List<Vertex> cycle = new ArrayList<>();
                cycle.add(vertex);
                if (dfs(edge.targetVertex, vertex, cycle)) {
                    cycles.add(cycle);
                }
            }
        }
        return cycles;
    }

    // Main algorithm to simplify roommate payments
    public static void simplifyPayments(Graph graph) {
        List<List<Vertex>> cycles = findAllCycles(graph);
        while (!cycles.isEmpty()) {
            for (List<Vertex> cycle : cycles) {
                double minWeight = Double.MAX_VALUE;
                for (int i = 0; i < cycle.size(); i++) {
                    Vertex from = cycle.get(i);
                    Vertex to = cycle.get((i + 1) % cycle.size());
                    for (Edge edge : from.edges) {
                        if (edge.targetVertex == to) {
                            minWeight = Math.min(minWeight, edge.weight);
                            break;
                        }
                    }
                }
                for (int i = 0; i < cycle.size(); i++) {
                    Vertex from = cycle.get(i);
                    Vertex to = cycle.get((i + 1) % cycle.size());
                    for (Edge edge : from.edges) {
                        if (edge.targetVertex == to) {
                            edge.weight -= minWeight;
                            break;
                        }
                    }
                }
            }
            cycles = findAllCycles(graph);
        }
    }

    public static void main(String[] args) {
        // Create vertices
        Vertex A = new Vertex('A');
        Vertex B = new Vertex('B');
        Vertex C = new Vertex('C');

        // Create edges
        Edge edge1 = new Edge(10.00, B);
        Edge edge2 = new Edge(15.00, C);
        Edge edge3 = new Edge(5.00, C);

        // Connect edges to vertices
        A.addEdge(edge1);
        A.addEdge(edge2);
        B.addEdge(edge3);

        // Create the graph
        Graph graph = new Graph();
        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);

        // Print the graph representation
        System.out.println("Initial Graph: " + graph.toString());


    }
}
