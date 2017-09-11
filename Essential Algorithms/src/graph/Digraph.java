package graph;

import java.util.ArrayList;
import java.util.List;

public class Digraph {
    private final int V;
    private int E;
    private List<Integer> vertices;
    private List<List<Integer>> adj;

    public Digraph(int v) {
        V = v;
        vertices = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            vertices.add(i);
            adj.add(new ArrayList<>());
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> vertices() {
        return vertices;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj.get(v).add(w);
        E++;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj.get(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex - Edges\n");
        for (int v : vertices) {
            sb.append(v).append(" - ");
            for (int w: adj.get(v)) {
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
