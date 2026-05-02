package GUI;

import javax.swing.*;
import java.awt.*;
import Pieces.Piece;

public class SquarePanel extends JButton {

    private int row, col;
    private JLabel imageLabel;
    private final String PATH_TO_PIECE_VISUALS = "C:\\Users\\dalto\\OneDrive\\Desktop\\Programs\\Java\\Chess\\GUI\\PieceVisual\\";
    private String colorPath;
    private String piecePath;
    private int size = 65;

    public SquarePanel(int row, int col, Color color){
        this.row = row;
        this.col = col;
        setBackground(color);
        imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon());
        this.add(imageLabel);
    }

    public String getImagePath(){
        return PATH_TO_PIECE_VISUALS + colorPath + piecePath;
    }

    public int getRow() {return row;}
    public int getCol() {return col;}

    public void setPiece(Piece piece){
        if (piece == null) {
            piecePath = "";
        } else {
            //Switch case to set correct path
            switch (piece.toString().charAt(1)) {
                case 'P':
                    piecePath = "pawn.png\\";
                    break;
                case 'N':
                    piecePath = "knight.png\\";
                    break;
                case 'B':
                    piecePath = "bishop.png\\";
                    break;
                case 'R':
                    piecePath = "rook.png\\";
                    break;
                case 'Q':
                    piecePath = "queen.png\\";
                    break;
                case 'K':
                    piecePath = "king.png\\";
                    break;
                default:
                    break;
            }
        }
        updateImage();
    }

    private void updateImage(){
        if(piecePath == ""){
            imageLabel.setIcon(null);            
        }
        else{
            imageLabel.setIcon(new ImageIcon(new ImageIcon(getImagePath()).getImage()
                               .getScaledInstance(size, size, Image.SCALE_SMOOTH)));
        }
    }

    public String getColor(){
        return colorPath.substring(0, colorPath.length() - 3);
    }

    public void setColor(String color){
        colorPath = color + "\\";
        updateImage();
    }
}