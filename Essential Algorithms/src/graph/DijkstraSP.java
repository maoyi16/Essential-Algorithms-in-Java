package graph;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class DijkstraSP {
    private final int S;
    private final int V;
    private double[] dist;
    private DirectedEdge[] edgeTo;

    private class Pair implements Comparable<Pair> {
        final int v;
        final double d;
        public Pair(int v, double d) {
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Pair o) {
            return Double.compare(d, o.d);
        }
    }

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        S = s;
        V = G.V();
        dist = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.offer(new Pair(s, 0));
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            if (Double.compare(pair.d, dist[pair.v]) == 0) { // Make sure this pair is the newest pair
                for (DirectedEdge e : G.adj(pair.v)) {
                    double newDist = dist[e.from] + e.weight;
                    if (newDist < dist[e.to]) {
                        pq.offer(new Pair(e.to, newDist));
                        dist[e.to] = newDist;
                        edgeTo[e.to] = e;
                    }
                }
            }
        }
    }

    public boolean reachable(int v) {
        validateVertex(v);
        return dist[v] < Double.POSITIVE_INFINITY;
    }

    public Double distanceTo(int v) {
        validateVertex(v);
        return dist[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!reachable(v))
            return null;
        ArrayDeque<DirectedEdge> stack = new ArrayDeque<>();
        for (int x = v; x != S; x = edgeTo[x].from)
            stack.push(edgeTo[x]);
        return stack;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex must be in the range of [0, " + V + ")");
    }
}
