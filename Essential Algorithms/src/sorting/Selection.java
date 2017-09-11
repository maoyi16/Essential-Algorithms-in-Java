package sorting;

import java.util.Arrays;

public class Selection {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j])
                    minIdx = j;
            }
            swap(arr, i, minIdx);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 4, 1, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
