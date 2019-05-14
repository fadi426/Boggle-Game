package main.service;

import model.GameRoom;
import model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameRoomService {

    List<GameRoom> gameRoomList = new ArrayList<GameRoom>(Arrays.asList());

    public List<GameRoom> getAllGameRoom() {
        return gameRoomList;
    }

    public void addGameRoom(GameRoom gameRoom){
        gameRoomList.add(gameRoom);
    }

    public void removeGameRoom(){
        gameRoomList.removeIf(room -> room.getPlayers().isEmpty());
    }

    // look for the matching GameRoom UUID and add a player to that room
    public void addPlayerToGame(String gameId, Player player) {
        gameRoomList.stream()
                .filter(room -> room.getUuid().equals(gameId))
                .findFirst().get().getPlayers().add(player);
    }

    // look for the matching GameRoom UUID and retrieve its information
    public GameRoom findGameRoom(String gameId){
        GameRoom gameRoom = null;
        for (GameRoom room : gameRoomList){
            if (gameId.equals(room.getUuid()))
                gameRoom = room;
        }
        return gameRoom;
    }

    // look for the matching GameRoom UUID and change the score of a player in that room
    public  void changePlayerGameScore(String gameId, Player player){
        gameRoomList.stream()
                .filter(room -> room.getUuid().equals(gameId))
                .findFirst().get().getPlayers().stream()
                    .filter(roomPlayer -> roomPlayer.getUuid().equals(player.getUuid()))
                    .findFirst().get().setScore(player.getScore());
    }

    // look for the matching GameRoom UUID and remove a player from that room
    public void removePlayerToGame(String gameId, Player player){
        if (gameId.length() > 1  && player.getUuid().length() > 1 ) {
            gameRoomList.stream()
                    .filter(room -> room.getUuid().equals(gameId))
                    .findFirst().get().getPlayers()
                    .removeIf(roomPlayer -> roomPlayer.getUuid().equals(player.getUuid()));
        }
    }

}
