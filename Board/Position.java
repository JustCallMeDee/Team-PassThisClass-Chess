package Board;

import java.io.Serializable;

public class Position implements Serializable{
    private static final long serialVersionUID = 1L;
}

public class Position {
    private int row;
    private char column;

    //Todo: Add a more debugable input detection
    public Position(int row, char column) throws IllegalArgumentException{
        this.row = row;
        this.column = column;

        if(!Position.isValid(this)){
            throw new IllegalArgumentException("Either row or column is incorrect");
        }
    }

    public Position(int row, int column){
        //The char '@' comes right before 'A', so as long as the column is correct, its 
        this(row, (char) (column + '@'));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public void setColumn(int column) {
        this.column = (char)(column + '@');
    }

    public static boolean isValid(Position p){
        if(p.getRow() < 1 || p.getRow() > 8){
            //System.out.print("row");
            return false;
        }
        //'A' = 65, 'H' = 72
        if(p.getColumn() < 65 || p.getColumn() > 72){
            //System.out.print("column");
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Position p = (Position)(obj);
            if(this.row == p.getRow() && this.column == p.getColumn()){
                return true;
            }
            return false;
        } catch (Exception e) {
            return super.equals(obj);
        }

    }
}
