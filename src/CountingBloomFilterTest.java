import java.util.*;
import java.io.*;

public class CountingBloomFilterTest {

    public static void main(String[] args) {

        /* Begin of CountingBloomFilterTest */
        System.out.println("\n--------------------------");
        System.out.println("\nCounting Bloom Filter Test");
        System.out.println("\n--------------------------\n");

        /* Number of random strings */
        int m = 1000;
        /* Strings size */
        int stringSize = 40;
        /* Size of the Counting Bloom Filter */
        int n = 8000;
        /* Number of Hash Functions */
        int k = 3;

        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        for(int t = 0; t < 2; t++){
            System.out.println("\n------------------------------------------------------------------------------");
            if(t == 0) {
                System.out.println("Initialize a Counting Bloom Filter with " + k + " hash functions");
            } else {
                System.out.println("Initialize a Counting Bloom Filter with " + 0.0000001 + " false positive probability");
            }
            System.out.println("------------------------------------------------------------------------------");
            m = 1000;
            /* Initialize Counting Bloom Filter */
            CountingBloomFilter<String> cbf;
            if(t == 0) {
                cbf = new CountingBloomFilter<>(n, k);
            } else {
                cbf = new CountingBloomFilter<>(n, 0.0000001);
            }

            Random rand = new Random();
            List<String> strings = new ArrayList<>();

            System.out.println("\n*Generating 1000 random strings...");
            for(int i = 0; i < m; i++) {
                String randString = "";
                for(int j = 0; j < stringSize; j++) {
                    randString += Character.toString(alphabet[rand.nextInt(alphabet.length-1)]);
                }
                strings.add(randString);
                cbf.insertElement(randString);
                cbf.insertElement(randString);
            }

            System.out.println("\n-> Check if a string is in the Counting Bloom Filter and decrement it value:");
            for(String s : strings) {
                if(cbf.isElement(s)) {
                    System.out.println("\n(" + strings.indexOf(s) + ") - " + s + " is probably in the Counting Bloom Filter");
                    System.out.println("Count before one deletion: " + cbf.count(s));
                    cbf.deleteElement(s);
                    System.out.println("Count after one deletion: " + cbf.count(s) + "\n");
                } else {
                    System.out.println("\n(" + strings.indexOf(s) + ") - " + s + " is not in the Counting Bloom Filter");
                }
            }

            System.out.printf("\n%s is %s in the Counting Bloom Filter", "StringTeste", cbf.isElement("StringTeste") ? "probably" : "not");

            /* Same test with 10000 strings */
            System.out.println("\n\n-> Same test with 10000 strings:");
            m = 10000;

            List<String> strings2 = new ArrayList<>();

            System.out.println("\n*Generating 10000 random strings...");
            for(int i = 0; i < m; i++) {
                String randString = "";
                for(int j = 0; j < stringSize; j++) {
                    randString += Character.toString(alphabet[rand.nextInt(alphabet.length-1)]);
                }
                strings2.add(randString);
            }

            int count = 0;
            System.out.println("\n*Checking if the " + m + " random strings are in the Counting Bloom Filter...");
            for(String s : strings2) {
                if(cbf.isElement(s)) {
                    count++;
                }
            }

            System.out.println("\n-> There are " + count + " random strings that seems to be in the Counting Bloom Filter. (False Positives = " + count + ")\n");
        }

        /* End of CountingBloomFilterTest */
        System.out.println("\n---------------------------------");
        System.out.println("\nEnd of Counting Bloom Filter Test");
        System.out.println("\n---------------------------------\n");
    }
}
