package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class Trie {
    private static final int SIZE = 256;

    private Node root = new Node();

    private class Node {
        boolean isString;
        private Node[] next = new Node[SIZE];
    }

    public boolean contains(String key) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null");
        Node cur = root;
        for (int i = 0; i < key.length() && cur != null; i++) {
            cur = cur.next[key.charAt(i)];
        }
        return cur != null && cur.isString;
    }

    public void add(String key) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null");
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            int p = key.charAt(i);
            if (cur.next[p] == null)
                cur.next[p] = new Node();
            cur = cur.next[p];
        }
        cur.isString = true;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("Prefix can't be null");
        Node cur = root;
        for (int i = 0; i < prefix.length() && cur != null; i++) {
            cur = cur.next[prefix.charAt(i)];
        }
        List<String> result = new ArrayList<>();
        aux(cur, new StringBuilder(prefix), result);
        return result;
    }

    private void aux(Node cur, StringBuilder str, List<String> result) {
        if (cur == null)
            return;
        if (cur.isString)
            result.add(str.toString());
        for (char i = 0; i < cur.next.length; i++) {
            aux(cur.next[i], str.append(i), result);
            str.deleteCharAt(str.length() - 1);
        }
    }

    public String longestPrefixOf(String str) {
        if (str == null)
            throw new IllegalArgumentException("str can't be null");
        Node cur = root;
        int maxLenFound = -1;
        for (int len = 0; len <= str.length() && cur != null; len++) {
            if (cur.isString)
                maxLenFound = len;
            if (len < str.length())
                cur = cur.next[str.charAt(len)];
        }
        return maxLenFound == -1 ? null : str.substring(0, maxLenFound);
    }

    // TODO: Currently wrong
    public void delete(String key) {
        if (key == null)
            throw new IllegalArgumentException("key can't be null");
        root = delete(root, key, 0);
    }

    private Node delete(Node node, String key, int d) {
        if (node == null)
            return null;
        if (d == key.length()) {
            node.isString = false;
        } else {
            char c = key.charAt(d);
            node.next[c] = delete(node.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (node.isString)
            return node;
        for (int i = 0; i < SIZE; i++) {
            if (node.next[i] != null)
                return node;
        }
        return null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("");
        trie.add("adxB34xd");
        trie.add("adfd");
        assert trie.contains("");
        assert trie.contains("adfd");
        assert !trie.contains("ad");
        assert !trie.contains("a");
        trie.add("b42t");
        assert trie.contains("b42t");
        trie.add("adbbR");
        trie.add("adbbRJ");
        System.out.println(trie.keysWithPrefix("ad"));
        System.out.println(trie.keysWithPrefix("r"));
        System.out.println(trie.longestPrefixOf("adbbRJT"));
        trie.delete("adbbRJ");
        System.out.println(trie.longestPrefixOf("adbbRJT"));
    }
}
