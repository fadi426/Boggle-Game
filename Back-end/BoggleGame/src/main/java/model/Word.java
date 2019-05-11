package model;

public class Word {

    private String word;
    private boolean isValid;
    private int score;

    public Word() {

    }

    public Word(String word, boolean isValid, int score){
        this.word = word;
        this.isValid = isValid;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public int getScore() {
        return score;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
