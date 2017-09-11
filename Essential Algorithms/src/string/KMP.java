package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Efficient search for short pattern
 */
public class KMP {
    private static int[] preprocess(char[] arr) {
        int[] next = new int[arr.length];
        next[0] = -1;
        for (int i = 1; i < arr.length; i++) {
            int j = next[i - 1];
            while (j != -1 && arr[j + 1] != arr[i])
                j = next[j];
            next[i] = arr[j + 1] == arr[i] ? j + 1 : -1;
        }
        return next;
    }

    public static Iterable<Integer> search(String pattern, String text) {
        char[] arr = pattern.toCharArray();
        int[] next = preprocess(arr);
        List<Integer> result = new ArrayList<>();
        for (int i = 0, j = -1; i < text.length(); i++) {
            char c = text.charAt(i);
            while (j!= -1 && arr[j + 1] != c)
                j = next[j];
            j = arr[j + 1] == c ? j + 1 : -1;
            if (j + 1 == arr.length) {
                result.add(i - arr.length + 1);
                j = next[j];  // NOTE: look for next match (j = next[j]) instead of restarting at the beginning (j = -1)
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "abcdesabce";
        String pattern = "abc";
        System.out.println(search(pattern, text));
    }
}
