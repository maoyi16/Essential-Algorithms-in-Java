package mathematics;

import org.junit.Test;

public class GCD {
    static int gcd(int a, int b) {
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static void main(String[] args) {
        assert 3 == gcd(51, 9);
        assert 2 == gcd(2, 10);
    }
}
