package Player;

import Utils.Color;
import Pieces.*;
import Board.Position;
import java.util.ArrayList;

/**
 * Represents a player in a chess game. 
 * 
 * @author Dee Quinn
 * @see {@link Pieces.Piece}
 * @see {@link java.util.ArrayList}
 */
public class Player {
    /**
     * The current color of the player
     */
    Color color;
    /**
     * A list of all pieces currently owned by the player
     */
    ArrayList<Piece> pieces;
    //Current board

    public Player(Color color, ArrayList<Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public Player(Color color){
        this.color = color;
        pieces = createStandardPieceSetup(color);
    }

    /**
     * Creates an Array list of Piece objects in standard position
     * @param color The color of the pieces
     * @return An array list of pieces in standard positions
     */
    private ArrayList<Piece> createStandardPieceSetup(Color color) {
        ArrayList<Piece> board = new ArrayList<Piece>();
        if(color == Color.WHITE){
            board.add(new King(this, new Position(1, 'E')));
            board.add(new Queen(this, new Position(1, 'D')));
            board.add(new Bishop(this, new Position(1, 'C')));
            board.add(new Bishop(this, new Position(1, 'F')));
            board.add(new Knight(this, new Position(1, 'B')));
            board.add(new Knight(this, new Position(1, 'G')));
            board.add(new Rook(this, new Position(1, 'A')));
            board.add(new Rook(this, new Position(1, 'H')));
            for(char i = 'A'; i <= 'H'; i++){
                board.add(new Pawn(this, new Position(2, i)));
            }
        }
        else{
            board.add(new King(this, new Position(8, 'E')));
            board.add(new Queen(this, new Position(8, 'D')));
            board.add(new Bishop(this, new Position(8, 'C')));
            board.add(new Bishop(this, new Position(8, 'F')));
            board.add(new Knight(this, new Position(8, 'B')));
            board.add(new Knight(this, new Position(8, 'G')));
            board.add(new Rook(this, new Position(8, 'A')));
            board.add(new Rook(this, new Position(8, 'H')));
            for(char i = 'A'; i <= 'H'; i++){
                board.add(new Pawn(this, new Position(7, i)));
            }
        }

        return board;
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
    public void getBoard(){
        //Unimplemented
    }

    /**
     * Sets the board the player is on
     */
    public void setBoard(){
        //Unimplemented
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
            return p.move(endPos);
        }
        else{
            return false;
        }
    }
}
