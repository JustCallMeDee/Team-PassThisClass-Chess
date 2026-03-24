package Board;

import Pieces.*;
import Player.*;
import Utils.Color;
//import java.util.*;

public class Board {
    private Position[][] board;
    private Player whitePlayer;
    private Player blackPlayer;

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
        board[pos.getRow()][pos.getColumn()] = pos;
    }

    private void placeAndAdd(Piece piece, Player player) {
        placePiece(piece);
        piece.move(piece.getPosition());
        player.addPiece(piece);
    }

    public Player getPlayer(Color color){
        if(color == Color.WHITE){
            return getWhitePlayer();
        }
        else{
            return getBlackPlayer();
        }
    }

    public Player getWhitePlayer(){
        return whitePlayer;
    }

    public Player getBlackPlayer(){
        return blackPlayer;
    }

    public Piece getPiece(Position pos){
        Piece p = whitePlayer.findPieceAt(pos);
        if(p == null){
            p = blackPlayer.findPieceAt(pos);
        }
        return p;
    }

   public boolean movePiece(Position start, Position end){
      Piece piece = getPiece(start);
      
      if(piece == null){
            return false;
        }

        Piece target = getPiece(end);

    if(piece.getColor() == Color.WHITE){
        whitePlayer.makeMove(start, end);
        if(target != null && target.getColor() == Color.BLACK){
            blackPlayer.capturePiece(end);
        }
    } else {
        blackPlayer.makeMove(start, end);
        if(target != null && target.getColor() == Color.WHITE){
            whitePlayer.capturePiece(end);
        }
    }

    return true;
}

    public boolean isCheck(){
        return false;
    }

    public boolean isCheck(Position moveStart, Position moveEnd){
        return false;
    }

    public boolean isCheckmate(){
        if(!isCheck()){
            return false;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

    sb.append("  A  B  C  D  E  F  G  H\n");

    for (int row = 0; row < 8; row++) {
        int displayRow = 8 - row;
        sb.append(displayRow + " ");

        for (int col = 0; col < 8; col++) {
            Position pos = board[row][col];
            Piece piece = getPiece(pos);

            if (piece != null) {
                sb.append(piece.toString() + " ");
            } else {
                sb.append("## ");
            }
        }
        sb.append("\n");
    }

    return sb.toString();
}
}