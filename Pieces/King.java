package Pieces;

import java.util.ArrayList;
import Board.Position;
import Utils.Color;
import Player.Player;
/**
 * Represents a king piece from a chess board
 * 
 * @author Dee Quinn
 * @see {@link Piece}
 */

public class King extends Piece{

    private boolean hasMoved = false;

    public King(Player player, Position pos) {
        super(player, pos);
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

        int row = this.getPosition().getRow();
        char column = this.getPosition().getColumn();

        for(int i = row-1; i<row+1; i++){
            for(char j = (char)(column-1); j < (char)(column+1); j++){
                try{
                    Position check = new Position(i, j);

                    //Check every condition that would invalidate the move.
                    //If it is invalid, skip this itteration of the loop  
                    if(check.getRow() == this.getPosition().getRow()
                            && check.getColumn() == this.getPosition().getColumn()){
                        throw new IllegalArgumentException();
                    }
                    if(getPlayer().findPieceAt(check) != null){
                        throw new IllegalArgumentException();
                    }    
                    //TODO: Check if in check
                    positions.add(check);
                }
                catch (IllegalArgumentException e){
                    continue;
                }
            }
        }
        
        return positions;
    }
    
    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wK";
        }
        
        return "bK";
    }
}
