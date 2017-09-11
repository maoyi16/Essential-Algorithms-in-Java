package basics;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class CompareTest {
    @Test
    public void test() throws Exception {
        class Node {
            int v;
            public Node (int v) {
                this.v = v;
            }

            @Override
            public String toString() {
                return String.valueOf(v);
            }
        }
        class ComparableNode extends Node implements Comparable<Node> {

            public ComparableNode(int v) {
                super(v);
            }

            // Natural Ordering
            @Override
            public int compareTo(Node o) {
                return v - o.v;
            }
        }

        Node[] nodes = {new Node(10), new Node(12), new Node(8)};
        Arrays.sort(nodes, new Comparator<Node>() {
            // Reverse Natural Ordering
            @Override
            public int compare(Node o1, Node o2) {
                return o2.v - o1.v;
            }
        });
        System.out.println(Arrays.toString(nodes));
        ComparableNode[] comparableNodes = {new ComparableNode(10), new ComparableNode(12), new ComparableNode(8)};
        Arrays.sort(comparableNodes);
        System.out.println(Arrays.toString(comparableNodes));
    }

    @Test
    public void test2() throws Exception {
        long a = 10;
        int b = 10;
        assert a == b;
    }
}
