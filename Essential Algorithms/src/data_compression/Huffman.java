package data_compression;

import java.util.BitSet;
import java.util.PriorityQueue;

public class Huffman {
    private static final int R = 256;

    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node n) {
            return freq - n.freq;
        }
    }

    private static Node root;
    private static String[] codeTable;

    public static void setFreq(String str) {
        char[] arr = str.toCharArray();
        int[] freq = new int[R];
        for (int i = 0; i < arr.length; i++)
            freq[arr[i]]++;
        root = buildTrie(freq);
        codeTable = new String[R];
        buildCode(root, "");
    }

    public static void setFreq(int[] freq) {
        assert freq.length == R;
        root = buildTrie(freq);
        codeTable = new String[R];
        buildCode(root, "");
    }

    private static Node buildTrie(int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.offer(new Node(i, freq[i], null, null));

        // In case the string consists of a single character
        if (pq.size() == 1)
            if (freq['\0'] == 0)
                pq.offer(new Node('\0', 0, null, null));
            else
                pq.offer(new Node('\1', 0, null, null));

        // Choose two nodes with the least frequencies in every iteration
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.offer(parent);
        }
        return pq.poll();
    }

    private static void buildCode(Node node, String str) {
        if (node.isLeaf())
            codeTable[node.ch] = str;
        else {
            buildCode(node.left, str + '0');
            buildCode(node.right, str + '1');
        }
    }

    public static byte[] compress(String str) {
        assert codeTable != null;
        final int L = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < L; i++) {
            sb.append(codeTable[str.charAt(i)]);
        }

        BitSet result = new BitSet(sb.length());
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '1')
                result.set(i);
        }

        // NOTE: set this extra bit to mark the end of actual data
        result.set(sb.length());

        return result.toByteArray();
    }

    public static String decompress(byte[] arr) {
        assert root != null;
        BitSet bits = BitSet.valueOf(arr);
        StringBuilder sb = new StringBuilder();
        Node cur = root;
        // NOTE: do not proceed to the last set bit, as it just marks the end of data
        for (int i = 0; i < bits.length() - 1; sb.append(cur.ch), cur = root) {
            while (!cur.isLeaf()) {
                if (bits.get(i))
                    cur = cur.right;
                else
                   cur = cur.left;
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "ABAABBBBBBBATTTTTTTTTTTTAAATTTTABB";
        setFreq(str);
        System.out.println(decompress(compress(str)));
    }
}
