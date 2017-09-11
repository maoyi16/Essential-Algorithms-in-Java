package data_compression;

import org.junit.Test;

import java.io.*;

public class RunLengthTest {

    @Test
    public void test() throws Exception {
        // Read the original text from the file
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("test_resources/Text/original.txt"));
        String cur;
        while ((cur = br.readLine()) != null)
            sb.append(cur);
        String str = sb.toString();
        br.close();
        // Write the compressed text to a file
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("test_resources/RunLength/compressed.txt"));
        String compressed = RunLength.compress(str);
        bw2.write(compressed);
        bw2.close();
        // Decompress the compressed text and write it to a file
        String decompressed = RunLength.decompress(compressed);
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("test_resources/RunLength/decompressed.txt"));
        bw3.write(decompressed, 0, decompressed.length());
        bw3.close();
    }
}
