import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    static Integer numberOfPlayers = 0;
    static ArrayList<Player> playersList = new ArrayList<Player>();
    static ArrayList<Player> loserPlayersList = new ArrayList<Player>();
    static ArrayList<String> wordsPlayedThisGame = new ArrayList<String>();
    static ArrayList<String> dictionary = LimitedVocabulary.getWords();
    static Integer roundId = 1;
    static Integer roundIdToPassFirstRound = 0;
    public static boolean gameIsActive = true;
    public static String firstLetterForWord;
    public static String theTwoLettersForNextWord;


    public static void startGame() {

        System.out.println( "The game is starting! Get your brains ready!" );

        firstLetterForWord = generateTheFirstLetterForGame();

        inputFirstWord();

        while (gameIsActive) {
            printActivePlayersInfo();

            for (Player player : playersList) {

                inputWordDuringGame( player );

                if (player.getNumberOfLives() < 1) {
                    System.out.println(
                            player.getPlayerName() + " you have 0 lives, you don't play anymore!" );
                    playersList.remove( player );
                    loserPlayersList.add( player );
                    break;
                }
            }
            roundId++;
            gameEndCondition();
        }
    }

    private static void printActivePlayersInfo() {
        System.out.println("Round " + roundId);
        String activePlayers = "";
        for (Player player : playersList) {
            activePlayers += player.getPlayerName() + ":" + player.getNumberOfLives() +":" + player.getPlayerPoints() + "-";
        }

        System.out.println("The active players are: " + activePlayers);
    }

    private static void inputWordDuringGame(Player player) {
        if (roundIdToPassFirstRound == 0) {
            roundIdToPassFirstRound += 1;
            return;
        }

        theTwoLettersForNextWord = wordsPlayedThisGame.get( wordsPlayedThisGame.size() - 1 )
                .substring(
                        (wordsPlayedThisGame.get( wordsPlayedThisGame.size() - 1 )).length()
                                - 1 ); //number of letters for next game word

        System.out.println(
                "Please enter a word starting with " + theTwoLettersForNextWord + ", "
                        + player.getPlayerName()
                        + ", or type 1 to skip this turn and lose  1 life!" );

        Scanner scanner = new Scanner( System.in );
        String playerWord = scanner.nextLine();

        if (playerWord.equals( "1" )) {
            player.setNumberOfLives( player.getNumberOfLives() - 1 );
        } else if (playerWord.length() >= 3 && dictionary.contains( playerWord ) && playerWord
                .startsWith( theTwoLettersForNextWord ) && !wordsPlayedThisGame
                .contains( playerWord )) {
            System.out.println( "Good word - " + playerWord + ", " + player.getPlayerName() );
            player.addPlayedWord( playerWord );
            wordsPlayedThisGame.add( playerWord );
            player.addPlayerPoints( playerWord.length() );
        } else {
            System.out.println( "The word is not valid, please try again: " );
            inputWordDuringGame( player );
        }
    }

    private static void inputFirstWord() {
        System.out.println(
                "Please enter a word starting with " + firstLetterForWord + ", " + playersList
                        .get( 0 ).getPlayerName() + ":");

        Scanner scanner = new Scanner( System.in );
        String playerWord = scanner.nextLine();
        if (playerWord.length() >= 3 && dictionary.contains( playerWord ) && playerWord
                .startsWith( firstLetterForWord )) {
            System.out.println(
                    "Good first word - " + playerWord + ", " + playersList.get( 0 ).getPlayerName()
                            + "!" );
            playersList.get( 0 ).addPlayedWord( playerWord );
            wordsPlayedThisGame.add( playerWord );
            playersList.get( 0 ).addPlayerPoints( playerWord.length() );
        } else {
            System.out.println( "The word is not valid, please try again: " );
            inputFirstWord();
        }
    }

    private static String generateTheFirstLetterForGame() {
        Random random = new Random();
        firstLetterForWord = String.valueOf( (char) (random.nextInt( 26 ) + 'a') );
        System.out.println( "The first letter for you to make a word is: " + firstLetterForWord + "!");
        return firstLetterForWord;
    }

    private static void gameEndCondition() {
        String gameWinner = "";
        if (Game.playersList.size() < 2) {
            System.out.println( "The game is over!" );

            loserPlayersList.add( playersList.get( 0 ) );
            ArrayList<Integer> playerPoints = new ArrayList<Integer>();

            for (Player player : loserPlayersList) {
                playerPoints.add( player.getPlayerPoints() );
            }

            for (int i = 0; i < playerPoints.size(); i++) {
                if (playerPoints.get( i ) < playerPoints.get( i + 1 )) {
                    playerPoints.remove( playerPoints.get( i ) );
                } else {
                    playerPoints.remove( playerPoints.get( i + 1 ) );
                }
            }

            for (Player player : loserPlayersList) {
                if (player.getPlayerPoints() == playerPoints.get( 0 )) {
                    gameWinner = player.getPlayerName();
                }
            }

            System.out.println( gameWinner + " is the winner with " + playerPoints.get( 0 ) + " points!");
            gameIsActive = false;
        }
    }

    public static void inputPlayerNumberAndName() {
        System.out.println( "Welcome to the WORDS game!" );
        System.out.println( "Please enter the number of players: " );

        numberOfPlayers = inputNumberOfPlayers();

        playerNameInput( numberOfPlayers, playersList );
    }

    private static Integer inputNumberOfPlayers() {
        Integer numberOfPlayers;
        Scanner scanner = new Scanner( System.in );
        numberOfPlayers = scanner.nextInt();
        return numberOfPlayers;
    }

    private static void playerNameInput(Integer numberOfPlayers, ArrayList<Player> playersList) {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            playersList.add( player );
        }
    }

    public static void printPlayerList(ArrayList<Player> playersList) {
        for (Player player : playersList) {
            System.out.print( player.getPlayerName() + "; " );
        }
        System.out.println();
    }
}


