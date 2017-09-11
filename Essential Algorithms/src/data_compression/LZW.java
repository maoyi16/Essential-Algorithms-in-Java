package data_compression;

import string.TrieMap;

import java.util.BitSet;

public class LZW {
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width

    public static byte[] compress(String str) {
        TrieMap<Integer> trieMap = new TrieMap<>();
        for (int i = 0; i < R; i++)
            trieMap.put(""+(char)i, i);

        int nextAvailableCode = R + 1; // R is the code for EOF

        BitSet bitSet = new BitSet(); // Use bitSet to deal with 12-bit variable
        int i, start, LEN = str.length();
        for (i = 0, start = 0; start < LEN; i += W) {
            String prefix = trieMap.longestPrefixOf(str, start);
            int codeword = trieMap.get(prefix); // codeword of the longest known word
            // Convert it to 12 bits. [ == Mark the corresponding 12 bits of the codeword in bitSet]
            for (int j = i + W - 1, k = 0; j >= i; j--, k++) {
                if ((codeword & (1 << k)) > 0)
                    bitSet.set(j);
            }
            int nextStart = start + prefix.length();
            if (nextStart < LEN && nextAvailableCode < L)
                // add the next unknown word [current known + a new char] to table
                trieMap.put(str.substring(start, nextStart + 1), nextAvailableCode++);
            start = nextStart;
        }
        // mark the end of the bitSet
        bitSet.set(i);

        return bitSet.toByteArray();
    }

    public static String decompress(byte[] arr) {
        String[] map = new String[L];

        for (int i = 0; i < R; i++)
            map[i] = "" + (char)i;


        int nextAvailableCode = R; // Next available code in map, set one step ahead
        BitSet bitSet = BitSet.valueOf(arr);
        StringBuilder sb = new StringBuilder();
        String prev = "";

        for (int i = 0; i < bitSet.length() - 1; i += W) {
            int nextCode = 0;
            // Get the nextCode from bitSet
            for (int j = i + W - 1, k = 0; j >= i; j--, k++) {
                if (bitSet.get(j))
                    nextCode |= 1 << k;
            }
            String nextWord = map[nextCode];
            if (nextCode == nextAvailableCode) // The special case
                nextWord = prev + prev.charAt(0);
            if (nextAvailableCode < L)
                // map[R] will be the first symbol in the input, which is wrong, but we don't actually care
                map[nextAvailableCode++] = prev + nextWord.charAt(0);
            sb.append(nextWord);
            prev = nextWord;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "ABCABCABCDEadfeoODIUFODHABC/opio23j54l2;9843ythjk a;dlfj   adfjkl . \nadfad";
        System.out.println(decompress(compress(str)));
    }

}
