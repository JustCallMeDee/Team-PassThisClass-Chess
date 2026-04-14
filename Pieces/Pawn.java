package Pieces;

import java.util.ArrayList;
import Player.Player;
import Utils.Color;
import Board.Position;
/**
 * Represents a pawn piece from a chess board
 * 
 * @author Dee Quinn
 * @see {@link Piece}
 */
public class Pawn extends Piece{
    /**
     * Tracks if the piece has moved or not
     */
    private boolean hasMoved = false;

    public Pawn(Player player, Position pos) {
        super(player, pos);
        //TODO Auto-generated constructor stub
    }

    /**
     * Gets if this piece has moved or not
     * @return If this piece has moved or not
     */
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
        Position check;

        //Check color, and adjust way to move based on it
        int moveModifier = getColor() == Color.WHITE ? -1 : 1;
        try{
            check = new Position(this.getPosition().getRow() + moveModifier, this.getPosition().getColumn());
            if(getPlayer().findPieceAt(check) != null){
                throw new IllegalArgumentException();
            }
            //TODO: Check if this leaves them in check
            positions.add(check);
        }
        catch (IllegalArgumentException e){
            //yup
        }

        if(!hasMoved){
            try{
                check = new Position(this.getPosition().getRow() + (2 * moveModifier), this.getPosition().getColumn());
                if(getPlayer().findPieceAt(check) != null){
                    throw new IllegalArgumentException();
                }                
                //TODO: Check if this leaves them in check
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
        //boolean pieceLeft = true, pieceRight = false; 
        //Since a piece already occupies the spaces we are checking, only need to check if king is in check
        try {
            check = new Position(this.getPosition().getRow() + moveModifier, (char)(this.getPosition().getColumn() + 1));
            System.out.println("[" + check.getColumn() + check.getRow() + "]");
            Piece checkPiece = getPlayer().getBoard().getPiece(check);
            if(checkPiece != null && checkPiece.getColor() != getColor()){
                //TODO: Check if in check
                positions.add(check);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            check = new Position(this.getPosition().getRow() + moveModifier, (char)(this.getPosition().getColumn() - 1));
            System.out.println("[" + check.getColumn() + check.getRow() + "]");
            Piece checkPiece = getPlayer().getBoard().getPiece(check);
            if(checkPiece != null && checkPiece.getColor() != getColor()){
                //TODO: Check if in check
                positions.add(check);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return positions;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wP";
        }
        
        return "bP";
    }
}
