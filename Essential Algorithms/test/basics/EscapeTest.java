package basics;

import org.junit.Test;

public class EscapeTest {
    @Test
    public void test() throws Exception {
        /* \ + number as a character means the character with octal value of the number
         * e.g.
         * \123 = 1 * 8^2 + 2 * 8 + 3 * 1 = 64 + 16 + 3 = 83
         * \36 = 3 * 8 + 6 = 24 + 6 = 30
         */
        int a = '\1';
        int b = '\36';
        int c = '\123';
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
