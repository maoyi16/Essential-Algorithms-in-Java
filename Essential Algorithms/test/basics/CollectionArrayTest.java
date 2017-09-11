package basics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maoyi16 on 7/12/17.
 */
public class CollectionArrayTest {
    @Test
    public void test() throws Exception {
        List[] arr = new List[10];
        arr[0] = new ArrayList();
        arr[0].add(10);
        arr[0].add("adfadf");
        System.out.println(Arrays.toString(arr));
    }
}
