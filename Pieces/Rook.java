package Pieces;

import java.util.ArrayList;

import Utils.Color;
import Player.Player;
import Board.Position;
/**
 * Represents a rook piece from a chess board
 * 
 * @author Dee Quinn
 * @see {@link Piece}
 */
public class Rook extends Piece{
    private boolean hasMoved = false;

    public Rook(Player player, Position pos) {
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
        Position check;
        boolean capture = false;
        //Check all positions to the right, stopping when an error is thrown, or a piece is captured
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow() + i, (char)(this.getPosition().getColumn()));
                if(getPlayer().findPieceAt(check) != null){
                    throw new IllegalArgumentException();
                }            
                //Todo: If leaves in check, continue loop
                //Todo: Check if there is a capture, set capture to that
                positions.add(check);
                if(capture){
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }

        //Checks all positions to the left
        capture = false;
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow() + i, (char)(this.getPosition().getColumn()));
                if(getPlayer().findPieceAt(check) != null){
                    throw new IllegalArgumentException();
                }            
                //Todo: If leaves in check, continue loop
                //Todo: Check if there is a capture, set capture to that
                positions.add(check);
                if(capture){
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }

        //Check all positions north
        capture = false;
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow(), (char)(this.getPosition().getColumn() + i));
                if(getPlayer().findPieceAt(check) != null){
                    throw new IllegalArgumentException();
                }            
                //Todo: If leaves in check, continue loop
                //Todo: Check if there is a capture, set capture to that
                positions.add(check);
                if(capture){
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }

        //Check all positions south
        capture = false;
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow(), (char)(this.getPosition().getColumn() - i));
                if(getPlayer().findPieceAt(check) != null){
                    throw new IllegalArgumentException();
                }            
                //Todo: If leaves in check, continue loop
                //Todo: Check if there is a capture, set capture to that
                positions.add(check);
                if(capture){
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }


        return positions;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wR";
        }
        
        return "bR";
    }
    
}
