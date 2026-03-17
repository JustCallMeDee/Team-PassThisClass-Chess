package Pieces;

import java.util.ArrayList;

import Board.Position;

public class Pawn extends Piece{
    private boolean hasMoved = false;

    public Pawn(Position pos) {
        super(pos);
        //TODO Auto-generated constructor stub
    }

    public boolean getHasMoved(){
        return hasMoved;
    }
    
    @Override
    public boolean move(Position newPos){
        boolean successful = super.move(newPos);
        if(successful){
            hasMoved = true;
        }
        return successful;
    }
    @Override
    public ArrayList<Position> possibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'possibleMoves'");
    }
    
}
