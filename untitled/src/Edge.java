class Edge {
    private Vertex destination;
    private double weight;

    public Edge(Vertex destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}