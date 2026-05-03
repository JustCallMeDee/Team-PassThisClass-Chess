package Player;

import Utils.Color;

import Pieces.*;
import Board.*;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Represents a player in a chess game. 
 * 
 * @author Dee Quinn
 * @see {@link Pieces.Piece}
 * @see {@link java.util.ArrayList}
 */
public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * The current color of the player
     */
    private Color color;
    /**
     * A list of all pieces currently owned by the player
     */

    ArrayList<Piece> pieces;
    Board board;

    public Player(Color color, ArrayList<Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public Player(Color color){
        this.color = color;
        //pieces = createStandardPieceSetup(color);
        pieces = new ArrayList<Piece>();
    }

    /**
     * Gets the color of the player
     * @return The color of the player
     */
    public Color getColor(){
        return color;
    }

    /**
     * Gets the array list of pieces
     * @return The array list of pieces
     */
    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    /**
     * Gets the board the player is on
     * @return The board the player is on
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Sets the board the player is on
     */
    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * Finds the piece at a position controlled by this player
     * @param pos the position the piece should be at
     * @return The piece, or null if none are found
     */
    public Piece findPieceAt(Position pos){
        for(Piece p : pieces){
            if(p.getPosition().equals(pos)){
                return p;
            }
        }
        return null;
    }

    /**
     * Attempts to remove a piece at a position.
     * @param pos The position that holds the piece that needs to be removed
     */
    public void capturePiece(Position pos){
        Piece p = findPieceAt(pos);
        if(p != null){
            pieces.remove(pieces.indexOf(p));
        }
    }

    /**
     * Attempts to move a piece from one position to another. 
     * @param startPos The position that the piece being moved is on
     * @param endPos The position that the piece is to end up on
     * @return True if there is a piece at startPos and the move was successful. Otherwise, the method returns false.
     */
    public boolean makeMove(Position startPos, Position endPos){
        Piece p = findPieceAt(startPos);
        if(p != null){
            //System.out.println("Found piece");
            return p.move(endPos);
        }
        else{
            //System.out.println("Didn't find piece");
            return false;
        }
    }
    
    /**
     * Adds a piece to the list of pieces owned by this player.
     * @param piece The piece to be added to this player
     */
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }
}
