package basics;

import org.junit.Test;

public class DoubleCompareTest {
    @Test
    public void test() throws Exception {
        System.out.println(Double.compare(0, 1.2));
        System.out.println(Double.compare(0,0));
        System.out.println(Double.compare(0.1, 0.01));
    }
}
