package utils;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;

        if (!isValid(this)){
            throw new IllegalArgumentException("Row/Column is out of bounds.");
        }
    }

    public Position(int rank, char file){
        file = Character.toUpperCase(file);

        if (rank < 1 || rank > 8 || file < 'A' || file > 'H') {
            throw new IllegalArgumentException("Invalid Chess position.");
        }

        this.row = 8 - rank;
        this.col = file - 'A';
    }

    public int getRow(){
        return row;
    }

    public void setRow(int row){
        this.row = row;
        if (!isValid(this)){
            throw new IllegalArgumentException("Row is out of bounds.");
        }
    }

    public int getCol(){
        return col;
    }

    public char getColumn(){
        return (char) ('A' + col);
    }

    public void setCol(int col){
        this.col = col;
        if (!isValid(this)){
            throw new IllegalArgumentException("Column is out of bounds.");
        }
    }

    public void setColumn(char column){
        column = Character.toUpperCase(column);
        if (column < 'A' || column > 'H'){
            throw new IllegalArgumentException("Column must be between A-H.");
        }
        this.col = column - 'A';
    }
    
    public void setColumn(int column){
        setCol(column);
    }

    public static boolean isValid(Position p){
        return p != null
                && p.row >= 0 && p.row < 8
                && p.col >= 0 && p.col < 8;
    }

    public char getFile(){
        return (char) ('A' + col);
    }

    public int getRank(){
        return 8 - row;
    }

    @Override
    public String toString(){
        return "" + getFile() + getRank();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;

        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}