package Utils;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }


    public int getCol(){
        return col;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    @Override
    public String toString(){
        char file = (char) ('A' + col);
        int rank = 8 - row;
        return "" + file + rank;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        
        Position other = (Position) obj;
        
        return this.row == other.row && this.col == other.col;
    }
}