package graph;

import java.util.ArrayDeque;
import java.util.List;

public class Bipartite {
    boolean[] color;
    boolean[] marked;
    int[] previousVertex;
    ArrayDeque<Integer> cycle;

    public Bipartite(Graph G, boolean useDFS) {
        color = new boolean[G.V()];
        marked = new boolean[G.V()];
        previousVertex = new int[G.V()];
        for (int v : G.vertices()) {
            if (!marked[v] && isBipartite()) {
                if (useDFS)
                    dfs(G, v);
                else
                    bfs(G, v);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null)  // Odd Cycle Found
                return;
            if (!marked[w]) {
                previousVertex[w] = v;
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                cycle = new ArrayDeque<>();
                cycle.push(w);
                for (int x = v; x != w; x = previousVertex[x])
                    cycle.push(x);
                cycle.push(w);
            }
        }
    }

    private void bfs(Graph G, int s) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        marked[s] = true;
        q.offer(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    previousVertex[w] = v;
                    color[w] = !color[v];
                    marked[w] = true;
                    q.offer(w);
                } else if (color[w] == color[v]) {
                    cycle = new ArrayDeque<>();
                    ArrayDeque<Integer> stack = new ArrayDeque<>();
                    int x = v, y = w;
                    while (x != y) {
                        cycle.offer(x);
                        stack.push(y);
                        x = previousVertex[x];
                        y = previousVertex[y];
                    }
                    stack.push(x);
                    while (!stack.isEmpty())
                        cycle.offer(stack.pop());
                    cycle.offer(v);
                    return;
                }
            }
        }
    }

    public boolean isBipartite() {
        return cycle == null;
    }
    
    public boolean color(int v) {
        validateVertex(v);
        if (!isBipartite())
            throw  new UnsupportedOperationException("Graph is not bipartite");
        return color[v];
    }
    
    public Iterable<Integer> oddCycle() {
        return cycle;
    }
    
    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length)
            throw new IllegalArgumentException("Vertex must be in the range of [0, " + marked.length + ")");
    }
}
