package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.service.GameRoomService;
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

    // push messages to the broker on topic /topic/gamerooms to update the gamerooms in the front-end
    // delete empty rooms
    @Scheduled(fixedRate = 2000)
    public void greeting() throws InterruptedException, JsonProcessingException {
        Thread.sleep(2000); // simulated delay
        ObjectMapper mapper = new ObjectMapper();
        gameRoomService.removeGameRoom();
        this.template.convertAndSend("/topic/gamerooms", gameRoomService.getAllGameRoom());
    }

}