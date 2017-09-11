package graph;

import java.util.ArrayDeque;

public class BellmanFordSP {
    final int S;
    final int V;
    private double[] dist;
    private DirectedEdge[] edgeTo;
    private boolean hasNegativeWeightCycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        S = s;
        V = G.V();
        validateVertex(s);
        dist = new double[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }
        dist[s] = 0;
        edgeTo = new DirectedEdge[V];
        // NOTE: Only V - 1 iterations
        for (int i = 1; i < V; i++) {
            for (DirectedEdge e : G.edges()) {
                if (dist[e.to] > dist[e.from] + e.weight) {
                    dist[e.to] = dist[e.from] + e.weight;
                    edgeTo[e.to] = e;
                }
            }
        }
        for (DirectedEdge e : G.edges()) {
            if (dist[e.to] > dist[e.from] + e.weight) { // Found A Negative Weight Cycle
                hasNegativeWeightCycle = true;
                break;
            }
        }
    }

    public boolean reachable(int v) {
        validateVertex(v);
        return dist[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        ArrayDeque<DirectedEdge> stack = new ArrayDeque<>();
        for (int x = v; x != S; x = edgeTo[x].from) {
            stack.push(edgeTo[x]);
        }
        return stack;
    }

    public boolean hasNegativeWeightCycle() {
        return hasNegativeWeightCycle;
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex must be in the range of [0, " + V + ")");
    }
}
