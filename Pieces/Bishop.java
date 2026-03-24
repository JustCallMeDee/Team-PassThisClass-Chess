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
        //TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> positions = new ArrayList<Position>();
        Position check;
        boolean capture = false;
        //Four loops, checking each diagonal 8 spaces away. 
        //If a space in invalid or a piece is encountered, break the loop early
        //North-east
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow() + i, this.getPosition().getColumn() + i);
                if(getPlayer().findPieceAt(check) == null){
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

        //North-west
        capture = false;
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow() + i, this.getPosition().getColumn() - i);
                if(getPlayer().findPieceAt(check) == null){
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

        //South-east
        capture = false;
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow() - i, this.getPosition().getColumn() + i);
                if(getPlayer().findPieceAt(check) == null){
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

        //South-west
        capture = false;
        for(int i = 1; i <= 8; i++){
            try {
                check = new Position(this.getPosition().getRow() - i, this.getPosition().getColumn() - i);
                if(getPlayer().findPieceAt(check) == null){
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
            return "wB";
        }
        
        return "bB";
    }
}
