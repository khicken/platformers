package src;

public class Player {
    private int lvl;
    private int deaths;
    private double currentHp, maxHp;
    private double totalXP, lvlXP, lvlMaxXP, previousLvlsXP;
    private String name;
    private String environment;

    Player() { // only name needs to be passed for default constructor, used for new player
        this.lvl = 1;
        this.previousLvlsXP = 0.0;
    }

    Player(String name, double currentHp, int lvl, double lvlXP, double previousLvlsXP, String environment, int deaths) { // constructor used for loading
        this.name = name;
        this.currentHp = currentHp;
        this.lvl = lvl;
        this.lvlXP = lvlXP;
        this.previousLvlsXP = previousLvlsXP;
        this.environment = environment;
        this.deaths = deaths;
    }

    public void updateStats() { // going to constantly be called at runtime
        
    }

    public void levelUp() { // called whenever lvlXP > lvlMaxXP
        this.maxHp = this.lvl * 20.0;
        this.currentHp = this.maxHp;

        this.previousLvlsXP += this.lvlMaxXP;
        this.lvlMaxXP = Math.pow(this.lvl, 1.1) * (76 * this.lvl) + 16.5;
        this.lvlXP = this.totalXP - this.previousLvlsXP;
        // this.lvlXP = 0;
    }

    public void death() {
        // k, might delete later
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

    public void addHp(double amount) {
        this.currentHp += amount;
        if(this.currentHp > this.maxHp) this.currentHp = this.maxHp;
        else if(this.currentHp <= 0) this.currentHp = 0;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}