package main.service;

import model.GameRoom;
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
}
