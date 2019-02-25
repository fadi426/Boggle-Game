package model;

public class PlayerToRoom {

    private String gameRoomId;
    private Player players;


    public PlayerToRoom() {
    }

    public PlayerToRoom(String gameRoomId, Player players) {
        this.gameRoomId = gameRoomId;
        this.players = players;

    }

    public String getGameRoomId() {
        return gameRoomId;
    }

    public Player getPlayers() {
        return players;
    }

}
