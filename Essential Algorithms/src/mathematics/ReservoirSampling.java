package mathematics;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by maoyi16 on 7/11/17.
 * Efficient uniform sampling when the count of total candidates is unknown
 */
public class ReservoirSampling {

    private static Random gen = new Random();

    public static int sampling(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (gen.nextInt(i + 1) == 0)
                res = arr[i];
        }
        return res;
    }

    public static int[] sampling(int[] arr, int k) {
        int[] res = Arrays.copyOf(arr, k);
        for (int i = k; i < arr.length; i++) {
            int j = gen.nextInt(i + 1);
            if (j < k)
                res[j] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(sampling(arr, 3)));
        System.out.println(sampling(arr));
    }
}
