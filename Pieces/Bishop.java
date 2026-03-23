package Pieces;

import java.util.ArrayList;

import Board.Position;
import Player.Player;

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
