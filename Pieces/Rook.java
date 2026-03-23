package Pieces;

import java.util.ArrayList;
import Player.Player;
import Board.Position;

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
        //Check all forizontal moves to the right. When one position is invalid, stop the loop 
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


        return positions;
    }


    
}
