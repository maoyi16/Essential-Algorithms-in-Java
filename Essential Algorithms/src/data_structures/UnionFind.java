package data_structures;

import org.junit.Test;

/**
 * Created by maoyi16 on 7/10/17.
 */
public class UnionFind {

    private int count;
    private int[] rank;
    private int[] parent;

    public UnionFind(int n) {
        count = n;
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Find root
    public int find(int p) {
        if (parent[p] == p)
            return p;
        parent[p] = find(parent[p]);
        return parent[p];
    }

    // Return true if union successfully
    public boolean union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return false;

        if (rank[rootP] > rank[rootQ])
            parent[rootQ] = rootP;
        else if (rank[rootP] < rank[rootQ])
            parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }

        count--;
        return true;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        UnionFind uf1 = new UnionFind(10);
        uf1.union(2, 3);
        uf1.union(3, 5);
        assert uf1.count() == 8;
        UnionFind uf2 = new UnionFind(20);
        assert uf2.union(2, 3);
        assert uf2.union(3, 5);
        assert !uf2.union(2, 5);
        assert uf2.count() == 18;
    }
}
