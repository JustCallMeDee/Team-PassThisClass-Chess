package Board;

import java.io.Serializable;
import java.util.List;
import Utils.Color;
import Pieces.*;
import Player.Player;

public class Board implements Serializable{
    private static final long serialVersionUID = 1L;


    private Position[][] board = new Position[8][8];
    private Player whitePlayer;
    private Player blackPlayer;

    public Board(){
        whitePlayer = new Player(Color.WHITE);
        whitePlayer.setBoard(this);
        blackPlayer = new Player(Color.BLACK);
        blackPlayer.setBoard(this);
        initializeBoard();
    }

    public void initializeBoard() {

    for (int col = 1; col <= 8; col++) {
        whitePlayer.addPiece(new Pawn(whitePlayer, new Position(7, col)));
        blackPlayer.addPiece(new Pawn(blackPlayer, new Position(2, col)));
    }
        whitePlayer.addPiece(new Rook(whitePlayer, new Position(8, 1)));
        whitePlayer.addPiece(new Rook(whitePlayer, new Position(8, 8)));
        blackPlayer.addPiece(new Rook(blackPlayer, new Position(1, 1)));
        blackPlayer.addPiece(new Rook(blackPlayer, new Position(1, 8)));

        whitePlayer.addPiece(new Knight(whitePlayer, new Position(8, 2)));
        whitePlayer.addPiece(new Knight(whitePlayer, new Position(8, 7)));
        blackPlayer.addPiece(new Knight(blackPlayer, new Position(1, 2)));
        blackPlayer.addPiece(new Knight(blackPlayer, new Position(1, 7)));

        whitePlayer.addPiece(new Bishop(whitePlayer, new Position(8, 3)));
        whitePlayer.addPiece(new Bishop(whitePlayer, new Position(8, 6)));
        blackPlayer.addPiece(new Bishop(blackPlayer, new Position(1, 3)));
        blackPlayer.addPiece(new Bishop(blackPlayer, new Position(1, 6)));

        whitePlayer.addPiece(new Queen(whitePlayer, new Position(8, 4)));
        blackPlayer.addPiece(new Queen(blackPlayer, new Position(1, 4)));

        whitePlayer.addPiece(new King(whitePlayer, new Position(8, 5)));
        blackPlayer.addPiece(new King(blackPlayer, new Position(1, 5)));

        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++){
                board[i-1][j-1] = new Position(i, j);
            }
        }
    }   
    
    public Player getPlayer(Color color){
        if(color == Color.WHITE){
            return getWhitePlayer();
        } else {
            return getBlackPlayer();
        }
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Piece getPiece(Position pos) {
        for (Piece p : whitePlayer.getPieces()){
            if (p.getPosition().equals(pos)) return p;
        }
        for (Piece p : blackPlayer.getPieces()){
            if (p.getPosition().equals(pos)) return p;
        }
        return null;
    }


    public boolean movePiece(Position start, Position end) {
        Piece piece = getPiece(start);

        if (piece == null) return false;

        if (!piece.possibleMoves().contains(end)){
            return false;
        }

        Piece target = getPiece(end);

        if (target != null) {
            if (target.getColor() == Color.WHITE){
                whitePlayer.capturePiece(end);
            } else {
                blackPlayer.capturePiece(end);
            }
        }

        piece.move(end);
        return true;
    }

    private King findKing(Color color) {
        Player player = getPlayer(color);

        for (Piece piece : player.getPieces()) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }

        return null;
    }

    private boolean canAttackSquare(Piece piece, Position target) {
        List<Position> moves = piece.possibleMoves();
        if (moves == null) {
            return false;
        }

        for (Position move : moves) {
            if (move.equals(target)) {
                return true;
            }
        }

        return false;
    }

    public boolean isCheck() {
        return isCheck(Color.WHITE) || isCheck(Color.BLACK);
    }

    public boolean isCheck(Color color) {
        King king = findKing(color);

        if (king == null) {
            return false;
        }

        Position kingPosition = king.getPosition();
        Player opponent = (color == Color.WHITE) ? blackPlayer : whitePlayer;

        for (Piece piece : opponent.getPieces()) {
            if (canAttackSquare(piece, kingPosition)) {
                return true;
            }
        }

        return false;
    }

    public boolean isCheck(Position moveStart, Position moveEnd) {
        return false;
        /*Piece movingPiece = getPiece(moveStart);
        if (movingPiece == null) {
            return false;
        }

        Piece capturedPiece = getPiece(moveEnd);
        Position originalPosition = movingPiece.getPosition();

        if (capturedPiece != null) {
            if (capturedPiece.getColor() == Color.WHITE) {
                whitePlayer.capturePiece(moveEnd);
            } else {
                blackPlayer.capturePiece(moveEnd);
            }
        }

        if (!movingPiece.move(moveEnd)) return false;
        boolean result = isCheck(movingPiece.getColor());
        movingPiece.move(originalPosition);

        if (capturedPiece != null) {
            if (capturedPiece.getColor() == Color.WHITE) {
                whitePlayer.addPiece(capturedPiece);
            } else {
                blackPlayer.addPiece(capturedPiece);
            }
        }

        return result;
        */
    }

    public boolean isCheckmate() {
        return isCheckmate(Color.WHITE) || isCheckmate(Color.BLACK);
    }

    public boolean isCheckmate(Color color) {
        if (!isCheck(color)) {
            return false;
        }

        Player player = getPlayer(color);

        for (Piece piece : player.getPieces()) {
            List<Position> moves = piece.possibleMoves();

            if (moves == null) {
                continue;
            }

            //Position start = piece.getPosition();

            for (Position end : moves) {
                Piece capturedPiece = getPiece(end);
                Position originalPosition = piece.getPosition();

                if (capturedPiece != null && capturedPiece.getColor() == color) {
                    continue;
                }

                if (capturedPiece != null) {
                    if (capturedPiece.getColor() == Color.WHITE) {
                        whitePlayer.capturePiece(end);
                    } else {
                        blackPlayer.capturePiece(end);
                    }
                }

                piece.move(end);
                boolean stillInCheck = isCheck(color);
                piece.move(originalPosition);

                if (capturedPiece != null) {
                    if (capturedPiece.getColor() == Color.WHITE) {
                        whitePlayer.addPiece(capturedPiece);
                    } else {
                        blackPlayer.addPiece(capturedPiece);
                    }
                }

                if (!stillInCheck) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  A  B  C  D  E  F  G  H\n");

        for (int row = 0; row < 8; row++) {
            int displayRow = 8 - row;
            sb.append(displayRow + " ");

            for (int col = 0; col < 8; col++) {
                //System.out.print(row + " " + col);
                Position pos = board[row][col];
                //System.out.print("AH");
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
