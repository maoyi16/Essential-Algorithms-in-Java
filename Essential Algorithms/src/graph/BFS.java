package graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by maoyi16 on 7/12/17.
 */
public class BFS {

    public static void iterative(Graph g) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[g.V()];
        for (int v : g.vertices()) {
            if (!visited[v]) {
                q.offer(v);
                visited[v] = true;
                while (!q.isEmpty()) {
                    int t = q.poll();
                    System.out.println(t);
                    for (int w : g.adj(t)) {
                        if (!visited[w]) {
                            visited[w] = true;
                            q.offer(w);
                        }
                    }
                }
            }
        }
    }
}
