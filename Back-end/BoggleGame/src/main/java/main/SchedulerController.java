package main;

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

    @Scheduled(fixedRate = 5000)
    public void greeting() throws InterruptedException {
        Thread.sleep(1000); // simulated delay
        System.out.println("scheduled");
        this.template.convertAndSend("/topic/greetings", new Greeting("Hello, TEEEEEEEEEEEEST"));
    }

}