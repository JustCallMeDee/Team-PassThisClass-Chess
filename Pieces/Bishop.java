package Pieces;

import java.util.ArrayList;
import Board.Position;
import Utils.Color;
import Player.Player;
/**
 * Represents a bishop piece from a chess board
 * 
 * @author Dee Quinn
 * @see {@link Piece}
 */

public class Bishop extends Piece{

    public Bishop(Player player, Position pos) {
        super(player, pos);
        //TOD
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> moves = new ArrayList<>();

        int[] rowDir = {1, 1, -1, -1};
        int[] colDir = {1, -1, 1, -1};

        for (int d = 0; d < 4; d++) {
            int i = 1;

            while (true) {
                try {
                    Position next = new Position(
                        getPosition().getRow() + rowDir[d] * i,
                        (char)(getPosition().getColumn() + colDir[d] * i)
                    );

                    Piece piece = getPlayer().getBoard().getPiece(next);

                    if (piece == null) {
                        if (!leavesKingInCheck(next)) {
                            moves.add(next);
                        }
                    } else {
                        if (piece.getColor() != getColor() && !leavesKingInCheck(next)) {
                            moves.add(next);
                        }
                        break;
                    }

                    i++;
                } catch (IllegalArgumentException e) {
                    break;
                }
            }
        }

        return moves;
    }
    
    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wB";
        }
        
        return "bB";
    }
}
