package graph;

import java.util.ArrayDeque;

public class DirectedCycle {
    boolean[] onStack;
    boolean[] marked;
    int[] previousVertex;
    ArrayDeque<Integer> cycle;

    public DirectedCycle (Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        previousVertex = new int[G.V()];
        for (int v : G.vertices()) {
            if (!marked[v] && cycle == null)
                dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null)
                return;
            if (!marked[w]) {
                previousVertex[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new ArrayDeque<>();
                cycle.push(w);
                for (int x = v; x != w; x = previousVertex[x])
                    cycle.push(x);
                cycle.push(w);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
