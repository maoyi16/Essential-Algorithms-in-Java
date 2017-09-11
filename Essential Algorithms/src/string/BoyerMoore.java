package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Efficient search for a long pattern
 */
public class BoyerMoore {
    private static int[] preprocess(String pattern) {
        int[] right = new int[256];
        Arrays.fill(right, -1);
        for (int i = 0; i < pattern.length(); i++)
            right[pattern.charAt(i)] = i;
        return right;
    }

    public static Iterable<Integer> search(String pattern, String text) {
        int[] right = preprocess(pattern);
        final int M = pattern.length(), N = text.length();
        List<Integer> result = new ArrayList<>();
        for (int i = 0, skip; i + M <= N; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    skip = Math.max(1, j - right[text.charAt(i + j)]); // Handle the case when there is no rightmost text.charAt(i + j) in pattern
                    break;
                }
            }
            if (skip == 0) {
                result.add(i);
                skip = Math.max(1, M - 1 - right[pattern.charAt(M - 1)]); // Shift to next possible case
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String pattern = "addddbbbd";
        String text = "adbdxxbaddddbbbddaeaddddbbbdadbd";
        System.out.println(search(pattern, text));
    }
}
