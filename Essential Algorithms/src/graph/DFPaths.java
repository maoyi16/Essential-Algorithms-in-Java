package graph;

import java.util.ArrayDeque;
import java.util.Stack;

public class DFPaths {
    private final int s;
    private int[] previousVertex;
    private boolean[] marked;
    public DFPaths(Graph G, int s) {
        this.s = s;
        previousVertex = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                previousVertex[w] = v;
                dfs(G, w);
            }
    }

    private void validateVertex(int i) {
        if (i < 0 || i >= marked.length)
            throw new IllegalArgumentException("Vertex is not in [0, " + marked.length + ")");
    }
    
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }
    
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v))
            return null;
        ArrayDeque<Integer> path = new ArrayDeque<>();
        for (int cur = v; cur != s; cur = previousVertex[cur])
            path.push(cur);
        path.push(s);
        return path;
    }
}
