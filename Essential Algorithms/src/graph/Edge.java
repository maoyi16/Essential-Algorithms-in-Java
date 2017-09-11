package graph;

public class Edge {
    final int v;
    final int w;
    final double weight;
    Edge (int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return v + "-" + w + ", w = " + weight;
    }
}
