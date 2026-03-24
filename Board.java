package Board;

import Pieces.*;
import Pieces.Piece;
import Player.*;

public class Board {
    private Position[][] board;
    private Player whitePlayer;
    private Player blackPlayer;
    
    public void initializeBoard() {

    for (int col = 0; col < 8; col++) {
        whitePlayer.addPiece(new Pawn(Color.White, new Position(6, col)));
        blackPlayer.addPiece(new Pawn(Color.Black, new Position(1, col)));
    }

    whitePlayer.addPiece(new Rook(Color.White, new Position(7, 0)));
    whitePlayer.addPiece(new Rook(Color.White, new Position(7, 7)));
    blackPlayer.addPiece(new Rook(Color.Black, new Position(0, 0)));
    blackPlayer.addPiece(new Rook(Color.Black, new Position(0, 7)));

    whitePlayer.addPiece(new Knight(Color.White, new Position(7, 1)));
    whitePlayer.addPiece(new Knight(Color.White, new Position(7, 6)));
    blackPlayer.addPiece(new Knight(Color.Black, new Position(0, 1)));
    blackPlayer.addPiece(new Knight(Color.Black, new Position(0, 6)));

    whitePlayer.addPiece(new Bishop(Color.White, new Position(7, 2)));
    whitePlayer.addPiece(new Bishop(Color.White, new Position(7, 5)));
    blackPlayer.addPiece(new Bishop(Color.Black, new Position(0, 2)));
    blackPlayer.addPiece(new Bishop(Color.Black, new Position(0, 5)));

    whitePlayer.addPiece(new Queen(Color.White, new Position(7, 3)));
    blackPlayer.addPiece(new Queen(Color.Black, new Position(0, 3)));

    whitePlayer.addPiece(new King(Color.White, new Position(7, 4)));
    blackPlayer.addPiece(new King(Color.Black, new Position(0, 4)));
}
    
    public Board(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = new Position[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                board[i][j] = new Position(i, j);
            }
        }
    }
    public Board(){
        this(new Player(Color.White), new Player(Color.Black));
        initializeBoard();
    }

    public Player getPlayer(Color color){
        if(color == Color.White){
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

    if(piece.getColor() == Color.White){
        whitePlayer.makeMove(start, end);
        if(target != null && target.getColor() == Color.Black){
            blackPlayer.capturePiece(end);
        }
    } else {
        blackPlayer.makeMove(start, end);
        if(target != null && target.getColor() == Color.White){
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