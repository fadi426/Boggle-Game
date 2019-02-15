package model;

import java.util.ArrayList;
import java.util.List;

public class GameRoom {

    private String uuid;
    private String name;
    private List<Player> players;

    public GameRoom(){

    }

    public GameRoom(String uuid, String name, List<Player> players){
        this.uuid = uuid;
        this.name = name;
        players = new ArrayList<Player>();
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
}
