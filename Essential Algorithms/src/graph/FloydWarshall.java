package graph;

import java.util.ArrayDeque;
import java.util.Arrays;

public class FloydWarshall {
    double[][] dist;    // dist[v][w] the shortest distance from v to w.
    DirectedEdge[][] edgeTo;    // edgeTo[v][w] = the last edge used in the shortest path from v to w.
    private boolean hasNegativeWeightCycle;
    final int V;

    public FloydWarshall(EdgeWeightedDigraph G) {
        V = G.V();
        dist = new double[V][V];
        edgeTo = new DirectedEdge[V][V];
        // Initialization
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (DirectedEdge e : G.edges()) {
            dist[e.from][e.to] = e.weight;
            edgeTo[e.from][e.to] = e;
        }
        // Floyd Warshall Algorithm
        for (int i = 0; i < V; i++) {
            /*
             Update the shortest path
             Before: Only vertices 0...i-1 can be used in the path
             After: Vertex i can also be used, in addition to vertices 0...i-1
              */
            for (int v = 0; v < V; v++) {
                if (edgeTo[v][i] == null) // can skip some unnecessary computation if i is unreachable from v
                    continue;
                for (int w = 0; w < V; w++) {
                    if (dist[v][i] + dist[i][w] < dist[v][w]) {
                        dist[v][w] = dist[v][i] + dist[i][w];
                        edgeTo[v][w] = edgeTo[i][w];
                    }
                }
                if (dist[v][v] < 0) {
                    hasNegativeWeightCycle = true;
                    return;
                }
            }
        }
    }

    public boolean hasNegativeWeightCycle() {
        return hasNegativeWeightCycle;
    }

    public Iterable<DirectedEdge> path(int v, int w) {
        assert reachable(v, w);
        ArrayDeque<DirectedEdge> stack = new ArrayDeque<>();
        for (int x = w; x != v; x = edgeTo[v][x].from) {
            stack.push(edgeTo[v][x]);
        }
        return stack;
    }

    public boolean reachable(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return dist[v][w] < Double.POSITIVE_INFINITY;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex must be in the range of [0, " + V + ")");
    }
}
