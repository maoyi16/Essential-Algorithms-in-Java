package basics;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueIterationTest {
    @Test
    public void test() throws Exception {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        q.offer(2);
        q.offer(3);
        for (Integer integer : q) {
            System.out.println(integer);
        }
    }
}
