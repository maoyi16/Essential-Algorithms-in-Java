package sorting;

import java.util.Arrays;

public class Insertion {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j - 1, j);
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 4, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
