package graph;

public class DirectedEdge {
    final int from;
    final int to;
    final double weight;

    DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return from + "->" + to + ", w = " + weight;
    }
}
