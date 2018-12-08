public class StochasticCounter {
    /* Attributes */
    private static int id = 0;
    private int myID;
    private double probability;
    private int events;
    private int value;

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
                "id=" + myID +
                ", probability=" + probability +
                ", events=" + this.getEvents() +
                ", value=" + value +
                ", expected value=" + String.format("%.2f",this.expectedValue()) +
                ", variance=" + String.format("%.2f",this.getVariance()) +
                ", standard deviation=" + String.format("%.2f",this.getStandardDeviation()) +
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

    public double expectedValue() {
        return events*probability;
    }

    public double getVariance() {
        double individualVariance = (Math.pow(0,2)*(1-probability) + Math.pow(1,2)*probability) - Math.pow(0*(1-probability) + 1*probability,2);
        double variance = 0;
        for(int i = 0; i < getEvents(); i++) {
            variance += individualVariance;
        }
        return variance;
    }

    public double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }
}
