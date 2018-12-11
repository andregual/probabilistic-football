import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("-------------");
            System.out.println("PROGRAM MENU");
            System.out.println("-------------");
            System.out.println("1.) Stochastic Counter");
            System.out.println("2.) Counting Bloom Filter");
            System.out.println("3.) Find Similarities");
            System.out.println("4.) List Players Dataset");
            System.out.println("5.) Exit Program");
            System.out.println("-------------");
            System.out.println("Enter Your Menu Choice: ");

            int op = sc.nextInt();

            switch (op) {

                case 1:
                    stochasticCounterMenu(sc);
                    break;
                case 2:
                    countingBloomFilterMenu(sc);
                    break;
                case 3:
                    findSimilaritiesMenu(sc);
                    break;
                case 4:
                    playersMenu(sc);
                    break;
                case 5:
                    System.out.println("\nExiting program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid option!");
                    break;
            }
        }

    }

    public static void stochasticCounterMenu(Scanner sc) {

        StochasticCounter sc1 = null;
        while (true) {
            System.out.println("---------------------------");
            System.out.println("Stochastic Counter Menu");
            System.out.println("---------------------------");
            System.out.println("1.) Create Stochastic Counter");
            System.out.println("2.) List current Stochastic Counter");
            System.out.println("3.) Run test for players");
            System.out.println("4.) Run test for countries");
            System.out.println("5.) Run test for teams");
            System.out.println("6.) Back to Main Menu");
            System.out.println("---------------------------");
            System.out.print("> ");

            int op = sc.nextInt();

            switch (op) {

                case 6:
                    return;
                case 1:
                    System.out.println("\n1.) Create Stochastic Counter");
                    double probability;
                    do {
                        System.out.print("\nPlease, enter a probability: \n>");
                        probability = sc.nextDouble();
                        if(probability <= 0 || probability > 1)
                            System.out.println("\nInvalid probability! Please try again");
                    } while (probability <= 0 || probability > 1);

                    try {
                        sc1 = new StochasticCounter(probability);
                    } catch (Exception e) {
                        System.out.println("\nError creating the Stochastic Counter! Returning back to the main menu...");
                        return;
                    }
                    System.out.println("\nStochastic Counter created with increment probability of " + probability);
                    break;
                case 2:
                    if(sc1 == null) {
                        System.out.println("\nThere is no Stochastic Counter added");
                        break;
                    }
                    System.out.println("\n2.) List current Stochastic Counter");
                    System.out.println(sc1);
                    break;
                case 3:
                    System.out.println("\n3.) Run test for players");

                    List<Player> playerList = getPlayers();

                    if(sc1 == null) {
                        System.out.println("\nThere is no Stochastic Counter added");
                        break;
                    }
                    sc1.clean();
                    System.out.println("\nIncrementing Stochastic Counter for all players...");
                    for(Player p : playerList) {
                        sc1.incrementStochasticCounter();
                    }
                    System.out.println("\nAccording to the Stochastic Counter, there are " + sc1.getEvents() + " players.");
                    System.out.println("\nThe value of the Stochastic Counter is " + sc1.getValue());
                    break;
                case 4:
                    System.out.println("\n4.) Run test for countries");

                    Set<String> countries = getCountries();

                    if(sc1 == null) {
                        System.out.println("\nThere is no Stochastic Counter added");
                        break;
                    }
                    sc1.clean();
                    System.out.println("\nIncrementing Stochastic Counter for all countries...");
                    for(String country : countries) {
                        sc1.incrementStochasticCounter();
                    }
                    System.out.println("\nAccording to the Stochastic Counter, there are " + sc1.getEvents() + " countries.");
                    System.out.println("\nThe value of the Stochastic Counter is " + sc1.getValue());
                    break;
                case 5:
                    System.out.println("\n5.) Run test for teams");

                    Set<String> teams = getTeams();

                    if(sc1 == null) {
                        System.out.println("\nThere is no Stochastic Counter added");
                        break;
                    }
                    sc1.clean();
                    System.out.println("\nIncrementing Stochastic Counter for all teams...");
                    for(String team : teams) {
                        sc1.incrementStochasticCounter();
                    }
                    System.out.println("\nAccording to the Stochastic Counter, there are " + sc1.getEvents() + " teams.");
                    System.out.println("\nThe value of the Stochastic Counter is " + sc1.getValue());
                    break;
                default:
                    System.out.println("\nInvalid option!");
                    break;

            }
        }

    }

    public  static void countingBloomFilterMenu(Scanner sc) {

        CountingBloomFilter cb1 = null;
        CountingBloomFilter cb2 = null;
        double falsePositiveProbability = 0.00000001;

        while (true) {

            System.out.println("------------------------");
            System.out.println("Counting Bloom Filter Menu");
            System.out.println("------------------------");
            System.out.println("1.) Create Counting Bloom Filter ");
            System.out.println("2.) List current Counting Bloom Filters ");
            System.out.println("3.) Number of players");
            System.out.println("4.) Remove a player");
            System.out.println("5.) Back to Main Menu");
            System.out.println("---------------------------");
            System.out.print("> ");

            int op = sc.nextInt();

            switch (op) {

                case 5:
                    return;
                case 1:
                    while (true) {
                        System.out.println("\n1.) Create Counting Bloom Filter ");
                        System.out.println("Select for what you want to use your Counting Bloom Filter:");
                        System.out.println("1.) Countries");
                        System.out.println("2.) Teams");
                        System.out.print("> ");
                        int op2 = sc.nextInt();
                        switch (op2) {

                            case 1:
                                System.out.println("\nAddind countries to the Counting Bloom Filter...");
                                cb1 = null;
                                cb1 = new CountingBloomFilter(getCountries().size(), falsePositiveProbability);
                                for (Player p : getPlayers()) {
                                    cb1.insertElement(p.getCountry());
                                }
                                System.out.println("\nFinished adding countries to the Counting Bloom Filter!");
                                break;
                            case 2:
                                System.out.println("\nAddind teams to the Counting Bloom Filter...");
                                cb2 = null;
                                cb2 = new CountingBloomFilter(getTeams().size(), falsePositiveProbability);
                                for (Player p : getPlayers()) {
                                    cb2.insertElement(p.getClub());
                                }
                                System.out.println("\nFinished adding teams to the Counting Bloom Filter!");
                                break;
                            default:
                                System.out.println("\nInvalid option!");
                                break;
                        }
                        break;
                    }
                    break;
                case 2:
                    System.out.println("\n2.) List current Counting Bloom Filters ");
                    if(cb1 == null && cb2 == null) {
                        System.out.println("\nThere are no Counting Bloom Filters added");
                    } else if(cb1 == null) {
                        System.out.println("\nTeams Counting Bloom Filter");
                        System.out.println(cb2);
                    } else if(cb2 == null) {
                        System.out.println("\nCountries Counting Bloom Filter");
                        System.out.println(cb1);
                    } else {
                        System.out.println("\nCountries Counting Bloom Filter");
                        System.out.println(cb1);
                        System.out.println("\nTeams Counting Bloom Filter");
                        System.out.println(cb2);
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("\n3.) Number of players");
                        System.out.println("Select one Counting Bloom Filter:");
                        System.out.println("1.) Countries");
                        System.out.println("2.) Teams");
                        System.out.print("> ");
                        int op2 = sc.nextInt();
                        switch (op2) {
                            case 1:
                                if(cb1 == null){
                                    System.out.println("\nThere are no Counting Bloom Filters added for countries!");
                                    break;
                                }
                                System.out.println("\nNumber of players per country:\n");
                                for(String country : getCountries()) {
                                    if(cb1.isElement(country)) {
                                        System.out.println(country);
                                        System.out.println("Number of players: " + cb1.count(country) + "\n");
                                    }
                                }
                                break;
                            case 2:
                                if(cb2 == null) {
                                    System.out.println("\nThere are no Counting Bloom Filters added for teams!");
                                    break;
                                }
                                System.out.println("\nNumber of players per team:\n");
                                for(String team : getTeams()) {
                                    if(cb2.isElement(team)) {
                                        System.out.println(team);
                                        System.out.println("Number of players: " + cb2.count(team) + "\n");
                                    }
                                }
                                break;
                            default:
                                System.out.println("\nInvalid option!");
                                break;
                        }
                        break;
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.println("\n4.) Remove a player");
                        System.out.println("Select one Counting Bloom Filter:");
                        System.out.println("1.) Countries");
                        System.out.println("2.) Teams");
                        System.out.print("> ");
                        int op2 = sc.nextInt();
                        switch (op2) {
                            case 1:
                                if(cb1 == null) {
                                    System.out.println("\nThere are no Counting Bloom Filters added for countries!");
                                    break;
                                }
                                System.out.println("\nRemoving one player for each country...\n");
                                for(String country : getCountries()) {
                                    cb1.deleteElement(country);
                                }
                                break;
                            case 2:
                                if(cb2 == null) {
                                    System.out.println("\nThere are no Counting Bloom Filters added for teams!");
                                    break;
                                }
                                System.out.println("\nRemove one player for each team:\n");
                                for(String team : getTeams()) {
                                    cb2.deleteElement(team);
                                }
                                break;
                            default:
                                System.out.println("\nInvalid option!");
                                break;
                        }
                        break;
                    }
                    break;
                default:
                    System.out.println("\nInvalid option!");
                    break;
            }
        }
    }

    public  static void findSimilaritiesMenu(Scanner sc) {

        MinHash mh1 = null;
        MinHash mh2 = null;
        /* Number of hash functions */
        int k = 100;

        while (true) {
            System.out.println("------------------------");
            System.out.println("Find Similarities Menu");
            System.out.println("------------------------");
            System.out.println("1.) Create MinHash");
            System.out.println("2.) Find Similar Pairs ");
            System.out.println("3.) Back to Main Menu ");
            System.out.print("> ");
            int op = sc.nextInt();

            switch (op) {
                case 3:
                    return;
                case 1:
                    while (true) {
                        System.out.println("\n1.) Create MinHash");
                        System.out.println("Select one MinHash type:");
                        System.out.println("1.) Countries");
                        System.out.println("2.) Teams");
                        System.out.print("> ");
                        int op2 = sc.nextInt();
                        switch (op2) {
                            case 1:
                                HashMap<String, Set<Integer>> countries = new HashMap<>();

                                for(Player p : getPlayers()) {
                                    if(!countries.containsKey(p.getCountry())) {
                                        Set<Integer> values = new HashSet<>();
                                        values.add(p.getOverall());
                                        countries.put(p.getCountry(), values);
                                    } else {
                                        Set<Integer> values = countries.get(p.getCountry());
                                        values.add(p.getOverall());
                                        countries.put(p.getCountry(), values);
                                    }
                                }

                                System.out.println("\nCreating MinHash matrix for contries...");
                                mh1 = null;
                                mh1 = new MinHash(countries, k);
                                System.out.println("\nComputing countries signatures...");
                                for(String country : countries.keySet()) {
                                    Set<Integer> countryOveralls = countries.get(country);
                                    int i = 0;
                                    int[] overalls = new int[countryOveralls.size()];
                                    for(Integer overall : countryOveralls) {
                                        overalls[i] = (int)(overall);
                                        i++;
                                    }
                                    mh1.computeSignature(overalls);
                                }
                                break;
                            case 2:
                                HashMap<String, Set<Integer>> teams = new HashMap<>();

                                for(Player p : getPlayers()) {
                                    if(!teams.containsKey(p.getClub())) {
                                        Set<Integer> values = new HashSet<>();
                                        values.add(p.getOverall());
                                        teams.put(p.getClub(), values);
                                    } else {
                                        Set<Integer> values = teams.get(p.getClub());
                                        values.add(p.getOverall());
                                        teams.put(p.getClub(), values);
                                    }
                                }

                                System.out.println("\nCreating MinHash matrix for teams...");
                                mh2 = null;
                                mh2 = new MinHash(teams, k);
                                System.out.println("\nComputing teams signatures...");
                                for(String team : teams.keySet()) {
                                    Set<Integer> teamOveralls = teams.get(team);
                                    int i = 0;
                                    int[] overalls = new int[teamOveralls.size()];
                                    for(Integer overall : teamOveralls) {
                                        overalls[i] = (int)(overall);
                                        i++;
                                    }
                                    mh2.computeSignature(overalls);
                                }
                                break;
                            default:
                                System.out.println("\nInvalid option!");
                                break;
                        }
                        break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("\n2.) Find Similar Pairs ");
                        System.out.println("Select what you want to compare:");
                        System.out.println("1.) Countries");
                        System.out.println("2.) Teams");
                        System.out.print("> ");
                        int op2 = sc.nextInt();
                        double threshold;
                        switch (op2) {
                            case 1:
                                if(mh1 == null) {
                                    System.out.println("\nThere are no countries to compare!");
                                    break;
                                }
                                System.out.println("\nPlease, enter a threshold value: ");
                                System.out.print("> ");
                                threshold = sc.nextDouble();
                                while(threshold <= 0 || threshold >= 1) {
                                    System.out.println("\nInvalid value for threshold! Please, insert another: ");
                                    System.out.print("> ");
                                    threshold = sc.nextInt();
                                }
                                System.out.println("\nFinding similar countries...");
                                System.out.println("\nSimiliar pairs countries:\n");
                                mh1.findSimiliarPairs(threshold);
                                break;
                            case 2:
                                if(mh2 == null) {
                                    System.out.println("\nThere are no teams to compare!");
                                    break;
                                }
                                System.out.println("\nPlease, enter a threshold value: ");
                                System.out.print("> ");
                                threshold = sc.nextDouble();
                                while(threshold <= 0 || threshold >= 1) {
                                    System.out.println("\nInvalid value for threshold! Please, insert another: ");
                                    System.out.print("> ");
                                    threshold = sc.nextInt();
                                }
                                System.out.println("\nFinding similar teams...");
                                System.out.println("\nSimiliar teams found:\n");
                                mh2.findSimiliarPairs(threshold);
                                break;
                            default:
                                System.out.println("\nInvalid option!");
                                break;
                        }
                        break;
                    }
                    break;
                default:
                    System.out.println("\nInvalid option!");
                    break;
            }
        }
    }

    public  static void playersMenu(Scanner sc) {

        System.out.println("\nAll players in the Dataset");
        for(Player p : getPlayers()) {
            System.out.println(p);
        }
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
            System.out.println("\nError opening the file.");
        }

        /* Return the playersList */
        return playersList;
    }

    public static Set<String> getCountries() {

        List<Player> playerList = getPlayers();

        Set<String> countries = new HashSet<>();

        for(Player p : playerList){
            countries.add(p.getCountry());
        }

        return countries;
    }

    public static Set<String> getTeams() {

        List<Player> playerList = getPlayers();

        Set<String> teams = new HashSet<>();

        for(Player p : playerList){
            teams.add(p.getClub());
        }

        return teams;
    }

}
