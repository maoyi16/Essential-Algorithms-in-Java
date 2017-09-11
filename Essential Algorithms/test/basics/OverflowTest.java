package basics;

import org.junit.Test;

import java.util.List;

public class OverflowTest {
    @Test
    public void test() throws Exception {

        /*
        NOTE: >>> 1 Operation will not overflow
         */
        System.out.println((Integer.MAX_VALUE + Integer.MAX_VALUE) / 2);
        System.out.println((Integer.MAX_VALUE + 10) >>> 1);
        List<Integer> s;

    }
}
