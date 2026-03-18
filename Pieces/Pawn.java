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
        ArrayList<Position> positions = new ArrayList<Position>();

        //Check moving spaces first
        //+1/2 if white, -1/2 if black
        Position check;
        try{
            check = new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn());
            /*
            Unimplemented:
                Check that this will not land on the same space as a same-color piece
                or that it will leave the king in check. Likely leave this to the 
                Board class.
            */
            positions.add(check);
        }
        catch (IllegalArgumentException e){
            //yup
        }

        if(!hasMoved){
            try{
                check = new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn());
                /*
                Unimplemented:
                    Check that this will not land on the same space as a same-color piece
                    or that it will leave the king in check. Likely leave this to the 
                    Board class.
                */
                positions.add(check);
            }
            catch (IllegalArgumentException e){
                //yup
            }
        }

        //Now check if there is opposing pieces to the front diagonals
        //If so, add those
        //Unimplemented: Needs board to be done
        //DEBUG STATEMENTS
        boolean pieceLeft = true, pieceRight = false; 

        //Since a piece already occupies the spaces we are checking, only need to check if king is in check
        if(pieceLeft){
            check = new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 1);
            //Check if check
            positions.add(check);
        }

        if(pieceRight){
            check = new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 1);
            //Check if check
            positions.add(check);
        }


        return positions;
    }
    
}
