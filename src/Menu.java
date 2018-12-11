import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Menu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-------------");
            System.out.println("PROGRAM MENU");
            System.out.println("-------------");
            System.out.print("1.) Stochastic Counter \n");
            System.out.print("2.) Counting Bloom Filter \n");
            System.out.print("3.) Min Hash \n");
            System.out.print("4.) Players \n");
            System.out.print("5.) Exit Program \n");
            System.out.println("-------------");
            System.out.print("\nEnter Your Menu Choice: ");

            int op = sc.nextInt();

            List<StochasticCounter> Stoc_list = new ArrayList<>(); // Array com os Contadores Estocásticos criados
            CountingBloomFilter[] Bloom_list = new CountingBloomFilter[2]; // Array com os Counting Bloom Filters criados
            MinHash[] Min_List = new MinHash[2]; // Array com os Min Hash criados
            List<Player> playerList = getPlayers(); // Lista com os players e todos os atributos
            Set<String> countriesSet = getCountries(playerList); // Set com a lista dos países
            Set<String> teamsSet = getTeams(playerList); // Set com a lista dos clubes

            boolean checker;

            switch (op) {

                case 1:

                    checker = true;
                    while (checker) {
                        System.out.println("---------------------------");
                        System.out.println("Stochastic Counter Menu");
                        System.out.println("---------------------------");
                        System.out.println("1.) Create Stochastic Counter");
                        System.out.println("2.) List current Stochastic Counters");
                        System.out.println("3.) Run test for players");
                        System.out.println("4.) Run test for countries");
                        System.out.println("5.) Run test for teams");
                        System.out.println("6.) Back to Main Menu");
                        System.out.println("---------------------------");
                        System.out.print("> ");

                        int op2 = sc.nextInt();

                        switch (op2) {

                            case 1:
                                System.out.println("Number of Stochastic Counters to be added : ");
                                int Stoc_n = sc.nextInt();
                                StochasticCounter[] Stocs_names = new StochasticCounter[Stoc_n];
                                System.out.println(Stoc_n);

                                for (int i = 0 ; i < Stoc_n ; i++ ) {
                                    int numero = i + 1;
                                    System.out.println("Probability" + numero +" : ");
                                    float prob = sc.nextFloat();
                                    Stocs_names[i] = new StochasticCounter(prob);
                                    Stoc_list.add(Stocs_names[i]);

                                }

                                System.out.println("Added " + Stoc_n + " Stochastic Counters");

                                break;

                            case 2:

                                if (Stoc_list.size() == 0) {
                                    System.out.println("No Stochastic Counters Found");
                                }
                                else {
                                    System.out.println("\nCurrent Stochastic Counters : ");
                                    for (int j = 0; j < Stoc_list.size(); j++) {
                                        System.out.println(j + " -> " + Stoc_list.get(j));
                                    }
                                }

                                break;

                            case 3:
                                if(Stoc_list.size() == 0) {
                                    System.out.println("No Stochastic Counters Found.");
                                }
                                else {
                                    for (Player p : playerList) {
                                        for (StochasticCounter counter : Stoc_list) {
                                            counter.incrementStochasticCounter();
                                        }
                                    }
                                }

                                System.out.println("No Stochastic Counters Found.");

                                break;

                            case 4:
                                if(Stoc_list.size() == 0) {
                                    System.out.println("Stochastic Counters Updated.");
                                }
                                else {
                                    for (String p : countriesSet) {
                                        for (StochasticCounter counter : Stoc_list) {
                                            counter.incrementStochasticCounter();
                                        }
                                    }
                                }

                                System.out.println("Stochastic Counters Updated.");

                                break;

                            case 5:
                                if(Stoc_list.size() == 0) {
                                    System.out.println("No Stochastic Counters Found.");
                                }
                                else {
                                    for (String p : teamsSet) {
                                        for (StochasticCounter counter : Stoc_list) {
                                            counter.incrementStochasticCounter();
                                        }
                                    }
                                }

                                System.out.println("Stochastic Counters Updated.");

                                break;

                            case 6:
                                checker = false;
                                break;

                            default:
                                System.out.println("This is not a valid Menu Option! Please Select Another");
                                break;

                        }

                    }

                    break;

                case 2:

                    checker = true;
                    while (checker) {
                        System.out.println("------------------------");
                        System.out.println("Counting Bloom Filter Menu");
                        System.out.println("------------------------");
                        System.out.println("1.) Create Counting Bloom Filter ");
                        System.out.println("2.) List current Counting Bloom Filters ");
                        System.out.println("3.) Check if string is in the Bloom Filter ");
                        System.out.println("4.) Delete a Bloom Filter element");
                        System.out.println("5.) Back to Main Menu \n");
                        System.out.println("---------------------------");
                        System.out.print("> ");



                        int op2 = sc.nextInt();

                        switch (op2) {

                            case 1:

                                CountingBloomFilter[] Bloom_names = new CountingBloomFilter[2];
                                System.out.println("Select the Bloom Filter :");
                                System.out.println("1.) Countries");
                                System.out.println("2.) Teams");
                                System.out.print("> ");
                                int op3 = sc.nextInt();

                                switch(op3) {

                                    case 1:

                                        int countriesSize = countriesSet.size();
                                        System.out.println("False Positive Probability :" );
                                        double fpp_country = sc.nextDouble();
                                        Bloom_names[0] = new CountingBloomFilter(countriesSize, fpp_country);
                                        Bloom_list[0] = Bloom_names[0];
                                        CountingBloomFilter cbf_countries = Bloom_list[0];

                                        for(Player p : playerList) {
                                            cbf_countries.insertElement(p.getCountry());
                                        }
                                        System.out.println("Added Countries Counting Bloom Filter");

                                        break;

                                    case 2:

                                        int teamsSize = teamsSet.size();
                                        System.out.println("False Positive Probability:" );
                                        double fpp_team = sc.nextDouble();
                                        Bloom_names[1] = new CountingBloomFilter(teamsSize, fpp_team);
                                        Bloom_list[1] = Bloom_names[1];
                                        CountingBloomFilter cbf_teams = Bloom_list[1];

                                        for(Player p : playerList) {
                                            cbf_teams.insertElement(p.getClub());
                                        }
                                        System.out.println("Added Teams Counting Bloom Filter");
                                        break;

                                    default:
                                        System.out.println("This is not a valid Menu Option! Please Select Another");

                                }

                                break;

                            case 2:

                                if (Bloom_list.length == 0) {
                                    System.out.println("No Counting Bloom Filters Found");
                                }
                                else {
                                    System.out.println("\nCurrent Counting Bloom Filters : ");
                                    for (int j = 0; j < Bloom_list.length; j++) {
                                        System.out.println(j + " -> " + Bloom_list[j]);
                                    }
                                }

                                break;

                            case 3:
                                System.out.println("Select the Bloom Filter :");
                                System.out.println("1.) Countries");
                                System.out.println("2.) Teams");
                                System.out.print("> ");
                                int op_insert = sc.nextInt();
                                switch (op_insert) {

                                    case 1:
                                        if (Bloom_list.length == 0) {
                                            System.out.println("No Counting Bloom Filters Found");
                                        } else {
                                            CountingBloomFilter cbf = Bloom_list[0];
                                            System.out.println("Insert the string that you want to search : ");
                                            String search_word = sc.next();

                                            if (cbf.isElement(search_word)) {
                                                System.out.println(search_word + " is probably in the Counting Bloom Filter. \nCount: " + cbf.count(search_word) + "\n");
                                            } else {
                                                System.out.println(search_word + " is not in the Counting Bloom Filter. \nCount: " + cbf.count(search_word) + "\n");
                                            }
                                        }
                                    break;

                                    case 2:
                                        if (Bloom_list.length == 0) {
                                            System.out.println("No Counting Bloom Filters Found");
                                        } else {
                                            CountingBloomFilter cbf = Bloom_list[1];
                                            System.out.println("Insert the string that you want to search : ");
                                            String search_word = sc.next();

                                            if (cbf.isElement(search_word)) {
                                                System.out.println(search_word + " is probably in the Counting Bloom Filter. \nCount: " + cbf.count(search_word) + "\n");
                                            } else {
                                                System.out.println(search_word + " is not in the Counting Bloom Filter. \nCount: " + cbf.count(search_word) + "\n");
                                            }
                                        }
                                        break;

                                    default:
                                        System.out.println("This is not a valid Menu Option! Please Select Another");

                                }

                                break;

                            case 4:

                                if(Bloom_list.length == 0) {
                                    System.out.println("No Counting Bloom Filters Found");
                                }
                                else {
                                    System.out.println("Select the Bloom Filter :");
                                    System.out.println("1.) Countries");
                                    System.out.println("2.) Teams");
                                    System.out.print("> ");
                                    int op4 = sc.nextInt();

                                    switch(op4) {

                                        case 1:

                                            CountingBloomFilter cbf_countries = Bloom_list[0];
                                            System.out.println("Insert the string that you want to delete : ");
                                            String delete_word_countries = sc.next();
                                            cbf_countries.deleteElement(delete_word_countries);
                                            System.out.println(delete_word_countries + " deleted from the bloom filter");

                                            break;

                                        case 2:

                                            CountingBloomFilter cbf_teams = Bloom_list[1];
                                            System.out.println("Insert the string that you want to delete : ");
                                            String delete_word_teams = sc.next();
                                            cbf_teams.deleteElement(delete_word_teams);
                                            System.out.println(delete_word_teams + " deleted from the bloom filter");
                                            break;

                                        default:
                                            System.out.println("This is not a valid Menu Option! Please Select Another");

                                    }

                                    break;

                                }

                            case 5:

                                checker = false;
                                break;

                            default:
                                System.out.println("This is not a valid Menu Option! Please Select Another");
                                break;

                        }

                    }

                    break;

                case 3:

                    checker = true;
                    while (checker) {
                        System.out.println("------------------------");
                        System.out.println("     Min hash Menu");
                        System.out.println("------------------------");
                        System.out.println("1.) Create Min Hash");
                        System.out.println("2.) Find Similar Pairs ");
                        System.out.println("3.) Back to Main Menu ");
                        System.out.print("> ");
                        int op2 = sc.nextInt();
                        HashMap<String, Set<Integer>> countries = new HashMap<>();
                        HashMap<String, Set<Integer>> teams = new HashMap<>();

                        switch (op2) {

                            case 1:
                                System.out.println("Select the Min Hash :");
                                System.out.println("1.) Countries");
                                System.out.println("2.) Teams");
                                System.out.print("> ");
                                int op3 = sc.nextInt();

                                switch (op3) {
                                    case 1:
                                        for(Player p : playerList) {
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
                                        MinHash minHash_countries = new MinHash(countries, 100);
                                        Min_List[0] = minHash_countries;
                                        break;

                                    case 2:
                                        for(Player p : playerList) {
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
                                        MinHash minHash_teams = new MinHash(teams, 100);
                                        Min_List[1] = minHash_teams;
                                        break;

                                    default:
                                        System.out.println("This is not a valid Menu Option! Please Select Another");
                                }

                                break;

                            case 2:
                                System.out.println("Select the Min Hash :");
                                System.out.println("1.) Countries");
                                System.out.println("2.) Teams");
                                System.out.print("> ");
                                int op4 = sc.nextInt();
                                countries = Min_List[0].getClubs();
                                teams = Min_List[1].getClubs();

                                switch (op4) {

                                    case 1:
                                        for (String country : countries.keySet()) {
                                            Set<Integer> countriesOveralls = countries.get(country);
                                            int i = 0;
                                            int[] overalls = new int[countriesOveralls.size()];
                                            for (Integer overall : countriesOveralls) {
                                                overalls[i] = (int) (overall);
                                                i++;
                                            }
                                            Min_List[0].computeSignature(overalls);
                                        }

                                        Min_List[0].findSimiliarPairs(0.4);
                                        break;

                                    case 2:
                                        for (String team : teams.keySet()) {
                                            Set<Integer> clubOveralls = teams.get(team);
                                            int i = 0;
                                            int[] overalls = new int[clubOveralls.size()];
                                            for (Integer overall : clubOveralls) {
                                                overalls[i] = (int) (overall);
                                                i++;
                                            }
                                            Min_List[1].computeSignature(overalls);
                                        }

                                        Min_List[1].findSimiliarPairs(0.4);
                                    break;

                                    default:
                                        System.out.println("This is not a valid Menu Option! Please Select Another");

                                }

                                break;

                            case 3:
                                checker = false;
                                break;
                        }
                    }

                    break;

                case 4:

                    checker = true;
                    while (checker) {
                        System.out.println("------------------------");
                        System.out.println("Player Menu");
                        System.out.println("------------------------");
                        System.out.print("1.) Get Players Database \n");
                        System.out.print("2.) Add Player to Database \n");
                        System.out.print("3.) Remove Player from Database \n");
                        System.out.print("4.) Back to Main Menu \n");

                        int op2 = sc.nextInt();

                        switch (op2) {

                            case 1:

                                System.out.println("o");

                                break;

                            case 4:

                                checker = false;
                                break;

                            default:
                                System.out.println("This is not a valid Menu Option! Please Select Another");
                                break;

                        }

                    }

                    break;

                case 5:
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("This is not a valid Menu Option! Please Select Another \n ");
                    break;
            }

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

    public static Set<String> getCountries(List<Player> playerList) {

        Set<String> countries = new HashSet<>();

        for(Player p : playerList){
            countries.add(p.getCountry());
        }

        return countries;
    }

    public static Set<String> getTeams(List<Player> playerList) {

        Set<String> teams = new HashSet<>();

        for(Player p : playerList){
            teams.add(p.getClub());
        }

        return teams;
    }

}
