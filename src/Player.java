import java.util.*;

public class Player {
    /* Attributes */
    private static int id = 0;
    private int myID;
    private String name;
    private int age;
    private String country;
    private int overall;
    private int potential;
    private String club;
    private double value;
    private double wage;
    private String position;
    private List<Skill> skills;

    /* Constructor */
    public Player(String name, int age, String country, int overall, int potential, String club, double value, double wage, String position) {
        this.myID = id;
        id++;
        this.name = name;
        this.age = age;
        this.country = country;
        this.overall = overall;
        this.potential = potential;
        this.club = club;
        this.value = value;
        this.wage = wage;
        this.position = position;
        skills = new ArrayList<>();
    }

    /* Methods */
    @Override
    public String toString() {
        return "Player{" +
                "id=" + myID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", overall=" + overall +
                ", potential=" + potential +
                ", club='" + club + '\'' +
                ", value=" + value +
                ", wage=" + wage +
                ", position=" + position +
                '}';
    }

    /* Getters and Setters */
    public int getMyID() {
        return myID;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public int getOverall() {
        return overall;
    }

    public int getPotential() {
        return potential;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public String getPosition() {
        return position;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
}
