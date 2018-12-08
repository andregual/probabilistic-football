import java.util.*;

public class CountingBloomFilter<T> {
    /* Attributes */
    private static int id = 0;
    private int myID;
    private int capacity;
    private double falsePositiveProbability;
    private int kHashFunctions;
    private int[] B;
    private boolean empty;
    private int prime = 2147483647;
    private int[] a;
    private int[] b;

    /* Constructor */
    public CountingBloomFilter(int setSize, double falsePositiveProbability) {
        this.myID = id;
        id++;
        this.falsePositiveProbability = falsePositiveProbability;
        this.capacity = (int)Math.ceil( (setSize * Math.log(falsePositiveProbability)) / Math.log(1 / Math.pow(2, Math.log(2))));
        this.B = new int[capacity];
        this.kHashFunctions = (int)((Math.log(2)*capacity)/setSize);
        empty = true;

        /* Initialize arrays a[] and b[] with random integers from 1 to prime (2147483647) */
        this.a = new int[kHashFunctions];
        this.b = new int[kHashFunctions];
        Random rand = new Random();
        for(int i = 0; i < kHashFunctions; i++)
        {
            a[i] = rand.nextInt(prime-1) + 1;
            b[i] = rand.nextInt(prime-1) + 1;
        }
    }

    public CountingBloomFilter(int setSize, double falsePositiveProbability, int kHashFunctions) {
        this.myID = id;
        id++;
        this.falsePositiveProbability = falsePositiveProbability;
        this.capacity = (int)Math.ceil( (setSize * Math.log(falsePositiveProbability)) / Math.log(1 / Math.pow(2, Math.log(2))));
        this.B = new int[capacity];
        this.kHashFunctions = kHashFunctions;
        empty = true;

        /* Initialize arrays a[] and b[] with random integers from 1 to prime (2147483647) */
        this.a = new int[kHashFunctions];
        this.b = new int[kHashFunctions];
        Random rand = new Random();
        for(int i = 0; i < kHashFunctions; i++)
        {
            a[i] = rand.nextInt(prime-1) + 1;
            b[i] = rand.nextInt(prime-1) + 1;
        }
    }



    /* Methods */
    @Override
    public String toString() {
        return "Counting Bloom Filter\n" +
                "-> ID: " + myID + "\n" +
                "-> Capacity: " + capacity + "\n" +
                "-> False Positive Probability: " + falsePositiveProbability + "\n" +
                "-> Number of Hash Functions: " + kHashFunctions + "\n" +
                "-> Is empty? " + empty + "\n";
    }

    public void makeEmpty() {
        B = new int[capacity];
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void insertElement(T element) {
        long hash = element.hashCode() & 0xffffffffL;
        for(int i = 0; i < kHashFunctions; i++) {
            hash = ((a[i]*hash + b[i]) % prime);
            hash = (hash % capacity);
            this.B[(int) hash]++;
        }
        empty = false;
    }

    public boolean isElement(T element) {
        long hash = element.hashCode() & 0xffffffffL;
        for(int i = 0; i < kHashFunctions; i++) {
            hash = ((a[i]*hash + b[i]) % prime);
            hash = (hash % capacity);
            if(this.B[(int) hash] == 0){
                return false;
            }
        }
        return true;
    }

    public void deleteElement(T element) {
        long hash = element.hashCode() & 0xffffffffL;
        for(int i = 0; i < kHashFunctions; i++) {
            hash = ((a[i]*hash + b[i]) % prime);
            hash = (hash % capacity);
            if(this.B[(int) hash] > 0){
                this.B[(int)hash]--;
            }
        }
    }

    public int count(T element){
        if(!this.isElement(element)){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        long hash = element.hashCode() & 0xffffffffL;
        for(int i = 0; i < kHashFunctions; i++) {
            hash = ((a[i]*hash + b[i]) % prime);
            hash = (hash % capacity);
            if(this.B[(int)hash] < min){
                min = this.B[(int)hash];
            }
        }
        return min;
    }

    /* djb2 algorithm */
    /*public long djb2Algorithm(String s) {
        char[] array = s.toCharArray();
        long hash = 5381 & 0xffffffffL;
        for(int i = 0 ; i < array.length ; ++i){
            hash = (1 + (((int)(hash*33) + array[i]) % 2147483647)) & 0xffffffffL;
        }
        return hash;
    }*/

    /* Getters and Setters */
    public int getMyID() {
        return myID;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getFalsePositiveProbability() {
        return falsePositiveProbability;
    }

    public int getkHashFunctions() {
        return kHashFunctions;
    }
}
