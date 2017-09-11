package graph;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private List<Edge> edges;

    public EdgeWeightedGraph(int v) {
        V = v;
        edges = new ArrayList<>();
    }

    public void addEdge(int v, int w, double weight) {
        validateVertex(v);
        validateVertex(w);
        if (Double.isNaN(weight))
            throw new IllegalArgumentException("Weight is NaN");
        edges.add(new Edge(v, w, weight));
        E++;
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    public Iterable<Edge> edges() {
        return edges;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex must be in the range of [0, " + v + ")");
    }
}
