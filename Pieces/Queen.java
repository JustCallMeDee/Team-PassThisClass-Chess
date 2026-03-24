package Pieces;

import java.util.ArrayList;

import Board.Position;
import Player.Color;
import Player.Player;

public class Queen extends Piece{

    public Queen(Player player, Position pos) {
        super(player, pos);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> positions = new ArrayList<Position>();
        Position check;
        boolean capture = false;
        //Check all horizontal moves to the right. When one position is invalid, stop the loop 
        for(int i = this.getPosition().getRow() + 1; i <= this.getPosition().getRow() + 8; i++){
            try {
                check = new Position(i, this.getPosition().getColumn());
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
        
        capture = false;
        //Next loops is for horizontal to the left, then vertical up and down
        for(int i = this.getPosition().getRow() - 1; i >= this.getPosition().getRow() - 8; i--){
            try {
                check = new Position(i, this.getPosition().getColumn());
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

        capture = false;
        for(int j = this.getPosition().getColumn() + 1; j <= this.getPosition().getColumn() + 8; j++){
            try {
                check = new Position(this.getPosition().getRow(), j);
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

        capture = false;
        for(int j = this.getPosition().getColumn() - 1; j >= this.getPosition().getColumn() - 8; j--){
            try {
                check = new Position(this.getPosition().getRow(), j);
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

        capture = false;
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
        if(getColor() == Color.White){
            return "wQ";
        }
        
        return "bQ";
    }
}
