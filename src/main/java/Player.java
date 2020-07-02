import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private String playerName;
    private Integer playerPoints;
    private Integer numberOfLives;
    private ArrayList<String> playedWords;

    public Player() {
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Please enter your name: " );
        playerName = scanner.nextLine();
        playerPoints = 0;
        numberOfLives = 3;
        playedWords = new ArrayList<String>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(Integer playerPoints) {
        this.playerPoints = playerPoints;
    }

    public void addPlayerPoints(Integer playerPoints) {
        this.playerPoints += playerPoints;
    }

    public Integer getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(Integer numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public ArrayList<String> getPlayedWords() {
        return playedWords;
    }

    public void addPlayedWord(String playedWord) {
        this.playedWords.add( playedWord );
    }
}
