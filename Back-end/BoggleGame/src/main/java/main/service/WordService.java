package main.service;

import main.helper.BoardCreator;
import main.helper.DictonaryChecker;
import main.helper.ValidWordJsonBuilder;
import model.Word;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class WordService {
    BoardCreator boardCreator = new BoardCreator();
    ArrayList<Integer> wordLength = new ArrayList<Integer>(Arrays.asList(0, 3, 4, 5, 6, 7,8));
    ArrayList<Integer> scorePoints = new ArrayList<Integer>(Arrays.asList(0, 1, 1, 2, 3, 5, 11));

    // check if the word exsists in the dictionary
    public Word checkWord(Word word) {
         try {
            boolean valid = DictonaryChecker.readInDictionaryWords(word.getWord());
             return sendValidityOutcome(word.getWord(),valid);
        } catch (IOException e) {
            System.out.println(e);
        }
        return new Word("Undefined", false, 0);
    }

    // send the outcome of the dictionary check
    public Word sendValidityOutcome(String word, boolean valid){
        Word validWord = new Word();
        validWord.setWord(word);
        validWord.setIsValid(valid);
        if (valid)
            validWord.setScore(rateWord(word));
        else
            validWord.setScore(0);

        String ValidityOutcomeJson = ValidWordJsonBuilder.ObjectToJson(validWord);
        System.out.println(ValidityOutcomeJson);
        return (validWord);
    }

    public ArrayList<Character> makeBoard() {
        return boardCreator.getRandomDiceList();
    }

    // rate the word by its length according to the boggle game rules
    public int rateWord(String word){
        int score = 0;
        for (int i = 0; i < wordLength.size(); i++){
            if (word.length() >= wordLength.get(i) && word.length() < wordLength.get(i+1) && i != wordLength.size()-1){
                score = scorePoints.get(i);
                break;
            }
            if (i >= wordLength.size()-1) {
                score = scorePoints.get(scorePoints.size() - 1);
                break;
            }
        }
        return score;
    }
}
