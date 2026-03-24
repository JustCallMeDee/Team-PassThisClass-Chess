package game;

import java.util.Scanner;

import board.Board;
import utils.Color;
import utils.Position;
import utils.Utils;

public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Color currentTurn;

    public Game() {
        board = new Board();
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        currentTurn = Color.WHITE;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to OOP Console Chess!");
        System.out.println("Enter moves in the format: E2 E4");
        System.out.println("Type EXIT (all caps) to quit/terminate.");

        while (running){
            board.display();
            System.out.println(currentTurn + "'s turn.");
            System.out.print("Enter your move: ");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")){
                running = false;
                System.out.println("Game end.");
                break;
            }

            if (!Utils.isValidMoveFormat(input)){
                System.out.println("Invalid input format. Retry in format: E2 E4");
                continue;
            }

            String[] parts = input.toUpperCase().split("\\s+");
            Position from = Utils.parsePosition(parts[0]);
            Position to = Utils.parsePosition(parts[1]);

            if (from == null || to == null){
                System.out.println("Invalid board position.");
                continue;
            }
            boolean moved = board.movePiece(from, to, currentTurn);

            if (moved){
                currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
            }
        }
        scanner.close();
    }
}