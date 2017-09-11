package graph;

import java.util.Stack;

/**
 * Created by maoyi16 on 7/12/17.
 */
public class DFS {

    private static void recursiveAux(Graph g, int v, boolean[] visited) {
        visited[v] = true;
        System.out.println(v);
        for (int w: g.adj(v)) {
            if (!visited[w])
                recursiveAux(g, w, visited);
        }
    }

    public static void recursive(Graph g) {
        boolean[] visited = new boolean[g.V()];
        for (int v : g.vertices()) {
            if (!visited[v])
                recursiveAux(g, v, visited);
        }
    }

    public static void iterative(Graph g) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[g.V()];
        for (int v : g.vertices()) {
            if (!visited[v]) {
                stack.push(v);
                while (!stack.isEmpty()) {
                    int t = stack.pop();
                    visited[t] = true;
                    System.out.println(t);
                    for (int w : g.adj(t)) {
                        if (!visited[w])
                            stack.push(w);
                    }
                }
            }
        }
    }
}
