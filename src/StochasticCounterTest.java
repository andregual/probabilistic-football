import java.util.*;
import java.io.*;

public class StochasticCounterTest {

    /* Main function */
    public static void main(String[] args) {

        /* Get the players complete dataset */
        List<Player> playerList = getPlayers();

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
        System.out.println("STOCHASTIC COUNTERS INFO:\n");
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

                /* Get the player skills */
                HashMap<String, Integer> aux = new HashMap<>();
                aux.put("acceleration",13);
                aux.put("dribbling",21);
                aux.put("finishing",22);
                aux.put("shotPower",39);
                aux.put("strength",44);
                for(String skill : aux.keySet()) {
                    try {
                        p.addSkill(new Skill(skill, Integer.parseInt(lineArray[aux.get(skill)].substring(0,2))));
                    } catch (StringIndexOutOfBoundsException e) {
                        p.addSkill(new Skill(skill, Integer.parseInt(lineArray[aux.get(skill)].substring(0,1))));
                    }
                }

                /* Add the player do the players list */
                playersList.add(p);

                /* Read next line */
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("\nError reading the file.");
        }

        for(Player p : playersList) {
            /* Initialize a new characteristicMatrix */
            int characteristicMatrix[] = new int[51];
            for(int i = 0; i < 5; i++) {
                Skill skill = p.getSkills().get(i);
                if(0 <= skill.getValue() && skill.getValue() <= 10) {
                    characteristicMatrix[i*10 + 1] = 1;
                } else if(10 < skill.getValue() && skill.getValue() <= 20) {
                    characteristicMatrix[i*10 + 2] = 1;
                } else if(20 < skill.getValue() && skill.getValue() <= 30) {
                    characteristicMatrix[i*10 + 3] = 1;
                } else if(30 < skill.getValue() && skill.getValue() <= 40) {
                    characteristicMatrix[i*10 + 4] = 1;
                } else if(40 < skill.getValue() && skill.getValue() <= 50) {
                    characteristicMatrix[i*10 + 5] = 1;
                } else if(50 < skill.getValue() && skill.getValue() <= 60) {
                    characteristicMatrix[i*10 + 6] = 1;
                } else if(60 < skill.getValue() && skill.getValue() <= 70) {
                    characteristicMatrix[i*10 + 7] = 1;
                } else if(70 < skill.getValue() && skill.getValue() <= 80) {
                    characteristicMatrix[i*10 + 8] = 1;
                } else if(80 < skill.getValue() && skill.getValue() <= 90) {
                    characteristicMatrix[i*10 + 9] = 1;
                } else if(90 < skill.getValue() && skill.getValue() <= 100) {
                    characteristicMatrix[i*10 + 10] = 1;
                }
            }
            p.setCharacteristicMatrix(characteristicMatrix);
        }

        /* Return the playersList */
        return playersList;
    }
}
