package Pieces;

import java.util.ArrayList;

import Board.Position;

public class Queen extends Piece{

    public Queen(Position pos) {
        super(pos);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> positions = new ArrayList<Position>();
        Position check;
        boolean capture = false;
        //Check all forizontal moves to the right. When one position is invalid, stop the loop 
        for(int i = this.getPosition().getRow() + 1; i <= this.getPosition().getRow() + 8; i++){
            try {
                check = new Position(i, this.getPosition().getColumn());
                //Check board state
                //Check if there is a capture
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
                //Check board state
                //Check if there is a capture
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
                //Check board state
                //Check if there is a capture
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
                //Check board state
                //Check if there is a capture
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
                //Check board state
                //Check if there is a capture, if so change capture to true
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
                //Check board state
                //Check if there is a capture, if so change capture to true
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
                //Check board state
                //Check if there is a capture, if so change capture to true
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
                //Check board state
                //Check if there is a capture, if so change capture to true
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
}
