package graph;

import java.util.ArrayDeque;
import java.util.List;

public class Cycle {
    private int[] previousVertex;
    private boolean[] marked;
    private ArrayDeque<Integer> cycle;
    public Cycle (Graph G) {
        if (hasSelfLoop(G)) return;
        if (hasParallelEdges(G)) return;
        marked = new boolean[G.V()];
        previousVertex = new int[G.V()];
        for (int s : G.vertices()) {
            if (!marked[s])
                dfs(G, s, -1);
        }
    }

    /*
    If the graph is not a simple graph, then it can have loop (a cycle that has only one edge,
    which starts and ends at a same vertex). The cycle will have a form of v-v
     */
    private boolean hasSelfLoop(Graph G) {
        for (int v : G.vertices()) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new ArrayDeque<>();
                    cycle.offer(v);
                    cycle.offer(v);
                    return true;
                }
            }
        }
        return false;
    }

    /*
    If the graph is not a simple graph, then it can have parallel edges (multiple edges between the same two vertices)
    The cycle will have of a form of v-w-v
     */
    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];
        for (int v : G.vertices()) {
            for (int w : G.adj(v)) {
                if (marked[w]) {
                    cycle = new ArrayDeque<>();
                    cycle.offer(v);
                    cycle.offer(w);
                    cycle.offer(v);
                    return true;
                }
                marked[w] = true;
            }
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    private void dfs(Graph G, int v, int from) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null)
                return;
            if (!marked[w]) {
                previousVertex[w] = v;
                dfs(G, w, v);
            } else if (w != from) {
                cycle = new ArrayDeque<>();
                cycle.push(w);
                for (int x = v; x != w; x = previousVertex[x])
                    cycle.push(x);
                cycle.push(w);
                return;
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}
