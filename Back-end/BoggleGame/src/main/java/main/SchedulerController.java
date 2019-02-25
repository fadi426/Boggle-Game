package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.service.GameRoomService;
import model.GameRoom;
import model.Greeting;
import model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@EnableScheduling
@Controller
public class SchedulerController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private GameRoomService gameRoomService;

    @Scheduled(fixedRate = 1000)
    public void greeting() throws InterruptedException, JsonProcessingException {
        Thread.sleep(1000); // simulated delay
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(gameRoomService.getAllGameRoom());
        gameRoomService.removeGameRoom();
        this.template.convertAndSend("/topic/gamerooms", gameRoomService.getAllGameRoom());
    }

}