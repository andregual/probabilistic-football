import java.util.*;
import java.io.*;

public class CountingBloomFilterTest {

    public static void main(String[] args) {

        /* Begin of CountingBloomFilterTest */
        System.out.println("\n--------------------------");
        System.out.println("Counting Bloom Filter Test");
        System.out.println("--------------------------\n");

        /* Number of random strings */
        int m = 1000;
        /* Strings size */
        int stringSize = 40;
        /* Size of the Counting Bloom Filter */
        int n = 8000;
        /* Number of Hash Functions */
        int k = 3;

        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        /* Initialize Counting Bloom Filter */
        CountingBloomFilter<String> cbf = new CountingBloomFilter<>(n, 0.0000001, k);

        Random rand = new Random();
        List<String> strings = new ArrayList<>();

        System.out.println("Generating 1000 random strings...\n");
        for(int i = 0; i < m; i++) {
            String randString = "";
            for(int j = 0; j < stringSize; j++) {
                randString += Character.toString(alphabet[rand.nextInt(alphabet.length-1)]);
            }
            strings.add(randString);
            cbf.insertElement(randString);
            cbf.insertElement(randString);
        }

        System.out.println("\nCheck if a string is in the Counting Bloom Filter and decrement it value:\n");
        for(String s : strings) {
            if(cbf.isElement(s)) {
                System.out.println("(" + strings.indexOf(s) + ") - " + s + " is probably in the Counting Bloom Filter");
                System.out.println("Count before one deletion: " + cbf.count(s));
                cbf.deleteElement(s);
                System.out.println("Count after one deletion: " + cbf.count(s) + "\n");
            } else {
                System.out.println("(" + strings.indexOf(s) + ") - " + s + " is not in the Counting Bloom Filter");
            }
        }

        System.out.printf("%s is %s in the Counting Bloom Filter\n", "StringTeste", cbf.isElement("StringTeste") ? "probably" : "not");

        /* Same test with 10000 strings */
        System.out.println("\nSame test with 10000 strings:\n");
        m = 10000;

        List<String> strings2 = new ArrayList<>();

        System.out.println("Generating 10000 random strings...");
        for(int i = 0; i < m; i++) {
            String randString = "";
            for(int j = 0; j < stringSize; j++) {
                randString += Character.toString(alphabet[rand.nextInt(alphabet.length-1)]);
            }
            strings2.add(randString);
        }

        System.out.println("\nCheck if a string is in the Counting Bloom Filter and decrement it value:\n");
        for(String s : strings2) {
            if(cbf.isElement(s)) {
                System.out.println(strings2.indexOf(s) + " - " + s + " is probably in the Counting Bloom Filter");
                System.out.println("Count: " + cbf.count(s));
            } else {
                System.out.println(strings2.indexOf(s) + " - " + s + " is not in the Counting Bloom Filter");
            }
        }

        /* End of CountingBloomFilterTest */
        System.out.println("\n---------------------------------");
        System.out.println("End of Counting Bloom Filter Test");
        System.out.println("---------------------------------\n");
    }
}
