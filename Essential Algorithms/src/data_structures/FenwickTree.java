package data_structures;

/**
 * Created by maoyi16 on 7/10/17.
 */

/**
 * Basic idea: any number can be expressed as a sum of 2's power
 * e.g. 15 = 2^3 + 2^2 + 2^1 + 2^ 0 = 8 + 4 + 2 + 1
 * sum(1, 15) = sum(1, 8) + sum(9, 12) + sum(13, 14) + sum(15,15)
 * sum(1, 15) = arr[8] + arr[12] + arr[14] + arr[15]
 * sum(1, 15) = arr[0b1000] + arr[0b1100] + arr[0b1110] + arr[0b1111] (in binary)
 * OBSERVATION: 12 - 8 = 4, 14 - 12 = 2, 15 - 14 = 1
 * Conversion in update: add the last set bit
 * Conversion in sum: removing the last set bit
 */
public class FenwickTree {

    int[] arr;

    public FenwickTree(int n) {
        arr = new int[n + 1]; // size = N + 1, for easy implementation
    }

    public void update(int idx, int delta) {
        idx = idx + 1; // convert to 1-based index
        while (idx < arr.length) {
            arr[idx] += delta;
            idx += idx & (-idx); // idx & (-idx) = the last set bit of idx
        }
    }

    public void set(int idx, int newValue) {
        int oldValue = sum(idx) - sum(idx - 1);
        update(idx, newValue - oldValue);
    }

    public int sum(int idx) {
        idx = idx + 1;
        int res = 0;
        while (idx > 0) {
            res += arr[idx];
            idx -= idx & (-idx);
        }
        return res;
    }

    public int rangeSum(int i, int j) {
        return sum(j) - sum(i - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12, 13, 14};
        FenwickTree t = new FenwickTree(arr.length);
        for (int i = 0; i < arr.length; i++)
            t.set(i, arr[i]);
        assert t.rangeSum(1, 4) == 2 + 3 + 4 + 5;
        assert t.rangeSum(8, 13) == 9 + 10 + 11 + 12 + 13 + 14;
    }
}
