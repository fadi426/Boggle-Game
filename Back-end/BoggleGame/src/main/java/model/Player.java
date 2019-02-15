package model;

public class Player {

    private String uuid;
    private String name;
    private int score;


    public Player() {
    }

    public Player(String uuid, String name, int score) {
        this.uuid = uuid;
        this.name = name;
        this.score = score;
    }

    public String getUuid() {
        return uuid;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
