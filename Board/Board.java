package board;

import pieces.*;
import player.*;
import java.util.*;

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
            Pawn whitePawn = new Pawn(Color.WHITE, new Position(6, col));
            Pawn blackPawn = new Pawn(Color.BLACK, new Position(1, col));
            placeAndAdd(whitePawn, whitePlayer);
            placeAndAdd(blackPawn, blackPlayer);
        }

        placeAndAdd(new Rook(Color.WHITE, new Position(7, 0)), whitePlayer);
        placeAndAdd(new Rook(Color.WHITE, new Position(7, 7)), whitePlayer);
        placeAndAdd(new Rook(Color.BLACK, new Position(0, 0)), blackPlayer);
        placeAndAdd(new Rook(Color.BLACK, new Position(0, 7)), blackPlayer);

        placeAndAdd(new Knight(Color.WHITE, new Position(7, 1)), whitePlayer);
        placeAndAdd(new Knight(Color.WHITE, new Position(7, 6)), whitePlayer);
        placeAndAdd(new Knight(Color.BLACK, new Position(0, 1)), blackPlayer);
        placeAndAdd(new Knight(Color.BLACK, new Position(0, 6)), blackPlayer);

        placeAndAdd(new Bishop(Color.WHITE, new Position(7, 2)), whitePlayer);
        placeAndAdd(new Bishop(Color.WHITE, new Position(7, 5)), whitePlayer);
        placeAndAdd(new Bishop(Color.BLACK, new Position(0, 2)), blackPlayer);
        placeAndAdd(new Bishop(Color.BLACK, new Position(0, 5)), blackPlayer);

        placeAndAdd(new Queen(Color.WHITE, new Position(7, 3)), whitePlayer);
        placeAndAdd(new Queen(Color.BLACK, new Position(0, 3)), blackPlayer);

        placeAndAdd(new King(Color.WHITE, new Position(7, 4)), whitePlayer);
        placeAndAdd(new King(Color.BLACK, new Position(0, 4)), blackPlayer);
    }

    private void placePiece(Piece piece) {
        Position pos = piece.getPosition();
        board[pos.getRow()][pos.getCol()] = piece;
    }

    private void placeAndAdd(Piece piece, Player player) {
        placePiece(piece);
        piece.setPosition(piece.getPosition());
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
        return board[pos.getRow()][pos.getCol()];
    }

    public boolean movePiece(Position start, Position end) {
        Piece piece = getPiece(start);
        if (piece == null) return false;

        Piece target = getPiece(end);
        if (target != null && target.getColor() == piece.getColor()) return false;

        board[start.getRow()][start.getCol()] = null;

        if (target != null) {
            if (target.getColor() == Color.WHITE) whitePlayer.capturePiece(end);
            else blackPlayer.capturePiece(end);
        }

        piece.setPosition(end);
        board[end.getRow()][end.getCol()] = piece;

        if (piece.getColor() == Color.WHITE) whitePlayer.makeMove(start, end);
        else blackPlayer.makeMove(start, end);

        return true;
    }

    public boolean isCheck(Color color) {
        public boolean isCheck(Color color) {
        Player player = getPlayer(color);
        Piece king = player.getKing();   // You need to add getKing() to Player (see below)

        if (king == null) return false;
        return king.isInCheck(this);
    }
    
    public boolean isCheck(Position moveStart, Position moveEnd) {
        Piece piece = getPiece(moveStart);
        if (piece == null) return false;

        Piece captured = getPiece(moveEnd);

        board[moveStart.getRow()][moveStart.getCol()] = null;
        board[moveEnd.getRow()][moveEnd.getCol()] = piece;
        Position originalPos = piece.getPosition();
        piece.setPosition(moveEnd);

        Color opponentColor = (piece.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;
        boolean opponentInCheck = isCheck(opponentColor);

        board[moveStart.getRow()][moveStart.getCol()] = piece;
        board[moveEnd.getRow()][moveEnd.getCol()] = captured;
        piece.setPosition(originalPos);

        return opponentInCheck;
    }

    public boolean isCheckmate(Color color) {
         if (!isCheck(color)) return false;

        Player player = getPlayer(color);

        for (Piece piece : new ArrayList<>(player.getPieces())) {
            Position start = piece.getPosition();
            for (Position end : piece.getPossibleMoves(this)) {
                if (isMoveSafe(piece, start, end, color)) {
                    return false;
                }
            }
        }
        return true;
    }
      private boolean isMoveSafe(Piece piece, Position start, Position end, Color color) {
        Piece captured = board[end.getRow()][end.getCol()];

        board[start.getRow()][start.getCol()] = null;
        board[end.getRow()][end.getCol()] = piece;
        Position original = piece.getPosition();
        piece.setPosition(end);

        boolean stillInCheck = isCheck(color);

        board[start.getRow()][start.getCol()] = piece;
        board[end.getRow()][end.getCol()] = captured;
        piece.setPosition(original);

        return stillInCheck;
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
