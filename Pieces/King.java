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
        //TOD
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
        ArrayList<Position> moves = new ArrayList<>();

        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {

               if (r == 0 && c == 0) continue;

                try {
                    Position next = new Position(
                        getPosition().getRow() + r,
                        (char)(getPosition().getColumn() + c)
                    );

                    Piece piece = getPlayer().getBoard().getPiece(next);

                    if ((piece == null || piece.getColor() != getColor())
                         && !leavesKingInCheck(next)) {
                        moves.add(next);
                    }

             } catch (IllegalArgumentException e) {}
            }
        }

        return moves;
    }
    
    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wK";
        }
        
        return "bK";
    }
}
