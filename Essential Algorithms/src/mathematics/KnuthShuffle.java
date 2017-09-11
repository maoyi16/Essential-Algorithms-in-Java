package mathematics;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class KnuthShuffle {
    private static Random gen = new Random();
    public static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = gen.nextInt(i + 1);
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        KnuthShuffle.shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
}
