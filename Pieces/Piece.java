package Pieces;

import Board.Position;
import Player.Player;
import Utils.Color;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * An abstract class that represents a chess piece.
 * 
 * @author Dee Quinn
 * @see {@link Board.Position}
 */

public abstract class Piece implements Serializable{
    private static final long serialVersionUID = 1L;
    
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
        //System.out.print("|" + this.toString() + " at " + getPosition().getColumn() + getPosition().getRow() + " ");
        if(possibleMoves().contains(newPos)){
            pos = newPos;
            //System.out.println("moved to " + newPos.getColumn() + newPos.getRow());
            return true;
        }
        /*
        System.out.print("|||");
        for(Position p : possibleMoves()){
            System.out.print(Character.toString(p.getColumn()) + p.getRow() + " ");
        }
        System.out.print("|||");
        System.out.println("failed to move to " + newPos.getColumn() + newPos.getRow());
        */
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
