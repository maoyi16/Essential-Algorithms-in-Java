package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class MergeSort {

    public static void sort(int[] arr) {
        sort(arr, new int[arr.length], 0, arr.length - 1);
    }
    private static void sort(int[] arr, int[] aux, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = (lo + hi) >>> 1;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }
    private static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = arr[i];

        for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
            if (i > mid)                arr[k] = aux[j++];
            else if (j > hi)           arr[k] = aux[i++];
            else if (aux[i] < aux[j])   arr[k] = aux[i++];
            else                        arr[k] = aux[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, -5, 10, 1, 6};
        MergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
