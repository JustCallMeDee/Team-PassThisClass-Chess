package Pieces;

import java.util.ArrayList;
import Board.Position;
import Utils.Color;
import Player.Player;
/**
 * Represents a knight piece from a chess board
 * 
 * @author Dee Quinn
 * @see {@link Piece}
 */

public class Knight extends Piece{

    public Knight(Player player, Position pos) {
        super(player, pos);
        //TOD
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> moves = new ArrayList<>();

        int[][] offsets = {
            {2,1},{2,-1},{-2,1},{-2,-1},
            {1,2},{1,-2},{-1,2},{-1,-2}
        };

        for (int[] o : offsets) {
            try {
                Position next = new Position(
                    getPosition().getRow() + o[0],
                    (char)(getPosition().getColumn() + o[1])
                );

                Piece piece = getPlayer().getBoard().getPiece(next);

                if ((piece == null || piece.getColor() != getColor())
                        && !leavesKingInCheck(next)) {
                    moves.add(next);
                }

            } catch (IllegalArgumentException e) {}
        }

        return moves;
    }
    
    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wN";
        }
        
        return "bN";
    }
}
