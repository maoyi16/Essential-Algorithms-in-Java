package data_compression;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileGenerator {
    @Test
    public void run() throws Exception {
        // Generate a text with 100000 lines
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("test_resources/Text/shakespeare.txt"));
        for (int i = 0; i < 100000; i++) {
            sb.append(br.readLine());
        }
        br.close();
        String str = sb.toString();
        BufferedWriter bw1 = new BufferedWriter(new FileWriter("test_resources/Text/original.txt"));
        bw1.write(str, 0, str.length());
        bw1.close();
    }
}
