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
        //TOD
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
        ArrayList<Position> moves = new ArrayList<>();
        int dir = (getColor() == Color.WHITE) ? -1 : 1;

        try {
            Position one = new Position(getPosition().getRow() + dir, getPosition().getColumn());

            if (getPlayer().getBoard().getPiece(one) == null) {

                if (!leavesKingInCheck(one)){
                    moves.add(one);
                }

                if (!hasMoved) {
                    Position two = new Position(getPosition().getRow() + 2 * dir, getPosition().getColumn());
                    
                    if (getPlayer().getBoard().getPiece(two) == null &&
                           !leavesKingInCheck(two)){
                        moves.add(two);
                    }
                }
            }
        } catch (IllegalArgumentException e) {}

        int[] cols = {1, -1};

    for (int c : cols){
            try {
                Position diag = new Position(
                    getPosition().getRow() + dir,
                    (char)(getPosition().getColumn() + c)
                );

                Piece p = getPlayer().getBoard().getPiece(diag);

                if (p != null && p.getColor() != getColor()
                        && !leavesKingInCheck(diag)) {
                    moves.add(diag);
                }

            } catch (IllegalArgumentException e) {}
        }

        return moves;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wP";
        }
        
        return "bP";
    }
}
