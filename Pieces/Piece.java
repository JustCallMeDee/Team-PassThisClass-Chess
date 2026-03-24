package Pieces;

import Board.Position;
import Player.Player;
import Player.Color;
import java.util.ArrayList;

/**
 * An abstract class that represents a chess piece.
 * 
 * @author Dee Quinn
 * @see {@link Board.Position}
 */
public abstract class Piece {
    
    private Player player;

    /**
     * Tracks the position of the piece on a board.
     * @see {@link Board.Position}
     */
    private Position pos;

    public Piece(Player player, Position pos){
        this.player = player;
        this.pos = pos;
    }

    public Player getPlayer(){
        return player;
    }

    public Color getColor(){
        return player.getColor();
    }

    /**
     * Gets the position of the piece
     * @return the position of this piece
     * @see {@link Board.Position}
     */
    public Position getPosition(){
        return pos;
    }

    /**
     * Attempts to move the piece to a new position. If unsuccessful, returns false. Otherwise,
     * changes the position of the piece and returns true.
     * @param newPos The new position you want to move the piece to
     * @return If the move was successful or not
     * @see {@link #possibleMoves()}
     * @see {@link Board.Position}
     */
    public boolean move(Position newPos){
        if(possibleMoves().contains(newPos)){
            pos = newPos;
            return true;
        }
        return false;
    }
    
    /**
     * Calculates all the possible moves the piece can go 
     * @return an ArrayList of all valid positions
     * @see {@link Board.Position}
     * @see {@link java.util.ArrayList}
     */
    public abstract ArrayList<Position> possibleMoves();

}
