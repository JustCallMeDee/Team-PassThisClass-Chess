package Utils;

public class Utils {

    public static Position parsePosition(String square){
        if (square == null || square.length() != 2) {
            return null;
        }

        square = square.toUpperCase();

        char file = square.charAt(0);
        char rank = square.charAt(1);

        if (file < 'A' || file > 'H' || rank < '1' || rank > '8'){
            return null;
        }

        int col = file - 'A';
        int row = 8 - (rank - '0');

        return new Position(row, col);
    }

    public static boolean isValidMoveFormat(String input){
        if (input == null){
            return false;
        }
        return input.trim().toUpperCase().matches("^[A-H][1-8]\\s+[A-H][1-8]$");
    }

    public static boolean isWithinBound(int row, int col){
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}