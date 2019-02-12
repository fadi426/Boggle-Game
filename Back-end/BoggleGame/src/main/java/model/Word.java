package model;

public class Word {
    private String word;
    private int score;
//    private boolean validWord;

    public Word() {

    }

    public Word(String word, int score){
        this.word = word;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public void setWord() {
        this.word = word;
    }

    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score = score;
    }

//    public boolean getValidWord(){
//        return this.validWord;
//    }
//
//    public void setValidWord() {
//        this.validWord = validWord;
//    }
}
