import java.util.*;

public class MinHash {
    /* Attributes */
    private static int id = 0;
    private int myID;
    private int numElements;
    private int kHashFunctions;
    private HashMap<String, Set<Integer>> clubs;
    private String[] clubsName;
    private double threshold;
    private int col;
    private int[][] minHashMatrix;
    private int prime = 2147483647;
    private int[] a;
    private int[] b;

    /* Constructor */
    public MinHash(HashMap<String, Set<Integer>> clubs, int kHashFunctions) {
        this.myID = id;
        id++;
        this.clubs = clubs;
        this.numElements = clubs.size();
        this.kHashFunctions = kHashFunctions;
        this.minHashMatrix = new int[kHashFunctions][numElements];
        int i = 0;
        clubsName = new String[numElements];
        for(String club : clubs.keySet()) {
            clubsName[i] = club;
            i++;
        }
        /* Initialize arrays a[] and b[] with random integers from 1 to prime (2147483647) */
        this.a = new int[kHashFunctions];
        this.b = new int[kHashFunctions];
        Random rand = new Random();
        for(i = 0; i < kHashFunctions; i++)
        {
            a[i] = rand.nextInt(prime-1) + 1;
            b[i] = rand.nextInt(prime-1) + 1;
        }
    }

    public void computeSignature(int[] overalls) {
        /* Compute de minHash to insert on the signatures matrix */
        for(int i = 0; i < kHashFunctions; i++) {
            int min = hashFunction(overalls[0],i);
            for(int j = 1; j < overalls.length; j++) {
                int temp = hashFunction(overalls[j],i);
                if(temp < min) {
                    min = temp;
                }
            }
            minHashMatrix[i][col] = min;
        }
        col++;
    }

    public void findSimiliarPairs(double threshold) {
        this.threshold = threshold;
        for(int i = 0; i < numElements; i++) {
            for(int j = i+1; j < numElements; j++) {
                int sum = 0;
                for(int k = 0; k < kHashFunctions; k++) {
                    if(minHashMatrix[k][i] == minHashMatrix[k][j]) {
                        sum++;
                    }
                }
                double distance = 1 - ((double)(sum) / (double)(kHashFunctions));
                if(distance <= threshold && distance != 0) {
                    System.out.printf("The elements (" + clubsName[i] + ", " + clubsName[j] + ") are a similiar pair. Distance: %.2f\n",distance);
                }
            }
        }
    }

    public int hashFunction(int attribute, int i) {
        long hash = attribute & 0xffffffffL;
        return (int)((a[i]*hash + b[i]) % prime);
    }

    /* Getters and Setters */
    public int[][] getMinHashMatrix() {
        return minHashMatrix;
    }
}
