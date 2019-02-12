package main;

import model.ValidWord;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoggleController {

    @Autowired
    private BoggleService boggleService;

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/words")
    public List<Word> getAllArticles(){
        return boggleService.getAllWords();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/word/{word}")
    public Word getWord(@PathVariable String word){
        return boggleService.getWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "/words")
    public String addWords(@RequestBody Word word){
        System.out.println(word.getWord());
        return boggleService.checkWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.DELETE, value = "words/{word}")
    public void deleteWord(@PathVariable String word){
        boggleService.deleteWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(method = RequestMethod.POST, value = "test1")
    public void test1(@RequestBody ValidWord word){
        boggleService.addValidWord(word);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/test1")
    public List<ValidWord> getAllValidWords(){
        return boggleService.getAllValidWords();
    }
}
