package main.service;

import model.GameRoom;
import model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    public void addPlayerToGame(String gameId, Player player) {
        gameRoomList.stream()
                .filter(room -> room.getUuid().equals(gameId))
                .findFirst().get().getPlayers().add(player);
    }

//    public void fullGameRoom(String  gameId){
//        gameRoomList.stream()
//                .filter(room -> room.getUuid().equals(gameId))
//                .findFirst().get().setAvailable(false);
//    }

    public  void increasePlayerGameScore(String gameId, Player player){
        gameRoomList.stream()
                .filter(room -> room.getUuid().equals(gameId))
                .findFirst().get().getPlayers().stream()
                    .filter(roomPlayer -> roomPlayer.getUuid().equals(player.getUuid()))
                    .findFirst().get().setScore(player.getScore());
    }

    public void removePlayerToGame(String gameId, Player player){
        if (gameId.length() > 1  && player.getUuid().length() > 1 ) {
            gameRoomList.stream()
                    .filter(room -> room.getUuid().equals(gameId))
                    .findFirst().get().getPlayers()
                    .removeIf(roomPlayer -> roomPlayer.getUuid().equals(player.getUuid()));
        }
    }

}
