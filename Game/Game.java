package Game;

import java.util.Scanner;

import Board.Board;
import Board.Position;
import GUI.ChessGUI;
import Utils.Color;
import Utils.Utils;

public class Game {
    private Board board;
    private ChessGUI gui;
    private Color currentTurn;

    public Game() {
        board = new Board();
        currentTurn = Color.WHITE;
        gui = new ChessGUI(board, currentTurn, this);
    }

    public void playGUI(){
        if (gui == null) {
            gui = new ChessGUI(board, currentTurn, this);
        }
        gui.setVisible(true);
    }

    public void setCurrentTurn(Color currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public void playConsole() {
        if (gui != null) {
            gui.setVisible(false);
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to OOP Console Chess!");
        System.out.println("Enter moves in the format: E2 E4");
        System.out.println("Type EXIT (all caps) to quit/terminate.");

        while (running) {
            System.out.println(board);
            System.out.println(currentTurn + "'s turn");
            System.out.print("Enter your move: ");

            String input = scanner.nextLine().trim();

            // Exit condition
            if (input.equalsIgnoreCase("EXIT")) {
                running = false;
                System.out.println("Game end.");
                break;
            }

            // Validate format
            if (!Utils.isValidMoveFormat(input)) {
                System.out.println("Invalid input format. Retry in format: E2 E4");
                continue;
            }

            // Parse input
            String[] parts = input.split(" ");
            Position from = Utils.parsePosition(parts[0]);
            Position to = Utils.parsePosition(parts[1]);

            if (from == null || to == null) {
                System.out.println("Invalid board position.");
                continue;
            }

            // Check correct player's turn
            if (board.getPiece(from) == null ||
                board.getPiece(from).getColor() != currentTurn) {

                System.out.println("That's not your piece!");
                continue;
            }

            // Move piece
            boolean moved = board.movePiece(from, to);

            if (moved) {
                // Switch turns
                currentTurn = (currentTurn == Color.WHITE)
                        ? Color.BLACK
                        : Color.WHITE;
                if (gui != null) {
                    gui.setCurrentTurn(currentTurn);
                }
            } else {
                System.out.println("Invalid move.");
            }
        }

        scanner.close();
    }
}