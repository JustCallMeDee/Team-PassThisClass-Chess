package gui;

import javax.swing.*;
import java.awt.*;
import Pieces.Piece;

public class SquarePanel extends JButton {

    private int row, col;

    public SquarePanel(int row, int col, Color color){
        this.row = row;
        this.col = col;
        setBackground(color);
        setFont(new Font("Arial", Font.BOLD, 18));
    }

    public int getRow() {return row;}
    public int getCol() {return col;}

    public void setPiece(Piece piece){
        if (piece == null) {
            setText("");
        } else {
            setText(piece.toString());
        }
    }
}