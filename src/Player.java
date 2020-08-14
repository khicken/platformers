package src;

public class Player {
    private int lvl;
    private double currentHp, maxHp;
    private double totalXP, lvlXP, lvlMaxXP, previousLvlsXP;
    private String name;
    private String environment;

    Player(String name) { // only name needs to be passed for default constructor, used for new player
        this.name = name;
        this.lvl = 1;

        this.previousLvlsXP = 0.0;
    }

    Player(String name, double currentHp, int lvl, double lvlXP, double previousLvlsXP, String environment) { // constructor used for loading
        this.name = name;
        this.currentHp = currentHp;
        this.lvl = lvl;
        this.lvlXP = lvlXP;
        this.previousLvlsXP = previousLvlsXP;
        this.environment = environment;
    }

    public void updateStats() {
        this.lvlMaxXP = Math.pow(this.lvl, 1.1) * (76 * this.lvl) + 16.5;
        this.lvlXP = this.totalXP - this.previousLvlsXP;
    }

    public void levelUp() { // called whenever lvlXP > lvlMaxXP
        this.maxHp = this.lvl * 20.0;
        this.previousLvlsXP += this.lvlMaxXP;
        // this.lvlXP = 0;
    }

    /************************************ getters and setters ************************************/
    public String getName() {
        return this.name;
    }

    public int getLvl() {
        return this.lvl;
    }

    public double getCurrentHp() {
        return this.currentHp;
    }

    public double getMaxHp() {
        return this.maxHp;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}