package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maoyi16 on 7/12/17.
 */
public class GraphTest {
    private Graph G;
    private Graph NB;
    private Digraph DG;
    private Digraph DG2;
    private EdgeWeightedGraph EWG;
    private EdgeWeightedDigraph EWDG;
    private EdgeWeightedDigraph EWDG2;

    @Before
    public void setUp() throws Exception {
        G = new Graph(10);
        G.addEdge(0, 1);
        G.addEdge(2, 3);
        G.addEdge(4, 5);
        G.addEdge(1, 4);
        G.addEdge(5, 3);
        G.addEdge(7, 9);
        G.addEdge(9, 8);
        NB = new Graph(10);
        NB.addEdge(0, 1);
        NB.addEdge(0, 3);
        NB.addEdge(1, 2);
        NB.addEdge(2, 5);
        NB.addEdge(3, 4);
        NB.addEdge(4, 0);
        NB.addEdge(5, 6);
        NB.addEdge(6, 0);
        DG = new Digraph(10);
//        DG.addEdge(0, 1);
//        DG.addEdge(1, 2);
//        DG.addEdge(2,0);
        DG.addEdge(3, 4);
        DG.addEdge(4, 5);
        DG.addEdge(5, 6);
        DG.addEdge(6, 3);
        DG.addEdge(7, 8);
        DG.addEdge(6, 8);
        DG.addEdge(8, 9);
        DG.addEdge(9, 8);

        DG2 = new Digraph(10); // an acyclic digraph
        DG2.addEdge(0, 1);
        DG2.addEdge(1, 2);
        DG2.addEdge(1, 3);
        DG2.addEdge(3, 4);
        DG2.addEdge(5, 6);
        DG2.addEdge(5, 7);
        DG2.addEdge(5, 8);
        DG2.addEdge(7, 9);
        DG2.addEdge(5, 9);
        /*
        0  - (W=1) - 1 - (W=6) - 2              0  - (W=1) - 1           2
        |          * |         * |                           |           |
        |       *    |       *   |                           |           |
      (W=3) (W=5)  (W=1) (W=5) (W=2)  ------>              (W=1)       (W=2)    Total weight = 3 + 4 + 2 = 9
        |   *        |   *       |                           |           |
        | *          | *         |                           |           |
        3  - (W=1) - 4 - (W=4) - 5              3  - (W=1) - 4 - (W=4) - 5
         */
        EWG = new EdgeWeightedGraph(6);
        EWG.addEdge(0, 1, 1);
        EWG.addEdge(0, 3, 3);
        EWG.addEdge(1, 2, 6);
        EWG.addEdge(1, 3, 5);
        EWG.addEdge(1, 4, 1);
        EWG.addEdge(2, 4, 5);
        EWG.addEdge(2, 5, 2);
        EWG.addEdge(3, 4, 1);
        EWG.addEdge(4, 5, 4);

        /*
        GeeksForGeeks Example
         */
        EWDG = new EdgeWeightedDigraph(10);
        EWDG.addEdge(0, 1, 4);
        EWDG.addEdge(0, 7, 8);
        EWDG.addEdge(1, 2, 8);
        EWDG.addEdge(1, 7, 11);
        EWDG.addEdge(2, 3, 7);
        EWDG.addEdge(2, 5, 4);
        EWDG.addEdge(2, 8, 2);
        EWDG.addEdge(3, 4, 9);
        EWDG.addEdge(3, 5, 14);
        EWDG.addEdge(5, 4, 10);
        EWDG.addEdge(6, 5, 2);
        EWDG.addEdge(6, 8, 6);
        EWDG.addEdge(7, 6, 1);
        EWDG.addEdge(7, 8, 7);
        /*
        An Edge-Weighted Digraph with a negative cycle
         */
        EWDG2 = new EdgeWeightedDigraph(10);
        EWDG2.addEdge(0, 1, 4);
        EWDG2.addEdge(0, 7, 8);
        EWDG2.addEdge(1, 2, 8);
        EWDG2.addEdge(1, 7, 11);
        EWDG2.addEdge(2, 3, 7);
        EWDG2.addEdge(5, 2, -4);
        EWDG2.addEdge(2, 8, 2);
        EWDG2.addEdge(3, 4, 9);
        EWDG2.addEdge(3, 5, 14);
        EWDG2.addEdge(5, 4, 10);
        EWDG2.addEdge(6, 5, 2);
        EWDG2.addEdge(8, 6, -6);
        EWDG2.addEdge(7, 6, 1);
        EWDG2.addEdge(7, 8, 7);
    }

    @Test
    public void test() throws Exception {
        System.out.println(G);
    }

    @Test
    public void DFSTest() throws Exception {
        DFS.recursive(G);
    }

    @Test
    public void DFSTest2() throws Exception {
        DFS.iterative(G);
    }

    @Test
    public void BFSTest() throws Exception {
        BFS.iterative(G);
    }

    @Test
    public void DFPathsTest() throws Exception {
        DFPaths dfPaths = new DFPaths(G, 0);
        for (int i = 0; i < G.V(); i++) {
            if (dfPaths.hasPathTo(i)) {
                System.out.println(dfPaths.pathTo(i));
            }
        }
    }

    @Test
    public void BFPathsTest() throws Exception {
        BFPaths bfPaths = new BFPaths(G, 0);
        for (int i = 0; i < G.V(); i++) {
            if (bfPaths.hasPathTo(i)) {
                System.out.println(bfPaths.pathTo(i));
            }
        }
    }

    @Test
    public void ConnectedComponentTest() throws Exception {
        ConnectedComponent CC = new ConnectedComponent(G);
        int count = CC.count();
        List<Integer>[] components = (List<Integer>[]) new List[count];
        for (int i = 0; i < count; i++)
            components[i] = new ArrayList<>();
        for (int v : G.vertices()) {
            components[CC.id(v)].add(v);
        }
        for (int i = 0; i < count; i++)
            System.out.println("Component #" + i + ": " + components[i]);
    }

    @Test
    public void BipartiteTestDFS() throws Exception {
        Bipartite bipartite = new Bipartite(NB, true);
        if (!bipartite.isBipartite())
            System.out.println(bipartite.oddCycle());
    }

    @Test
    public void BipartiteTestBFS() throws Exception {
        Bipartite bipartite = new Bipartite(NB, false);
        if (!bipartite.isBipartite())
            System.out.println(bipartite.oddCycle());
    }

    @Test
    public void CycleTest() throws Exception {
        Cycle c = new Cycle(NB);
        if (c.hasCycle())
            System.out.println(c.cycle());
        Graph G1 = new Graph(5);
        G1.addEdge(0, 0);
        c = new Cycle(G1);
        if (c.hasCycle())
            System.out.println(c.cycle());
        Graph G2 = new Graph(5);
        G2.addEdge(2, 3);
        G2.addEdge(3, 2);
        c = new Cycle(G2);
        if (c.hasCycle())
            System.out.println(c.cycle());
    }

    @Test
    public void DigraphCycleTest() throws Exception {
        DirectedCycle directedCycle = new DirectedCycle(DG);
        if (directedCycle.hasCycle())
            System.out.println(directedCycle.cycle());
    }

    @Test
    public void TopologicalTest() throws Exception {
        assert Topological.DfsApproach(DG) == null;
        System.out.println(Topological.DfsApproach(DG2));
        assert Topological.BfsApproach(DG) == null;
        System.out.println(Topological.BfsApproach(DG2));
    }

    @Test
    public void StrongConnectedComponentTest() throws Exception {
        StrongConnectedComponent scc = new StrongConnectedComponent(DG);
        for (Iterable<Integer> integers : scc.components()) {
            System.out.println("----------------");
            System.out.println(integers);
        }
    }

    @Test
    public void MinimumSpanningTreeTest() throws Exception {
        KruskalMST mst = new KruskalMST(EWG);
        assert Double.compare(mst.weight(), 9) == 0;
    }

    @Test
    public void DijkstraSPTest() throws Exception {
        DijkstraSP sp = new DijkstraSP(EWDG, 0);
        System.out.println(sp.pathTo(8) + ", D = " + sp.distanceTo(8));
        System.out.println(sp.reachable(9));
        System.out.println(sp.pathTo(4) + ", D = " + sp.distanceTo(4));
        System.out.println(sp.pathTo(3) + ", D = " + sp.distanceTo(3));
    }

    @Test
    public void BellmanFordSPTest() throws Exception {
        BellmanFordSP sp = new BellmanFordSP(EWDG, 0);
        assert !sp.hasNegativeWeightCycle();
        System.out.println(sp.pathTo(8));
        System.out.println(sp.reachable(9));
        System.out.println(sp.pathTo(4));
        System.out.println(sp.pathTo(3));
        // Test Negative-Weight Cycle
        BellmanFordSP spInvalid = new BellmanFordSP(EWDG2, 0);
        assert spInvalid.hasNegativeWeightCycle();
    }

    @Test
    public void FloydWarshallTest() throws Exception {
        FloydWarshall sp = new FloydWarshall(EWDG);
        assert !sp.hasNegativeWeightCycle();
        System.out.println(sp.path(0, 8));
        System.out.println(sp.path(1,6));
        System.out.println(sp.path(7,4));
        FloydWarshall spInvalid = new FloydWarshall(EWDG2);
        assert spInvalid.hasNegativeWeightCycle();
    }
}
