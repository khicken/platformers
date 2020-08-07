package src;

public class Player {
    private int lvl, hp;
    private String name;

    Player() {
        // empty constructor
    }

    Player(String name, int lvl, int hp) {
        this.name = name;
        this.lvl = lvl;
        this.hp = hp;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public int getLvl() {
        return lvl;
    }

    public int getHp() {
        return hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}