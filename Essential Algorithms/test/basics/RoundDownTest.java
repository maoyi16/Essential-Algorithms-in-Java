package basics;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by maoyi16 on 7/11/17.
 */
public class RoundDownTest {
    @Test
    public void test() throws Exception {
        assert 2 == (int) 2.9999;
        assert 0 == (int) -0.05;
        assert -1 == (int) Math.floor(-0.05);
        assert -2 == (int) -2.3;
        PriorityQueue<Integer> pq;
    }
}
