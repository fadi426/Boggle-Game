package main.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoardCreator {
    private List<String> diceList = new ArrayList<>();

    public BoardCreator(){
        diceList = Arrays.asList(
                "QBZJXK",
                "TOUOTO",
                "OVWRGR",
                "AAAFSR",
                "QUMEEG",
                "HHLRDO",
                "NHDTHO",
                "LHNROD",
                "AFAISR",
                "YIFASR",
                "TELPCI",
                "SSNSEU",
                "RIYPRH",
                "DORDLN",
                "CCWNST",
                "TTOTEM",
                "SCTIEP",
                "EANDNN",
                "MNNEAG",
                "UOTOWN",
                "AEAEEE",
                "YIFPSR",
                "EEEEMA",
                "ITITIE",
                "ETILIC"
        );
    }
    public ArrayList<Character> getRandomDiceList() {
        // create a random letterList
        ArrayList<Character> dice = new ArrayList<>();
        Random rand = new Random();
        for (String die : diceList){
            dice.add(die.charAt(rand.nextInt(5)));
        }
        return dice;
    }
}
