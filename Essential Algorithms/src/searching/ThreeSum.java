package searching;

import java.util.Arrays;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class ThreeSum {
    public static int count(int[] arr, int target) {
        int res = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int j = i + 1, k = arr.length - 1;
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum > target)
                    k--;
                else if (sum < target)
                    j++;
                else {
                    res++;
                    j++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 1, 3, 5, 4, 6};
        assert count(arr, 6) == 3;
    }
}
