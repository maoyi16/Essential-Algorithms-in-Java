package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Kosaraju's algorithm for finding strong connected components in a digraph
 */
public class StrongConnectedComponent {
    int[] id;
    ArrayDeque<Integer> postOrder;
    boolean[] marked;
    int count;

    public StrongConnectedComponent(Digraph G) {
        id = new int[G.V()];
        marked = new boolean[G.V()];
        postOrder = new ArrayDeque<>();
        for (int v : G.vertices())
            if (!marked[v])
                dfs(G, v);

        Arrays.fill(marked,false);
        Digraph reverseG = new Digraph(G.V());
        for (int v : G.vertices())
            for (int w : G.adj(v))
                reverseG.addEdge(w, v);

        count = 0;
        for (int v : postOrder) {
            if (!marked[v]) {
                assign(reverseG, v);
                count++;
            }
        }
    }

    /*
    Find vertices that are reachable from v
     */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);

        postOrder.push(v);
    }

    /*
    Find vertices that can reach v from vertices that are reachable from v
    In this way, we make sure that v and w are reachable from each other
     */
    private void assign(Digraph reverseG, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : reverseG.adj(v))
            if (!marked[w])
                assign(reverseG, w);
    }

    public int count() {
        return count;
    }

    public boolean strongConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length)
            throw new IllegalArgumentException("Vertex must be in the range of [0, " + marked.length + ")");
    }

    public Iterable<Iterable<Integer>> components() {
        List<Iterable<Integer>> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new ArrayList<>());
        }
        for (int v = 0; v < marked.length; v++) {
            ((List<Integer>)result.get(id[v])).add(v);
        }
        return result;
    }
}
