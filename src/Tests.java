import java.util.*;
import java.io.*;

public class Tests {

    /* Main function */
    public static void main(String[] args) {

        /* Get the Players Complete Dataset */
        List<Player> playerList = getPlayers();

        /* StochasticCounterTest call */
        StochasticCounterTest(playerList);

        /* CountingBloomFilterTest call */
        CountingBloomFilterTest(playerList);

        System.exit(0);

    }

    public static void StochasticCounterTest(List<Player> playerList) {

        /* Begin of StochasticCounterTest */
        System.out.println("\n-----------------------");
        System.out.println("Stochastic Counter Test");
        System.out.println("-----------------------\n");

        /* Creation of some StochasticCounters with differentes probabilities */
        StochasticCounter sc1 = new StochasticCounter(0.1);
        StochasticCounter sc2 = new StochasticCounter(0.2);
        StochasticCounter sc3 = new StochasticCounter(0.5);
        StochasticCounter sc4 = new StochasticCounter(0.8);
        StochasticCounter sc5 = new StochasticCounter(0.9);

        /* Call the incrementStochasticCounter */
        for(Player p : playerList) {
            sc1.incrementStochasticCounter();
            sc2.incrementStochasticCounter();
            sc3.incrementStochasticCounter();
            sc4.incrementStochasticCounter();
            sc5.incrementStochasticCounter();
        }

        /* Print StochasticCounter info */
        System.out.println(sc1);
        System.out.println(sc2);
        System.out.println(sc3);
        System.out.println(sc4);
        System.out.println(sc5);

        /* End of StochasticCounterTest */
        System.out.println("\n------------------------------");
        System.out.println("End of Stochastic Counter Test");
        System.out.println("------------------------------\n\n");
    }

    public static void CountingBloomFilterTest(List<Player> playerList) {

        /* Begin of CountingBloomFilterTest */
        System.out.println("\n--------------------------");
        System.out.println("Counting Bloom Filter Test");
        System.out.println("--------------------------\n");

        Set<String> countries = new HashSet<>();
        for(Player p : playerList){
            countries.add(p.getCountry());
        }

        /* Creation of one CountingBloomFilter */
        CountingBloomFilter cbf1 = new CountingBloomFilter(countries.size(), 0.0000001);

        /* Print CountingBloomFilter info */
        System.out.println(cbf1);

        /* Insertion of countries in the CountingBloomFilter */
        System.out.println("*Insertion of countries in the Counting Bloom Filter... \n");
        for (Player p : playerList) {
            cbf1.insertElement(p.getCountry());
        }

        /* Check if CountingBloomFilter is empty after insertion */
        System.out.println("Is empty after insertion? " + cbf1.isEmpty() + "\n");

        /* Check if a country is a possible member of CountingBloomFilter */
        System.out.println("*Countries check:\n");
        countries.add("PaisInexistente");
        countries.add("PaisInexistente2");
        countries.add("PaisInexistente3");
        for (String country : countries) {
            if(cbf1.isElement(country)) {
                System.out.println(country + " is probably in the Counting Bloom Filter. \nCount: " + cbf1.count(country) + "\n");
            } else {
                System.out.println(country + " is not in the Counting Bloom Filter. \nCount: " + cbf1.count(country) + "\n");
            }
        }

        /* Decrement the count of a country by two */
        System.out.println("*Decrement the count of a country by one\n");
        for(String country : countries){
            if(cbf1.isElement(country)){
                System.out.println("-> Country: " + country + "\n");
                System.out.println("   Count before decrement: " + cbf1.count(country) + "\n");
                cbf1.deleteElement(country);
                System.out.println("   Count after decrement: " + cbf1.count(country) + "\n");
            } else {
                System.out.println(country + " is not in the Counting Bloom Filter. \nCount: " + cbf1.count(country) + "\n");
            }
        }

        /* End of CountingBloomFilterTest */
        System.out.println("\n---------------------------------");
        System.out.println("End of Counting Bloom Filter Test");
        System.out.println("---------------------------------\n");
    }


    public static List<Player> getPlayers() {

        /* Initialize a BufferedReader */
        BufferedReader br = null;

        /* Try to open "PlayersCompleteDataset.csv" file */
        try {
            br = new BufferedReader(new FileReader("PlayersCompleteDataset.csv"));
        } catch (FileNotFoundException e){
            System.out.println("\nError opening the file!");
            System.exit(1);
        }

        /* Initialize ArrayList of players*/
        List<Player> playersList = new ArrayList<>();

        try {
            /* Ignore the header */
            String firsLine = br.readLine();

            /* Read first line */
            String line = br.readLine();

            /* Read all lines */
            while (line != null) {
                /* Split the line by columns */
                String[] lineArray = line.split(",");

                /* Parsing of players attributes */
                String name = lineArray[1];
                int age = Integer.parseInt(lineArray[2]);
                String country = lineArray[4];
                int overall = Integer.parseInt(lineArray[6]);
                int potential = Integer.parseInt(lineArray[7]);
                String club = lineArray[8];
                double value;
                double wage;
                try {
                    value = Double.parseDouble(lineArray[10].substring(1, lineArray[10].length() - 1));
                    wage = Double.parseDouble(lineArray[11].substring(1, lineArray[11].length() - 1));
                } catch (NumberFormatException e) {
                    value = 0;
                    wage = 0;
                }
                String position = lineArray[63].substring(0,2);

                /* Creating new player */
                Player p = new Player(name,age,country,overall,potential,club,value,wage,position);

                /* Add the player do the players list */
                playersList.add(p);

                /* Read next line */
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("\nError opening the file.");
        }

        /* Return the playersList */
        return playersList;
    }
}
