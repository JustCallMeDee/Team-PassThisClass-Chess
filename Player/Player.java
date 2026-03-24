package Player;

import Pieces.*;
import Board.Position;
import java.util.ArrayList;

public class Player {
    Color color;
    King king;
    ArrayList<Piece> pieces;
    //Current board

    public Player(Color color, King king, ArrayList<Piece> pieces) {
        this.color = color;
        this.king = king;
        this.pieces = pieces;
    }

    public Player (Color color, King king){
        this(color, king, new ArrayList<Piece>());
    }

    public Player (Color color, ArrayList<Piece> pieces){
        this(color, (King)pieces.get(0), pieces);
    }

    public Player(Color color){
        this.color = color;
        pieces = createStandardPieceSetup(color);
        king = (King)pieces.get(0);
    }

    private ArrayList<Piece> createStandardPieceSetup(Color color) {
        ArrayList<Piece> board = new ArrayList<Piece>();
        if(color == Color.White){
            board.add(new King(this, new Position(1, 'E')));
            board.add(new Queen(this, new Position(1, 'D')));
            board.add(new Bishop(this, new Position(1, 'C')));
            board.add(new Bishop(this, new Position(1, 'F')));
            board.add(new Knight(this, new Position(1, 'B')));
            board.add(new Knight(this, new Position(1, 'G')));
            board.add(new Rook(this, new Position(1, 'A')));
            board.add(new Rook(this, new Position(1, 'H')));
            for(char i = 'A'; i <= 'H'; i++){
                board.add(new Pawn(this, new Position(2, i)));
            }
        }
        else{
            board.add(new King(this, new Position(8, 'E')));
            board.add(new Queen(this, new Position(8, 'D')));
            board.add(new Bishop(this, new Position(8, 'C')));
            board.add(new Bishop(this, new Position(8, 'F')));
            board.add(new Knight(this, new Position(8, 'B')));
            board.add(new Knight(this, new Position(8, 'G')));
            board.add(new Rook(this, new Position(8, 'A')));
            board.add(new Rook(this, new Position(8, 'H')));
            for(char i = 'A'; i <= 'H'; i++){
                board.add(new Pawn(this, new Position(7, i)));
            }
        }

        return board;
    }

    public Color getColor(){
        return color;
    }

    public King getKing(){
        return king;
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public void getBoard(){
        //Unimplemented
    }

    public void setBoard(){
        //Unimplemented
    }

    public Piece findPieceAt(Position pos){
        for(Piece p : pieces){
            if(p.getPosition().equals(pos)){
                return p;
            }
        }
        return null;
    }
    public void capturePiece(Position pos){
        Piece p = findPieceAt(pos);
        if(p != null){
            pieces.remove(pieces.indexOf(p));
        }
    }

    public boolean makeMove(Position startPos, Position endPos){
        Piece p = findPieceAt(startPos);
        if(p != null){
            return p.move(endPos);
        }
        else{
            return false;
        }
    }
}
