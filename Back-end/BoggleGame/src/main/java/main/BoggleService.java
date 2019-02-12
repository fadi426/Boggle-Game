package main;

import main.helper.DictonaryChecker;
import main.helper.ExternalPostRequest;
import main.helper.ValidWordJsonBuilder;
import model.ValidWord;
import model.Word;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BoggleService {

    List<Word> wordList = new ArrayList<Word>(Arrays.asList());
    List<ValidWord> validWordList = new ArrayList<ValidWord>(Arrays.asList());
    public List<Word> getAllWords(){
        return wordList;
    }

    public List<ValidWord> getAllValidWords(){
        return validWordList;
    }

    public Word getWord(String word){
        return wordList.stream().filter(t -> t.getWord().equals(word)).findFirst().get();
    }

    public String checkWord(Word word) {
        String response = "Undefined";
         try {
            boolean valid = DictonaryChecker.readInDictionaryWords(word.getWord());
             response = sendValidityOutcome(valid);
        } catch (IOException e) {
            System.out.println(e);
        }
        return response;
    }

    public String sendValidityOutcome(boolean valid){
        ValidWord validWord = new ValidWord();
        validWord.setValidWord(valid);

        String ValidityOutcomeJson = ValidWordJsonBuilder.ObjectToJson(validWord);
        System.out.println(ValidityOutcomeJson);
        //ExternalPostRequest.HttpPostValidWord(ValidityOutcomeJson);
        return (ValidityOutcomeJson);
    }

    public void addWord(Word word) {
        wordList.add(word);
    }

    public void addValidWord(ValidWord word) {
        validWordList.add(word);
    }

    public void deleteWord(String word) {
        wordList.removeIf(t ->t.getWord().equals(word));
    }
}
