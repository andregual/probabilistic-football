import java.util.Arrays;

public class CountingBloomFilter {
    /* Attributes */
    private static int id = 0;
    private int myID;
    private int capacity;
    private int kHashFunctions;
    private int[] B;
    private boolean empty;

    /* Constructor */
    public CountingBloomFilter(int setSize, double loadFactor) {
        this.myID = id;
        id++;
        this.capacity = (int)(setSize/loadFactor);
        this.B = new int[capacity];
        this.kHashFunctions = (int)((Math.log(2)*capacity)/setSize);
        empty = true;
    }

    /* Methods */
    @Override
    public String toString() {
        return "CountingBloomFilter{" +
                "id=" + myID +
                ", capacity=" + capacity +
                ", kHashFunctions=" + kHashFunctions +
                ", B=" + Arrays.toString(B) +
                '}';
    }

    public void makeEmpty() {
        B = new int[capacity];
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void insertElement(String element) {
        for(int i = 0; i < kHashFunctions; i++) {
            element = element + Integer.toString(i);

            System.out.println(element);
            long hash = this.djb2Algorithm(element);
            System.out.println(hash);
            hash = (hash % capacity) + 1;
            System.out.println(hash);
            this.B[(int) hash]++;
        }
        empty = false;
    }

    public boolean isElement(String element) {
        for(int i = 0; i < kHashFunctions; i++) {
            element = element + Integer.toString(i);
            long hash = (this.djb2Algorithm(element) % capacity) + 1;
            if(this.B[(int) hash] == 0){
                return false;
            }
        }
        return true;
    }

    public void deleteElement(String element) {
        for(int i = 0; i < kHashFunctions; i++) {
            element = element + Integer.toString(i);
            long hash = (this.djb2Algorithm(element) % capacity) + 1;
            if(this.B[(int) hash] > 0){
                this.B[(int)hash]--;
            }
        }
    }

    public int count(String element){
        if(!this.isElement(element)){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < kHashFunctions; i++){
            element = element + Integer.toString(i);
            long hash = (this.djb2Algorithm(element) % capacity) + 1;
            if(this.B[(int)hash] < min){
                min = this.B[(int)hash];
            }
        }
        return min;
    }

    /* djb2 algorithm */
    public long djb2Algorithm(String s) {
        char[] array = s.toCharArray();
        long hash = 5381 & 0xffffffffL;
        for(int i = 0 ; i < array.length ; ++i){
            hash = (1 + (((int)(hash*33) + array[i]) % 2147483647)) & 0xffffffffL;
        }
        return hash;
    }

    /* Getters and Setters */

    public int getMyID() {
        return myID;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getkHashFunctions() {
        return kHashFunctions;
    }
}
