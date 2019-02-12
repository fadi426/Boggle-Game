package main.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ValidWord;
import model.Word;

public class ValidWordJsonBuilder {

    public static String ObjectToJson(ValidWord word) {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(word);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
        return json;
    }
}
