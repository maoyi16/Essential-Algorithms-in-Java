package graph;

import data_structures.UnionFind;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalMST {
    private List<Edge> edges;
    private double weight;
    public KruskalMST(EdgeWeightedGraph G) {
        edges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((x,y)->Double.compare(x.weight, y.weight));
        for (Edge edge : G.edges()) {
            pq.offer(edge);
        }
        UnionFind uf = new UnionFind(G.V());
        while (!pq.isEmpty() && uf.count() != 1) {
            Edge e = pq.poll();
            if (uf.union(e.v, e.w)) {
                edges.add(e);
                weight += e.weight;
            }
        }
    }

    public double weight() {
        return weight;
    }
    @Override
    public String toString() {
        return edges.toString();
    }
}
