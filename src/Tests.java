import java.util.*;
import java.io.*;

public class Tests {

    /* Main function */
    public static void main(String[] args) {

        /* Get the Players Complete Dataset */
        List<Player> playerList = getPlayers();

        /* StochasticCounterTest call */
        StochasticCounterTest(playerList);
    }

    public static void StochasticCounterTest(List<Player> playerList) {

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
