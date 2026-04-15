package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import Board.Board;
import Board.Position;
import Pieces.Piece;

public class BoardPanel extends JPanel {

    private Board board;
    private SquarePanel[][] squares;
    private Position selected;

    private Color lightColor = new Color(240, 217, 181);
    private Color darkColor = new Color(181, 136, 99);

    public BoardPanel(Board board){
        this.board = board;
        setLayout(new GridLayout(8, 8));
        squares = new SquarePanel[8][8];

        initBoard();
    }

    public void setBoard(Board board){
        this.board = board;
        removeAll();
        initBoard();
        revalidate();
        repaint();
    }

    private void initBoard(){
        for (int row = 1; row < 9; row++){
            for (int col = 1; col < 9; col++){
                boolean isLight = (row + col) % 2 == 0;

                SquarePanel square = new SquarePanel(row, col, isLight ? lightColor : darkColor);
                squares[row-1][col-1] = square;

                square.addActionListener(e -> handleClick(square));
                add(square);
            }
        }
        refreshBoard();
    }

    private void handleClick(SquarePanel square){
        Position pos = new Position(square.getRow(), square.getCol());

        if (selected == null){
            selected = pos;
        } else {

            Piece target = board.getPiece(pos);
            if (target != null && target.toString().equalsIgnoreCase("bK")){
                showWinner("White Wins!");
            }
            if (target != null && target.toString().equalsIgnoreCase("wK")){
                showWinner("Black Wins!");
            }
            board.movePiece(selected, pos);
            selected = null;
            refreshBoard();
        }
    }

    private void showWinner(String message){
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }

    public void refreshBoard(){
        for (int row = 1; row < 9; row++){
            for (int col = 1; col < 9; col++){
                Piece piece = board.getPiece(new Position(row, col));
                squares[row-1][col-1].setPiece(piece);
            }
        }
    }

    public void saveGame(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("chess.save"))){
            out.writeObject(board);
            JOptionPane.showMessageDialog(this, "Game Saved.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("chess.save"))){
            board = (Board) in.readObject();
            setBoard(board);
            JOptionPane.showMessageDialog(this, "Game Loaded.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateColors(Color light, Color dark){
        this.lightColor = light;
        this.darkColor = dark;
        setBoard(board);
    }
}
