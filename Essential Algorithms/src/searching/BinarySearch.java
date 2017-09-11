package searching;

import java.util.Arrays;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class BinarySearch {
    public static int indexOf(int[] arr, int key) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (key < arr[mid])
                hi = mid - 1;
            else if (key > arr[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -(lo + 1); // the index at where the key should be inserted = the first index at where the number is greater than the key
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7};
        assert indexOf(arr, -1) == -(0 + 1);
        assert indexOf(arr, 3) == -(2 + 1);
        assert indexOf(arr, 5) == -(3 + 1);
        assert indexOf(arr, 10) == -(4 + 1);
        Arrays.binarySearch(arr, 0);
    }
}
