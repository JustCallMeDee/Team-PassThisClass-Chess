//package here

//imports here
import Game.Game;
import GUI.ChessGUI;

public class ChessMain{
    public static void main(String[] args){
        Game game = new Game();
        new ChessGUI();
        game.play();
        System.out.println("Chess!");
    }
}