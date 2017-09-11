package sorting;

import java.util.Arrays;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class Inversions {
    private static int merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = arr[i];
        // count separately, enabling us to count pairs with other property, e.g. i < j, arr[i] > 2 * arr[j]
        int total = 0;
        for (int i = lo, j = mid + 1; i <= mid; i++) {
            while (j <= hi && arr[i] > arr[j])
                j++;
            total += j - (mid + 1);
        }
        // merge
        for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
            if      (i > mid)           arr[k] = aux[j++];
            else if (j > hi)            arr[k] = aux[i++];
            else if (aux[i] < aux[j])   arr[k] = aux[i++];
            else                        arr[k] = aux[j++];
        }
        return total;
    }
    private static int count(int[] arr, int[] aux, int lo, int hi) {
        if (lo >= hi)
            return 0;
        int mid = (lo + hi) >>> 1;
        int total = 0;
        total += count(arr, aux, lo, mid);
        total += count(arr, aux, mid + 1, hi);
        total += merge(arr, aux, lo, mid, hi);
        return total;
    }
    public static int count(int[] arr) {
        return count(arr, new int[arr.length], 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 1, 3, 2, 5};
        assert 1 == count(arr1);
        int[] arr2 = {0, 1, 3, 2, 5, -1};
        assert 6 == count(arr2);
    }
}
