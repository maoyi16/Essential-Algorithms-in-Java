package string;

import java.util.ArrayList;
import java.util.List;

public class TrieMap<Value> {
    private static final int SIZE = 256;

    private Node root = new Node();

    private static class Node {
        Object val;
        private Node[] next = new Node[SIZE];
    }

    public boolean contains(String key) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null");
        return get(key) != null;
    }

    @SuppressWarnings("unchecked")
    public Value get(String key) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null");
        Node cur = root;
        for (int i = 0; i < key.length() && cur != null; i++)
            cur = cur.next[key.charAt(i)];

        if (cur != null && cur.val != null)
            return (Value) cur.val;
        else
            return null;
    }

    public void put(String key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null");
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            int p = key.charAt(i);
            if (cur.next[p] == null)
                cur.next[p] = new Node();
            cur = cur.next[p];
        }
        cur.val = val;
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
        if (cur.val != null)
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
            if (cur.val != null)
                maxLenFound = len;
            if (len < str.length())
                cur = cur.next[str.charAt(len)];
        }
        return maxLenFound == -1 ? null : str.substring(0, maxLenFound);
    }

    public String longestPrefixOf(String str, int start) {
        if (str == null)
            throw new IllegalArgumentException("str can't be null");
        Node cur = root;
        int maxLenFound = -1;
        for (int len = start; len <= str.length() && cur != null; len++) {
            if (cur.val != null)
                maxLenFound = len;
            if (len < str.length())
                cur = cur.next[str.charAt(len)];
        }
        return maxLenFound == -1 ? null : str.substring(start, maxLenFound);
    }


    public void delete(String key) {
        if (key == null)
            throw new IllegalArgumentException("key can't be null");
        root = delete(root, key, 0);
    }

    private Node delete(Node node, String key, int d) {
        if (node == null)
            return null;
        if (d == key.length()) {
            node.val = null;
        } else {
            char c = key.charAt(d);
            node.next[c] = delete(node.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (node.val != null)
            return node;
        for (int i = 0; i < SIZE; i++) {
            if (node.next[i] != null)
                return node;
        }
        return null;
    }

    public static void main(String[] args) {
        TrieMap<Integer> tm = new TrieMap<>();
        tm.put("abc", 2);
        tm.put("abd", 3);
        assert tm.get("ab") == null;
        assert tm.get("abc") == 2;
        assert tm.contains("abd");
        tm.delete("abc");
        assert tm.get("abc") == null;
    }
}
