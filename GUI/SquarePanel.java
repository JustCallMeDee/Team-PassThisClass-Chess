package GUI;

import javax.swing.*;
import java.awt.*;
import Pieces.Piece;

public class SquarePanel extends JButton {

    private int row, col;
    private JLabel imageLabel;
    private final String PATH_TO_PIECE_VISUALS = "C:\\Users\\dalto\\OneDrive\\Desktop\\Programs\\Java\\Chess\\GUI\\PieceVisual\\";
    private String colorPath;
    private int size = 65;

    public SquarePanel(int row, int col, Color color){
        this.row = row;
        this.col = col;
        setBackground(color);
        imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon());
        this.add(imageLabel);
        //setFont(new Font("Arial", Font.BOLD, 18));
        colorPath = "Black\\";
    }

    public int getRow() {return row;}
    public int getCol() {return col;}

    public void setPiece(Piece piece){
        if (piece == null) {
            imageLabel.setIcon(new ImageIcon());
        } else {
            //Switch case to set correct path
            String fullPath = PATH_TO_PIECE_VISUALS + colorPath;
            switch (piece.toString().charAt(1)) {
                case 'P':
                    fullPath += "pawn.png\\";
                    break;
                case 'N':
                    fullPath += "knight.png\\";
                    break;
                case 'B':
                    fullPath += "bishop.png\\";
                    break;
                case 'R':
                    fullPath += "rook.png\\";
                    break;
                case 'Q':
                    fullPath += "queen.png\\";
                    break;
                case 'K':
                    fullPath += "king.png\\";
                    break;
                default:
                    break;
            }
            imageLabel.setIcon(new ImageIcon(new ImageIcon(fullPath).getImage()
                               .getScaledInstance(size, size, Image.SCALE_SMOOTH)));
        }
    }
}