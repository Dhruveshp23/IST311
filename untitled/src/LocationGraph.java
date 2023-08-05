import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LocationGraph {
    private HashMap<String, Vertex> vertices;

    public LocationGraph() {
        vertices = new HashMap<>();
    }

    public boolean addLocation(String location) {
        if (vertices.containsKey(location)) {
            return false;
        }
        vertices.put(location, new Vertex(location));
        return true;
    }

    public boolean addDistance(String locationA, String locationB, Double distance) {
        Vertex vertexA = vertices.get(locationA);
        Vertex vertexB = vertices.get(locationB);
        if (vertexA == null) {
            vertexA = new Vertex(locationA);
            vertices.put(locationA, vertexA);
        }
        if (vertexB == null) {
            vertexB = new Vertex(locationB);
            vertices.put(locationB, vertexB);
        }
        for (Edge edge : vertexA.getEdges()) {
            if (edge.getDestination() == vertexB) {
                return false;
            }
        }
        Edge edgeA = new Edge(vertexB, distance);
        Edge edgeB = new Edge(vertexA, distance);
        vertexA.addEdge(edgeA);
        vertexB.addEdge(edgeB);
        return true;
    }

    public double findDistanceBreadthFirst(String locationA, String locationB) {
        if (!vertices.containsKey(locationA) || !vertices.containsKey(locationB)) {
            return -1;
        }
        Vertex startVertex = vertices.get(locationA);
        Vertex endVertex = vertices.get(locationB);
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(startVertex);
        HashMap<Vertex, Double> distances = new HashMap<>();
        distances.put(startVertex, 0.0);
        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            if (currentVertex == endVertex) {
                return distances.get(currentVertex);
            }
            for (Edge edge : currentVertex.getEdges()) {
                Vertex neighbor = edge.getDestination();
                if (!distances.containsKey(neighbor)) {
                    double distanceToNeighbor = distances.get(currentVertex) + edge.getWeight();
                    distances.put(neighbor, distanceToNeighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return -1;
    }

    public double findDistanceDepthFirst(String locationA, String locationB) {
        if (!vertices.containsKey(locationA) || !vertices.containsKey(locationB)) {
            return -1;
        }
        Vertex startVertex = vertices.get(locationA);
        Vertex endVertex = vertices.get(locationB);
        HashMap<Vertex, Double> distances = new HashMap<>();
        distances.put(startVertex, 0.0);
        dfs(startVertex, endVertex, distances);
        return distances.getOrDefault(endVertex, -1.0);
    }

    private void dfs(Vertex currentVertex, Vertex endVertex, HashMap<Vertex, Double> distances) {
        for (Edge edge : currentVertex.getEdges()) {
            Vertex neighbor = edge.getDestination();
            if (!distances.containsKey(neighbor)) {
                double distanceToNeighbor = distances.get(currentVertex) + edge.getWeight();
                distances.put(neighbor, distanceToNeighbor);
                if (neighbor == endVertex) {
                    return;
                }
                dfs(neighbor, endVertex, distances);
            }
        }
    }

    public boolean detectCycle() {
        for (Vertex vertex : vertices.values()) {
            if (!vertex.isVisited()) {
                if (dfsDetectCycle(vertex, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsDetectCycle(Vertex currentVertex, Vertex parentVertex) {
        currentVertex.setVisited(true);
        for (Edge edge : currentVertex.getEdges()) {
            Vertex neighbor = edge.getDestination();
            if (!neighbor.isVisited()) {
                if (dfsDetectCycle(neighbor, currentVertex)) {
                    return true;
                }
            } else if (!neighbor.equals(parentVertex)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex vertex : vertices.values()) {
            sb.append(vertex).append(": ");
            for (Edge edge : vertex.getEdges()) {
                sb.append(edge.getDestination()).append("(");
                sb.append(edge.getWeight()).append(")").append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public ArrayList<String> findMinimumPath(String locationA, String locationB) {
        if (!vertices.containsKey(locationA) || !vertices.containsKey(locationB)) {
            return null;
        }
        Vertex startVertex = vertices.get(locationA);
        Vertex endVertex = vertices.get(locationB);
        HashMap<Vertex, Double> distances = new HashMap<>();
        HashMap<Vertex, Vertex> previous = new HashMap<>();
        ArrayList<Vertex> unvisited = new ArrayList<>(vertices.values());
        distances.put(startVertex, 0.0);
        while (!unvisited.isEmpty()) {
            Vertex currentVertex = getVertexWithMinDistance(distances, unvisited);
            unvisited.remove(currentVertex);
            if (currentVertex == endVertex) {
                break;
            }
            for (Edge edge : currentVertex.getEdges()) {
                Vertex neighbor = edge.getDestination();
                if (!unvisited.contains(neighbor)) {
                    continue;
                }
                double tentativeDistance = distances.get(currentVertex) + edge.getWeight();
                if (tentativeDistance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, tentativeDistance);
                    previous.put(neighbor, currentVertex);
                }
            }
        }
        ArrayList<String> path = new ArrayList<>();
        Vertex currentVertex = endVertex;
        while (previous.containsKey(currentVertex)) {
            path.add(0, currentVertex.getLocation());
            currentVertex = previous.get(currentVertex);
        }
        if (path.isEmpty()) {
            return null;
        }
        path.add(0, startVertex.getLocation());
        return path;
    }

    private Vertex getVertexWithMinDistance(HashMap<Vertex, Double> distances, ArrayList<Vertex> unvisited) {
        Vertex minVertex = null;
        double minDistance = Double.MAX_VALUE;
        for (Vertex vertex : unvisited) {
            double distance = distances.getOrDefault(vertex, Double.MAX_VALUE);
            if (distance < minDistance) {
                minVertex = vertex;
                minDistance = distance;
            }
        }
        return minVertex;
    }
}
