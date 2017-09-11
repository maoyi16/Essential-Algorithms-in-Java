package graph;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

public class Topological {
    private static ArrayDeque<Integer> order;


    public static Iterable<Integer> DfsApproach(Digraph G) {

        boolean[] marked = new boolean[G.V()];
        boolean[] onStack = new boolean[G.V()];
        order = new ArrayDeque<>();
        for (int v : G.vertices())
            if (!marked[v] && dfs(G, v, onStack, marked))
                return null;
        return order;
    }

    /**
     *
     * @param G a digraph
     * @param v current vertex
     * @param onStack a temporary mark
     * @param marked a permanent mark
     * @return whether a cycle has been found
     */
    private static boolean dfs(Digraph G, int v, boolean[] onStack, boolean[] marked) {
        onStack[v] = true;
        for (int w : G.adj(v))
            if (onStack[w] || !marked[w] && dfs(G, w, onStack, marked))
                return true;
        onStack[v] = false;
        marked[v] = true;
        order.push(v);
        return false;
    }

    public static Iterable<Integer> BfsApproach(Digraph G) {
        int[] inDegree = new int[G.V()];
        for (int v : G.vertices())
            for (int w : G.adj(v))
                inDegree[w]++;

        ArrayDeque<Integer> removable = new ArrayDeque<>();
        for (int v : G.vertices())
            if (inDegree[v] == 0)
                removable.offer(v);

        int removed = 0;
        order = new ArrayDeque<>();
        while (!removable.isEmpty()) {
            int v = removable.poll();
            order.offer(v);
            removed++;
            for (int w : G.adj(v))
                if (--inDegree[w] == 0)
                    removable.offer(w);
        }

        if (removed != G.V())
            return null;
        return order;
    }
}
