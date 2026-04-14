package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Board.Board;

public class ChessGUI extends JFrame{

    private Board board;
    private BoardPanel boardPanel;

    public ChessGUI(){
        
        board = new Board();

        setTitle("Chess Game");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel(board);
        add(boardPanel, BorderLayout.CENTER);

        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    private JMenuBar createMenuBar(){
        
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGame = new JMenuItem(new AbstractAction("New Game"){
            public void actionPerformed(ActionEvent e){
                board = new Board();
                boardPanel.setBoard(board);
            }
        });

        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e -> boardPanel.saveGame());

        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(e -> boardPanel.loadGame());

        JMenuItem settings = new JMenuItem("Settings");
        settings.addActionListener(e -> new SettingsDialog(boardPanel));

        gameMenu.add(newGame);
        gameMenu.add(saveGame);
        gameMenu.add(loadGame);
        gameMenu.add(settings);
        menuBar.add(gameMenu);
        return menuBar;
    }

    public static void main(String[] args){
        new ChessGUI();
    }
}