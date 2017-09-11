package basics;

import org.junit.Test;

public class SubStringTest {
    @Test
    public void test() throws Exception {
        String str = "abc";
//        System.out.println(Integer.valueOf(str.substring(0,0)));
        System.out.println(str.substring(2, 3));
    }

    @Test
    public void test2() throws Exception {
        String s = "abcdac", t = "cdac";
        for (int i = 0; i + t.length() <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (j == t.length()) {
                    System.out.println(i);
                    break;
                } else if (s.charAt(i + j) != t.charAt(j))
                    break;
            }
        }
    }
}
