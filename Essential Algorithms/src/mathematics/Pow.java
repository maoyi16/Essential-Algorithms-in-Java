package mathematics;

import org.junit.Test;

import java.math.*;

public class Pow {

    static double recursivePow(double a, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return a;
        double t = recursivePow(a, n / 2);
        return t * t * recursivePow(a, n % 2);
    }

    static double iterativePow(double a, int n) {
        double ret = 1;
        while (n > 0) {
            if (n % 2 == 1)
                ret *= a;
            a *= a;
            n /= 2;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(recursivePow(1, 10));
        System.out.println(recursivePow(3, 3));
        System.out.println(iterativePow(1, 10));
        System.out.println(iterativePow(3, 10));
    }
}
