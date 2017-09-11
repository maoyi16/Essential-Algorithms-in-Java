package graph;

public class ConnectedComponent {
    private int count;
    private boolean[] marked;
    private int[] id;
    private int[] size;
    public ConnectedComponent(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v : G.vertices()) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    public int count() {
        return count;
    }

    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length)
            throw new IllegalArgumentException("Vertex should be in the range of [0, " + marked.length + ")");
    }
}
