class Edge {
    double weight;
    Vertex targetVertex;

    public Edge(double weight, Vertex targetVertex) {
        this.weight = weight;
        this.targetVertex = targetVertex;
    }

    @Override
    public String toString() {
        return "[" + targetVertex + ", $" + weight + "]";
    }
}