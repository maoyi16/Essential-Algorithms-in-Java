package basics;

import org.junit.Test;

public class DoubleOperationTest {
    @Test
    public void additionTest() throws Exception {
        System.out.println(Double.POSITIVE_INFINITY + Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY + Double.NEGATIVE_INFINITY);
        System.out.println(Double.POSITIVE_INFINITY + 1);
        System.out.println(Double.compare(Double.POSITIVE_INFINITY + 1, Double.POSITIVE_INFINITY + 2));
    }

    @Test
    public void divisionTest() throws Exception {
        System.out.println(10.0/0.0);
        System.out.println(-10/0.0);
        System.out.println(10/0);
    }
}
