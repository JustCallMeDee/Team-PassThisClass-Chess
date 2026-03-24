package Pieces;

import java.util.ArrayList;

import Board.Position;
import Player.Color;
import Player.Player;

public class Knight extends Piece{

    public Knight(Player player, Position pos) {
        super(player, pos);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> positions = new ArrayList<Position>();

        //Each position can be reached by a straight line 2 spaces horizatonally/vertically,
        //and then one space in the opposing orientation.
        //Therefore, we need make 2 checks for each 2 space line from the knight (4)
        //No real way for me to put this in a loop

        int row = this.getPosition().getRow() + 2;
        Position check;
        try{
            check = new Position(row, this.getPosition().getColumn() + 1);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }            
            //Todo: If leaves in check, continue loop
            positions.add(check);
        }
        catch(IllegalArgumentException e){
            //weh
        }

        try {
            check = new Position(row, this.getPosition().getColumn() - 1);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }            
            //Todo: If leaves in check, continue loop
            positions.add(check);
        } catch (IllegalArgumentException e) {
            // TODO: handle exception
        }

        row = this.getPosition().getRow() - 2;
        try {
            check = new Position(row, this.getPosition().getColumn() + 1);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }            
            //Todo: If leaves in check, continue loop
            positions.add(check);
        } catch (IllegalArgumentException e) {
            // TODO: handle exception
        }

        try {
            check = new Position(row, this.getPosition().getColumn() + 1);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }            
            //Todo: If leaves in check, continue loop
            positions.add(check);
        } catch (IllegalArgumentException e) {
            // TODO: handle exception
        }


        char column = (char)(this.getPosition().getColumn() + 2);
        try {
            check = new Position(this.getPosition().getRow() + 1, column);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }            
            //Todo: If leaves in check, continue loop
            positions.add(check);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            check = new Position(this.getPosition().getRow() - 1, column);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }            
            //Todo: If leaves in check, continue loop
            positions.add(check);
        } catch (Exception e) {
            // TODO: handle exception
        }

        column = (char)(this.getPosition().getColumn() - 2);
        try {
            check = new Position(this.getPosition().getRow() + 1, column);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }            
            //Todo: If leaves in check, continue loop
            positions.add(check);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            check = new Position(this.getPosition().getRow() - 1, column);
            if(getPlayer().findPieceAt(check) == null){
                throw new IllegalArgumentException();
            }
            //Todo: If leaves in check, continue loop
            positions.add(check);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return positions;
    }
    
    @Override
    public String toString(){
        if(getColor() == Color.White){
            return "wK";
        }
        
        return "bK";
    }
}
