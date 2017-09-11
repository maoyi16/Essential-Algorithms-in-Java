package sorting;

import mathematics.KnuthShuffle;

import java.util.Arrays;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class QuickSort {
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (i < hi && arr[++i] < pivot);
            while (j > lo && arr[--j] > pivot);
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }
    private static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(arr, lo, hi);
        sort(arr, lo, p - 1);
        sort(arr, p + 1, hi);
    }
    public static void sort(int[] arr) {
        KnuthShuffle.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }
    // Quick Select
    public static int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length)
            return -1;
        KnuthShuffle.shuffle(arr);
        int lo = 0, hi = arr.length - 1;
        while (true) {
            int p = partition(arr, lo, hi);
            if      (p < k) lo = p + 1;
            else if (k < p) hi = p - 1;
            else return arr[p];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 4, -5, 10, 4, 1, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        assert 4 == select(arr, 3);
    }
}
