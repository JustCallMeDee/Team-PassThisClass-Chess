package Board;

import Pieces.*;
import Player.*;
import Utils.Color;
//import java.util.*;

public class Board {
    private Piece[][] board;
    private Player whitePlayer;
    private Player blackPlayer;

    public Board(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = new Piece[8][8];
    }

    public Board() {
        this(new Player(Color.WHITE), new Player(Color.BLACK));
        initializeBoard();
    }

    public void initializeBoard() {
        for (int col = 0; col < 8; col++) {
            Pawn whitePawn = new Pawn(whitePlayer, new Position(6, col));
            Pawn blackPawn = new Pawn(blackPlayer, new Position(1, col));
            placeAndAdd(whitePawn, whitePlayer);
            placeAndAdd(blackPawn, blackPlayer);
        }

        placeAndAdd(new Rook(whitePlayer, new Position(7, 0)), whitePlayer);
        placeAndAdd(new Rook(whitePlayer, new Position(7, 7)), whitePlayer);
        placeAndAdd(new Rook(blackPlayer, new Position(0, 0)), blackPlayer);
        placeAndAdd(new Rook(blackPlayer, new Position(0, 7)), blackPlayer);

        placeAndAdd(new Knight(whitePlayer, new Position(7, 1)), whitePlayer);
        placeAndAdd(new Knight(whitePlayer, new Position(7, 6)), whitePlayer);
        placeAndAdd(new Knight(whitePlayer, new Position(0, 1)), blackPlayer);
        placeAndAdd(new Knight(whitePlayer, new Position(0, 6)), blackPlayer);

        placeAndAdd(new Bishop(whitePlayer, new Position(7, 2)), whitePlayer);
        placeAndAdd(new Bishop(whitePlayer, new Position(7, 5)), whitePlayer);
        placeAndAdd(new Bishop(blackPlayer, new Position(0, 2)), blackPlayer);
        placeAndAdd(new Bishop(blackPlayer, new Position(0, 5)), blackPlayer);

        placeAndAdd(new Queen(whitePlayer, new Position(7, 3)), whitePlayer);
        placeAndAdd(new Queen(blackPlayer, new Position(0, 3)), blackPlayer);

        placeAndAdd(new King(whitePlayer, new Position(7, 4)), whitePlayer);
        placeAndAdd(new King(blackPlayer, new Position(0, 4)), blackPlayer);
    }

    private void placePiece(Piece piece) {
        Position pos = piece.getPosition();
        board[pos.getRow()][pos.getColumn()] = piece;
    }

    private void placeAndAdd(Piece piece, Player player) {
        placePiece(piece);
        piece.move(piece.getPosition());
        player.addPiece(piece);
    }

    public Player getPlayer(Color color) {
        return (color == Color.WHITE) ? whitePlayer : blackPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Piece getPiece(Position pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    public boolean movePiece(Position start, Position end) {
        Piece piece = getPiece(start);
        if (piece == null) return false;

        Piece target = getPiece(end);
        if (target != null && target.getColor() == piece.getColor()) return false;

        board[start.getRow()][start.getColumn()] = null;

        if (target != null) {
            if (target.getColor() == Color.WHITE) whitePlayer.capturePiece(end);
            else blackPlayer.capturePiece(end);
        }

        piece.move(end);
        board[end.getRow()][end.getColumn()] = piece;

        if (piece.getColor() == Color.WHITE) whitePlayer.makeMove(start, end);
        else blackPlayer.makeMove(start, end);

        return true;
    }

    public boolean isCheck(Color color) {
        return false;
    }

    public boolean isCheck(Position moveStart, Position moveEnd) {
        return false;
    }

    public boolean isCheckmate(Color color) {
        return false;
    }

    public void display() {
        System.out.println("  A  B  C  D  E  F  G  H");
        for (int row = 0; row < 8; row++) {
            int displayRow = 8 - row;
            System.out.print(displayRow + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) System.out.print(piece.toString() + " ");
                else System.out.print("## ");
            }
            System.out.println();
        }
    }
}