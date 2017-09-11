package data_compression;

import org.junit.Test;

import java.io.*;

public class HuffmanTest {
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
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("test_resources/Huffman/compressed.txt"));
        Huffman.setFreq(str);
        byte[] compressed = Huffman.compress(str);
        dos.write(compressed);
        dos.close();
        // Decompress the compressed text and write it to a file
        String decompressed = Huffman.decompress(compressed);
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("test_resources/Huffman/decompressed.txt"));
        bw2.write(decompressed, 0, decompressed.length());
        bw2.close();
    }
}
