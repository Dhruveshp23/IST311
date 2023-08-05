import java.util.ArrayList;

class Vertex {
    private String location;
    private ArrayList<Edge> edges;
    private boolean visited;

    public Vertex(String location) {
        this.location = location;
        edges = new ArrayList<>();
        visited = false;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return location;
    }
}