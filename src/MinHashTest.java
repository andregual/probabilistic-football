import java.util.*;
import java.io.*;

public class MinHashTest {

    public static void main(String[] args) {

        /* Begin of MinHashTest */
        System.out.println("\n------------");
        System.out.println("MinHash Test");
        System.out.println("------------\n");

        /* Initialize a BufferedReader */
        BufferedReader br = null;

        /* Try to open "u.data" file */
        try {
            br = new BufferedReader(new FileReader("u.data"));
        } catch (FileNotFoundException e){
            System.out.println("\nError opening the file!");
            System.exit(1);
        }

        /* Initialize HashMap */
        HashMap<String, Set<Integer>> users = new HashMap<>();

        try {
            /* Read first line */
            String line = br.readLine();

            /* Read all lines */
            while (line != null) {
                /* Split the line by columns */
                String[] lineArray = line.split("\t");

                if(!users.containsKey(lineArray[0])) {
                    Set<Integer> movies = new HashSet<>();
                    movies.add(Integer.parseInt(lineArray[1]));
                    users.put(lineArray[0],movies);
                } else {
                    Set<Integer> movies = users.get(lineArray[0]);
                    movies.add(Integer.parseInt(lineArray[1]));
                    users.put(lineArray[0],movies);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("\nError reading the file.");
        }

        /* Initialize a minHash object */
        MinHash minHash = new MinHash(users, 100);


        for(String user : users.keySet()) {
            Set<Integer> userMovies = users.get(user);
            int i = 0;
            int[] movies = new int[userMovies.size()];
            for(Integer movie : userMovies) {
                movies[i] = (int)(movie);
                i++;
            }
            /* ComputeSignature columns for every user */
            minHash.computeSignature(movies);
        }

        /* Finde similiar pairs */
        minHash.findSimiliarPairs(0.4);

        /* End of MinHashTest */
        System.out.println("\n-------------------");
        System.out.println("End of MinHash Test");
        System.out.println("-------------------\n");
    }

}
