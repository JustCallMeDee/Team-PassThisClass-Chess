package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Board.Board;
import Game.Game;

public class ChessGUI extends JFrame{

    private Board board;
    private BoardPanel boardPanel;
    private JLabel turnLabel;
    private Utils.Color currentTurn;
    private Game game;

    public ChessGUI(Board board, Utils.Color startingTurn){
        this(board, startingTurn, null);
    }

    public ChessGUI(Board board, Utils.Color startingTurn, Game game){
        this.board = board;
        this.currentTurn = startingTurn;
        this.game = game;

        setTitle("Chess Game");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        turnLabel = new JLabel("", SwingConstants.CENTER);
        turnLabel.setFont(turnLabel.getFont().deriveFont(Font.BOLD, 18f));
        add(turnLabel, BorderLayout.NORTH);

        boardPanel = new BoardPanel(board, this);
        add(boardPanel, BorderLayout.CENTER);

        setJMenuBar(createMenuBar());

        updateTurnDisplay();
        setVisible(true);
    }

    public ChessGUI(Board board){
        this(board, Utils.Color.WHITE);
    }

    public ChessGUI(){
        this(new Board(), Utils.Color.WHITE);
    }

    public Utils.Color getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Utils.Color currentTurn) {
        this.currentTurn = currentTurn;
        updateTurnDisplay();
        if (game != null && game.getCurrentTurn() != currentTurn) {
            game.setCurrentTurn(currentTurn);
        }
    }

    public void toggleTurn() {
        setCurrentTurn(currentTurn == Utils.Color.WHITE ? Utils.Color.BLACK : Utils.Color.WHITE);
    }

    public void resetGame() {
        board = new Board();
        boardPanel.setBoard(board);
        setCurrentTurn(Utils.Color.WHITE);
    }

    private void updateTurnDisplay() {
        String turnText = currentTurn + "'s turn";
        turnLabel.setText(turnText);
        setTitle("Chess Game - " + turnText);
    }

    private JMenuBar createMenuBar(){
        
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGame = new JMenuItem(new AbstractAction("New Game"){
            public void actionPerformed(ActionEvent e){
                resetGame();
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