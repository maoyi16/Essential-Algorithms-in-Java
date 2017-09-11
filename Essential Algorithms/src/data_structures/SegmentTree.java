package data_structures;

/**
 * Created by maoyi16 on 7/10/17.
 * A data structure optimized for range min/max
 */
public class SegmentTree {
    private class Node {
        int from, to;
        int min;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
    Node[] heap;

    public SegmentTree(int[] arr) {
        int size = (int) (2 * Math.pow(2, (int)(Math.log(arr.length) / Math.log(2)) + 1)); // max size of heap
        heap = new Node[size];
        construct(arr, 0, 0, arr.length - 1);
    }

    private void construct(int[] arr, int idx, int from, int to) {
        heap[idx] = new Node(from, to);
        if (from == to) {
            heap[idx].min = arr[from];
            return;
        }
        int leftIdx = idx * 2 + 1, rightIdx = idx * 2 + 2;
        int mid = (from + to) / 2;
        construct(arr, leftIdx, from, mid);
        construct(arr, rightIdx, mid + 1, to);
        heap[idx].min = Math.min(heap[leftIdx].min, heap[rightIdx].min);
    }

    private int rangeMinAux(int idx, int from, int to) {
        // the current range lies inside the target range, therefore its min is a valid candidate for the target min
        if (from <= heap[idx].from && heap[idx].to <= to)
            return heap[idx].min;
        // the current range lies outside the target range, therefore its min is not a valid candidate for the target min
        if (heap[idx].to < from || to < heap[idx].from)
            return Integer.MAX_VALUE;
        // the target min can be in either
        return Math.min(rangeMinAux(idx * 2 + 1, from, to), rangeMinAux(idx * 2 + 2, from, to));
    }
    public int rangeMin(int from, int to) {
        return rangeMinAux(0, from, to);
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 2, 6, 7, 8, 9};
        SegmentTree st = new SegmentTree(arr);
        assert 1 == st.rangeMin(1, 4);
        assert 2 == st.rangeMin(2, 9);
        assert 2 == st.rangeMin(3, 7);
    }
}
