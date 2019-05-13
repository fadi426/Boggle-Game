package main.helper;

import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.util.regex.Pattern;


// Declare the class
public class DictonaryChecker
{

    public static String REGEX_FIND_WORD="(?i).*?\\b%s\\b.*?";

    public static boolean readInDictionaryWords(String word) throws IOException
    {
        Boolean wordExists = false;
        File dictionaryFile = new File("C:\\Users\\Fadi\\Documents\\GitHub\\Boggle-Game\\Back-end\\BoggleGame\\src\\main\\resources\\Dutch.dic");
        if( ! dictionaryFile.exists()) {
            System.out.println("*** Error *** \n" +
                    "Your dictionary file has the wrong name or is " +
                    "in the wrong directory.  \n" +
                    "Aborting program...\n\n");
            System.exit( -1);    // Terminate the program
        }
        Scanner scan = new Scanner( dictionaryFile);

        while(scan.hasNextLine()){
            String regex=String.format(REGEX_FIND_WORD, Pattern.quote(word));
            String str = scan.findInLine(regex);
            if(str != null){
                return true;
            }
            scan.nextLine();
        }
        scan.close();
        return false;
    }
}
