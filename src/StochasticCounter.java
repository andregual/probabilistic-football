public class StochasticCounter {
    /* Attributes */
    private static int id = 0;
    private int myID;
    private double probability;
    private int events;
    private int value;
    private double mean;
    // private double variance;
    // private double standardDeviation;

    /* Constructor */
    public StochasticCounter(double probability) {
        this.myID = id;
        id++;
        this.probability = probability;
    }

    /* Methods */
    @Override
    public String toString() {
        return "StochasticCounter{" +
                "myID=" + myID +
                ", probability=" + probability +
                ", events=" + this.getEvents() +
                ", value=" + value +
                ", mean=" + this.getMean() +
                '}';
    }

    public void incrementStochasticCounter() {
        double rand = Math.random();
        if(rand < probability) {
            value++;
        }
    }

    /* Getters and Setters */
    public int getMyID() {
        return myID;
    }

    public double getProbability() {
        return probability;
    }

    public int getValue() {
        return value;
    }

    public int getEvents() {
        events = (int)((1/probability)*value);
        return events;
    }

    public double getMean() {
        mean = events*probability;
        return mean;
    }
}
