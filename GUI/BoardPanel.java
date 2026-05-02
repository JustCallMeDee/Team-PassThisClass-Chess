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
	private ChessGUI gui;

	private Color lightColor = new Color(240, 217, 181);
	private Color darkColor = new Color(181, 136, 99);

	public Color getLightColor() {
		return lightColor;
	}

	public Color getDarkColor() {
		return darkColor;
	}

	private String player1Color = "White";
	private String player2Color = "Black";

	public BoardPanel(Board board) {
		this(board, null);
	}

	public BoardPanel(Board board, ChessGUI gui) {
		this.board = board;
		this.gui = gui;
		setLayout(new GridLayout(8, 8));
		squares = new SquarePanel[8][8];

		initBoard();
	}

	public void setBoard(Board board) {
		this.board = board;
		selected = null;
		removeAll();
		initBoard();
		revalidate();
		repaint();
	}

	private void initBoard() {
		for (int row = 1; row < 9; row++) {
			for (int col = 1; col < 9; col++) {
				boolean isLight = (row + col) % 2 == 0;

				SquarePanel square = new SquarePanel(row, col, isLight ? lightColor : darkColor);
				squares[row-1][col-1] = square;

				square.addActionListener(e -> handleClick(square));
				add(square);
			}
		}
		refreshBoard();
	}

	private Utils.Color getCurrentTurn() {
		return gui == null ? Utils.Color.WHITE : gui.getCurrentTurn();
	}

	private void handleClick(SquarePanel square) {
		Position pos = new Position(square.getRow(), square.getCol());
		Piece clickedPiece = board.getPiece(pos);

		if (selected == null) {
			if (clickedPiece == null) {
				return;
			}

			if (clickedPiece.getColor() != getCurrentTurn()) {
				return;
			}

			selected = pos;
			return;
		}

		Piece movingPiece = board.getPiece(selected);
		if (movingPiece == null) {
			selected = null;
			return;
		}

		if (clickedPiece != null && clickedPiece.getColor() == movingPiece.getColor()) {
			if (clickedPiece.getColor() == getCurrentTurn()) {
				selected = pos;
			}
			return;
		}

		Piece targetBeforeMove = board.getPiece(pos);
		boolean moved = board.movePiece(selected, pos);

		if (moved) {
			if (targetBeforeMove != null) {
				if (targetBeforeMove.toString().equalsIgnoreCase("bK")) {
					showWinner("White Wins!");
				} else if (targetBeforeMove.toString().equalsIgnoreCase("wK")) {
					showWinner("Black Wins!");
				}
			}

			if (gui != null) {
				gui.toggleTurn();
			}
		}
		selected = null;
		refreshBoard();
	}

	private void showWinner(String message) {
		JOptionPane.showMessageDialog(this, message);
		System.exit(0);
	}

	public void refreshBoard() {
		for (int row = 1; row < 9; row++) {
			for (int col = 1; col < 9; col++) {
				Piece piece = board.getPiece(new Position(row, col));
				if(piece != null && piece.getColor() == Utils.Color.WHITE) {
					squares[row-1][col-1].setColor(player1Color);
				}
				else {
					squares[row-1][col-1].setColor(player2Color);
				}
				squares[row-1][col-1].setPiece(piece);
			}
		}
	}

	public void saveGame() {
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File("chess.save"));
		if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

		try (ObjectOutputStream out = new ObjectOutputStream(
			    new FileOutputStream(chooser.getSelectedFile()))) {
			out.writeObject(new SavedGameState(board, getCurrentTurn()));
			JOptionPane.showMessageDialog(this, "Game Saved.");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Save failed: " + e.getMessage());
		}
	}

	public void loadGame() {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

		try (ObjectInputStream in = new ObjectInputStream(
			    new FileInputStream(chooser.getSelectedFile()))) {
			Object loaded = in.readObject();

			if (loaded instanceof SavedGameState) {
				SavedGameState state = (SavedGameState) loaded;
				board = state.board;
				selected = null;
				if (gui != null) {
					gui.setCurrentTurn(state.currentTurn);
				}
			} else if (loaded instanceof Board) {
				board = (Board) loaded;
				selected = null;
			}

			setBoard(board);
			JOptionPane.showMessageDialog(this, "Game Loaded.");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Load failed: " + e.getMessage());
		}
	}

	public void updateColors(Color light, Color dark, String player1Color, String player2Color) {
		this.lightColor = light;
		this.darkColor = dark;
		this.player1Color = player1Color;
		this.player2Color = player2Color;
		setBoard(board);
	}

	private static class SavedGameState implements Serializable {
		private static final long serialVersionUID = 1L;
		private final Board board;
		private final Utils.Color currentTurn;

		private SavedGameState(Board board, Utils.Color currentTurn) {
			this.board = board;
			this.currentTurn = currentTurn;
		}
	}
}
