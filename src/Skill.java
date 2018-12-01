public class Skill {
    /* Attributes */
    private String name;
    private int value;

    /* Constructor */
    public Skill(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /* Methods */
    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    /* Getters and Setters */
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
