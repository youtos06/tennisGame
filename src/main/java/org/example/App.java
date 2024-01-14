package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            IGame tennisGame = new TennisGame("ABABAA");
            System.out.println(tennisGame.runTheGame());
        }catch (InvalidGameException invalidGameException){
            System.out.println(invalidGameException.getMessage());
        }

    }
}

interface IGame{
    String runTheGame();

    void validator();

    String play();
}


class TennisGame implements IGame{
    String script;
    Player player1;
    Player player2;
    boolean isInDeuce;

    boolean isOver;

    public TennisGame(String script){
        this.script = script;
        this.player1 = new Player('A');
        this.player2 = new Player('B');
        this.isInDeuce = false;
        this.isOver = false;
    }

    public String runTheGame(){
        this.validator();
        return this.play();
    }

    public void validator(){
        for(char c: this.script.toCharArray()){
            if(c !='A' && c != 'B') throw new InvalidGameException("Invalid game script : non supported characters");
        }
    }

    private String addScore(Player player){
        switch (player.score){
            case 0 -> player.score = 15 ;
            case 15 -> player.score = 30 ;
            case 30 -> player.score = 40 ;
            default -> this.isOver = true;
        }
        if(this.player2.score == 40 && this.player1.score == 40 ) this.isInDeuce = true;
        return printGameState(player);
    }

    private String printGameState(Player player){
        if(this.isOver) return String.format("Player %s win the game", player.name);
        return String.format("Player A : %d / Player B : %d \n", player1.score, player2.score);
    }

    public String play(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < this.script.length() ; i++){
            if(isOver) throw new InvalidGameException("Invalid game script : number of plays surpass game");

            if(isInDeuce){
                playInDeuceMode(i, builder);
            }
            else{
                if(script.charAt(i) == player1.name) builder.append(this.addScore(player1));
                else builder.append(this.addScore(player2));
            }

        }
        if(!isOver) throw new InvalidGameException("Invalid game script : number of plays don't end game");
        return builder.toString();
    }

    private void playInDeuceMode(int i, StringBuilder builder) {
        if(script.charAt(i) == player1.name){
            if (player1.advantage) {
                this.isOver = true;
                builder.append(printGameState(player1));
            }else{
                if(player2.advantage) player2.advantage = false;
                else player1.advantage = true;
            }
        }else {
            if (player2.advantage) {
                this.isOver = true;
                builder.append(printGameState(player2));
            }else{
                if(player1.advantage) player1.advantage = false;
                else player2.advantage = true;
            }
        }
    }
}

class InvalidGameException extends RuntimeException{
    public InvalidGameException(String message) {
        super(message);
    }
}


class Player{

    // can be switched for a string for larger names but since our inputs are just B and A, this is enough...
    char name;
    int score ;
    boolean advantage ;

    public Player(char name){
        this.name = name;
        this.score = 0 ;
        this.advantage = false;
    }
}