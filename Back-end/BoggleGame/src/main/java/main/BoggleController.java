package main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.service.GameRoomService;
import main.service.PlayerService;
import main.service.WordService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class BoggleController {

    @Autowired
    private WordService wordService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameRoomService gameRoomService;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/words")
    public Word checkWord(@RequestBody Word word){
        System.out.println(word);
        return wordService.checkWord(word);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/players")
    public void addPlayers(@RequestBody Player player){
        //System.out.println(player.getName());
         playerService.addPlayer(player);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/board")
    public ArrayList<Character> checkWord(){
        System.out.println("Get Board");
        return wordService.makeBoard();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/gamerooms")
    public void addGameRooms(@RequestBody GameRoom gameRoom){
        //System.out.println(gameRoom.getName());
        gameRoomService.addGameRoom(gameRoom);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/gamerooms/addplayer")
    public void addPlayerToGameRoom(@RequestBody PlayerToRoom playerToRoom){
        //System.out.println(playerToRoom.getGameRoomId() + " " + playerToRoom.getPlayer().getName());
        gameRoomService.addPlayerToGame(playerToRoom.getGameRoomId(), playerToRoom.getPlayers());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/gamerooms/removeplayer")
    public void removePlayerFromGame(@RequestBody PlayerToRoom playerToRoom){
        gameRoomService.removePlayerToGame(playerToRoom.getGameRoomId(), playerToRoom.getPlayers());
        //System.out.println("removed!");
    }

    @MessageMapping("/topic/greetings/{roomId}")
    @SendTo("/topic/greetings/{roomId}")
    public GameRoom showGameRoomInfo(String message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        GameRoom gameRoom = mapper.readValue(message, GameRoom.class);

        gameRoomService.changePlayerGameScore(gameRoom.getUuid(), gameRoom.getPlayers().get(0));
        GameRoom roomInfo = gameRoomService.findGameRoom(gameRoom.getUuid());

        return roomInfo;
    }
}
