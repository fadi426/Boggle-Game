package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.service.GameRoomService;
import main.service.PlayerService;
import main.service.WordService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class BoggleController {

    @Autowired
    private WordService wordService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameRoomService gameRoomService;

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/words")
    public List<Word> getAllArticles(){
        return wordService.getAllWords();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/word/{word}")
    public Word getWord(@PathVariable String word){
        return wordService.getWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/words")
    public String addWords(@RequestBody Word word){
        System.out.println(word.getWord());
        return wordService.checkWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.DELETE, value = "words/{word}")
    public void deleteWord(@PathVariable String word){
        wordService.deleteWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "test1")
    public void test1(@RequestBody ValidWord word){
        wordService.addValidWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/test1")
    public List<ValidWord> getAllValidWords(){
        return wordService.getAllValidWords();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(HelloMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        Greeting aap = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(aap);
        return jsonInString;
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/players")
    public List<Player> getPlayers(){
        return playerService.getAllPlayers();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/players")
    public void addPlayers(@RequestBody Player player){
        System.out.println(player.getName());
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
        System.out.println(gameRoom.getName());
        gameRoomService.addGameRoom(gameRoom);
    }


}
