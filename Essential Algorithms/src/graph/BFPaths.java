package graph;

import java.util.ArrayDeque;

public class BFPaths {
    private final int s;
    private int[] previousVertex;
    private boolean[] marked;
    public BFPaths(Graph G, int s) {
        this.s = s;
        previousVertex = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        marked[v] = true;
        q.offer(v);
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int w : G.adj(t)) {
                if (!marked[w]) {
                    marked[w] = true;
                    previousVertex[w] = t;
                    q.offer(w);
                }
            }
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
