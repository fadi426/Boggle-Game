package main.service;

import model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {

    List<Player> playerList = new ArrayList<Player>(Arrays.asList());

    public void addPlayer(Player player) {
        playerList.add(player);
    }
}
