package data_compression;

public class RunLength {
    // NOTE: Limited ability to compress text. Besides, it can only compress text with no numbers.
    public static String compress(String str) {
        StringBuilder sb = new StringBuilder();
        final int L = str.length();
        for (int i = 0; i < L; ) {
            char ch = str.charAt(i);
            sb.append(ch);
            int cnt = 1;
            while (++i < L && str.charAt(i) == ch)
                cnt++;
            if (cnt > 1)
                sb.append(cnt);
        }
        return sb.toString();
    }

    public static String decompress(String str) {
        StringBuilder sb = new StringBuilder();
        final int L = str.length();
        for (int i = 0, nextI; i < L; i = nextI) {
            char ch = str.charAt(i);
            nextI = i + 1;
            while (nextI < L && Character.isDigit(str.charAt(nextI)))
                nextI++;
            int cnt = i + 1 == nextI ? 1 : Integer.valueOf(str.substring(i + 1, nextI));
            for (int j = 0; j < cnt; j++)
                sb.append(ch);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String str = "ABBCCCDD";
        String compressed = compress(str);
        System.out.println(compressed);
        System.out.println(decompress(compress(str)));
    }
}
