package main;

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

import java.util.List;

@RestController
public class BoggleController {

    @Autowired
    private WordService wordService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameRoomService gameRoomService;

//    @CrossOrigin(origins = "http://localhost:8081")
//    @RequestMapping("/words")
//    public List<Word> getAllArticles(){
//        return wordService.getAllWords();
//    }

//    @CrossOrigin(origins = "http://localhost:8081")
//    @RequestMapping("/word/{word}")
//    public Word getWord(@PathVariable String word){
//        return wordService.getWord(word);
//    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/words")
    public String addWords(@RequestBody Word word){
        System.out.println(word.getWord());
        return wordService.checkWord(word);
    }

//    @CrossOrigin(origins = "http://localhost:8081")
//    @RequestMapping(method = RequestMethod.POST, value = "test1")
//    public void test1(@RequestBody ValidWord word){
//        wordService.addValidWord(word);
//    }

//    @CrossOrigin(origins = "http://localhost:8081")
//    @RequestMapping("/test1")
//    public List<ValidWord> getAllValidWords(){
//        return wordService.getAllValidWords();
//    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/players")
    public List<Player> getPlayers(){
        return playerService.getAllPlayers();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/players")
    public void addPlayers(@RequestBody Player player){
        //System.out.println(player.getName());
         playerService.addPlayer(player);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/gamerooms")
    public List<GameRoom> getGameRooms(){
        return gameRoomService.getAllGameRoom();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/gamerooms")
    public void addGameRooms(@RequestBody GameRoom gameRoom){
        //System.out.println(gameRoom.getName());
        gameRoomService.addGameRoom(gameRoom);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/gamerooms/addplayer")
    public void addPlayerToGameRoom(@RequestBody PlayerToRoom playerToRoom){
        //System.out.println(playerToRoom.getGameRoomId() + " " + playerToRoom.getPlayers().getName());
        gameRoomService.addPlayerToGame(playerToRoom.getGameRoomId(), playerToRoom.getPlayers());
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/gamerooms/removeplayer")
    public void removePlayerFromGame(@RequestBody PlayerToRoom playerToRoom){
        gameRoomService.removePlayerToGame(playerToRoom.getGameRoomId(), playerToRoom.getPlayers());
        //System.out.println("removed!");
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @MessageMapping("/hello")
    @SendTo("/topic/gamerooms")
    public void showGameRooms(String message) throws Exception {
        Thread.sleep(500); // simulated delay
        //System.out.println(message);
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PlayerToRoom playerToRoom = mapper.readValue(message, PlayerToRoom.class);
        gameRoomService.increasePlayerGameScore(playerToRoom.getGameRoomId(), playerToRoom.getPlayers());

        //System.out.println(message);
    }

}
