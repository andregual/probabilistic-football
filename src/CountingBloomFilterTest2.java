import java.util.*;
import java.io.*;

public class CountingBloomFilterTest2 {

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
        int k;

        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        int data1[] = new int[15];
        int i1 = 0;

        for(k = 1; k <= 15; k++) {
            m = 1000;
            System.out.println("\n------------------------------------------------------------------------------");
            System.out.println("Initialize a Counting Bloom Filter with " + k + " hash functions");
            System.out.println("------------------------------------------------------------------------------");

            /* Initialize Counting Bloom Filter */
            CountingBloomFilter<String> cbf = new CountingBloomFilter<>(n, k);

            Random rand = new Random();
            List<String> strings = new ArrayList<>();

            System.out.println("\n*Generating 1000 random strings and add them to the Countig Bloom Filter...");
            for(int i = 0; i < m; i++) {
                String randString = "";
                for(int j = 0; j < stringSize; j++) {
                    randString += Character.toString(alphabet[rand.nextInt(alphabet.length-1)]);
                }
                strings.add(randString);
                cbf.insertElement(randString);
            }

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

            data1[i1] = count;
            i1++;
        }


        FileWriter fe1 = null;
        PrintWriter pe1 = null;

        try {
            fe1 = new FileWriter("file1.csv");
            pe1 = new PrintWriter(fe1);
        } catch(IOException e) {
            System.out.println("\nErro ao abrir ficheiro!");
        }

        for(int i = 0; i < 15; i++) {
            pe1.write(String.format("%d;%d\n",i+1,data1[i]));
        }

        pe1.close();

        /* End of CountingBloomFilterTest */
        System.out.println("\n---------------------------------");
        System.out.println("\nEnd of Counting Bloom Filter Test");
        System.out.println("\n---------------------------------\n");
    }
}
