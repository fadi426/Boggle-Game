package model;

import java.util.ArrayList;
import java.util.List;

public class GameRoom {

    private String uuid;
    private String name;
    private List<Player> players;
    private List<String> letters;

    public GameRoom(){

    }

    public GameRoom(String uuid, String name,List<Player> players, List<String> letters){
        this.uuid = uuid;
        this.name = name;
        players = new ArrayList<Player>();
        letters = new ArrayList<String>();
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getLetters() {
        return letters;
    }

    public void setLetters(List<String> letters) {
        this.letters = letters;
    }
}
