package Pieces;

import Board.Position;
import java.util.ArrayList;

public abstract class Piece {
    //Figure out how we are doing Color
    private Position pos;

    public Piece(/*color,*/ Position pos){
        this.pos = pos;
    }

    public Piece(/*color,*/ int row, char column){
        this(/*color,*/ new Position(row, column));
    }

    public Piece(/*color,*/ int row, int column){
        this(/*color,*/ new Position(row, column));
    }

    public Position getPosition(){
        return pos;
    }

    public boolean move(Position newPos){
        if(possibleMoves().contains(newPos)){
            pos = newPos;
            return true;
        }
        return false;
    }
    
    public abstract ArrayList<Position> possibleMoves();

}
