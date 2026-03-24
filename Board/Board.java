package Board;

import java.util.List;
import Pieces.*;
import Pieces.Piece;
import Player.*;

public class Board {
    private Position[][] board;
    private Player whitePlayer;
    private Player blackPlayer;

    public void initializeBoard() {
        for (int col = 0; col < 8; col++) {
            whitePlayer.addPiece(new Pawn(Color.White, new Position(6, col)));
            blackPlayer.addPiece(new Pawn(Color.Black, new Position(1, col)));
        }

        whitePlayer.addPiece(new Rook(Color.White, new Position(7, 0)));
        whitePlayer.addPiece(new Rook(Color.White, new Position(7, 7)));
        blackPlayer.addPiece(new Rook(Color.Black, new Position(0, 0)));
        blackPlayer.addPiece(new Rook(Color.Black, new Position(0, 7)));

        whitePlayer.addPiece(new Knight(Color.White, new Position(7, 1)));
        whitePlayer.addPiece(new Knight(Color.White, new Position(7, 6)));
        blackPlayer.addPiece(new Knight(Color.Black, new Position(0, 1)));
        blackPlayer.addPiece(new Knight(Color.Black, new Position(0, 6)));

        whitePlayer.addPiece(new Bishop(Color.White, new Position(7, 2)));
        whitePlayer.addPiece(new Bishop(Color.White, new Position(7, 5)));
        blackPlayer.addPiece(new Bishop(Color.Black, new Position(0, 2)));
        blackPlayer.addPiece(new Bishop(Color.Black, new Position(0, 5)));

        whitePlayer.addPiece(new Queen(Color.White, new Position(7, 3)));
        blackPlayer.addPiece(new Queen(Color.Black, new Position(0, 3)));

        whitePlayer.addPiece(new King(Color.White, new Position(7, 4)));
        blackPlayer.addPiece(new King(Color.Black, new Position(0, 4)));
    }

    public Board(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = new Position[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Position(i, j);
            }
        }

        initializeBoard();
    }

    public Board() {
        this(new Player(Color.White), new Player(Color.Black));
    }

    public Player getPlayer(Color color) {
        if (color == Color.White) {
            return getWhitePlayer();
        } else {
            return getBlackPlayer();
        }
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Piece getPiece(Position pos) {
        Piece p = whitePlayer.findPieceAt(pos);
        if (p == null) {
            p = blackPlayer.findPieceAt(pos);
        }
        return p;
    }

    public boolean movePiece(Position start, Position end) {
        Piece piece = getPiece(start);

        if (piece == null) {
            return false;
        }

        Piece target = getPiece(end);

        if (piece.getColor() == Color.White) {
            whitePlayer.makeMove(start, end);
            if (target != null && target.getColor() == Color.Black) {
                blackPlayer.capturePiece(end);
            }
        } else {
            blackPlayer.makeMove(start, end);
            if (target != null && target.getColor() == Color.White) {
                whitePlayer.capturePiece(end);
            }
        }

        return true;
    }

    private King findKing(Color color) {
        Player player = getPlayer(color);

        for (Piece piece : player.getPieces()) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }

        return null;
    }

    private boolean canAttackSquare(Piece piece, Position target) {
        List<Position> moves = piece.possibleMoves();
        if (moves == null) {
            return false;
        }

        for (Position move : moves) {
            if (move.equals(target)) {
                return true;
            }
        }

        return false;
    }

    public boolean isCheck() {
        return isCheck(Color.White) || isCheck(Color.Black);
    }

    public boolean isCheck(Color color) {
        King king = findKing(color);

        if (king == null) {
            return false;
        }

        Position kingPosition = king.getPosition();
        Player opponent = (color == Color.White) ? blackPlayer : whitePlayer;

        for (Piece piece : opponent.getPieces()) {
            if (canAttackSquare(piece, kingPosition)) {
                return true;
            }
        }

        return false;
    }

    public boolean isCheck(Position moveStart, Position moveEnd) {
        Piece movingPiece = getPiece(moveStart);
        if (movingPiece == null) {
            return false;
        }

        Piece capturedPiece = getPiece(moveEnd);
        Position originalPosition = movingPiece.getPosition();

        if (capturedPiece != null) {
            if (capturedPiece.getColor() == Color.White) {
                whitePlayer.capturePiece(moveEnd);
            } else {
                blackPlayer.capturePiece(moveEnd);
            }
        }

        movingPiece.move(moveEnd);
        boolean result = isCheck(movingPiece.getColor());
        movingPiece.move(originalPosition);

        if (capturedPiece != null) {
            if (capturedPiece.getColor() == Color.White) {
                whitePlayer.addPiece(capturedPiece);
            } else {
                blackPlayer.addPiece(capturedPiece);
            }
        }

        return result;
    }

    public boolean isCheckmate() {
        return isCheckmate(Color.White) || isCheckmate(Color.Black);
    }

    public boolean isCheckmate(Color color) {
        if (!isCheck(color)) {
            return false;
        }

        Player player = getPlayer(color);

        for (Piece piece : player.getPieces()) {
            List<Position> moves = piece.possibleMoves();

            if (moves == null) {
                continue;
            }

            Position start = piece.getPosition();

            for (Position end : moves) {
                Piece capturedPiece = getPiece(end);
                Position originalPosition = piece.getPosition();

                if (capturedPiece != null && capturedPiece.getColor() == color) {
                    continue;
                }

                if (capturedPiece != null) {
                    if (capturedPiece.getColor() == Color.White) {
                        whitePlayer.capturePiece(end);
                    } else {
                        blackPlayer.capturePiece(end);
                    }
                }

                piece.move(end);
                boolean stillInCheck = isCheck(color);
                piece.move(originalPosition);

                if (capturedPiece != null) {
                    if (capturedPiece.getColor() == Color.White) {
                        whitePlayer.addPiece(capturedPiece);
                    } else {
                        blackPlayer.addPiece(capturedPiece);
                    }
                }

                if (!stillInCheck) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  A  B  C  D  E  F  G  H\n");

        for (int row = 0; row < 8; row++) {
            int displayRow = 8 - row;
            sb.append(displayRow + " ");

            for (int col = 0; col < 8; col++) {
                Position pos = board[row][col];
                Piece piece = getPiece(pos);

                if (piece != null) {
                    sb.append(piece.toString() + " ");
                } else {
                    sb.append("## ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}