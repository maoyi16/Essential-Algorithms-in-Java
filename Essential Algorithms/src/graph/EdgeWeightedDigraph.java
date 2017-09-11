package graph;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private List<List<DirectedEdge>> adj;
    private List<DirectedEdge> edges;

    public EdgeWeightedDigraph(int v) {
        V = v;
        adj = new ArrayList<>();
        edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, double weight) {
        validateVertex(from);
        validateVertex(to);
        if (Double.isNaN(weight))
            throw new IllegalArgumentException("Weight must be a number");
        DirectedEdge e = new DirectedEdge(from, to, weight);
        adj.get(from).add(e);
        edges.add(e);
        E++;
    }

    public int V() {return V;}

    public int E() {return E;}

    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj.get(v);
    }

    public Iterable<DirectedEdge> edges() {
        return edges;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex must be in the range of [0, " + V + ")");
    }
}
