package Board;

public class Position {
    private int row;
    private char column;

    //Todo: Add a more debugable input detection
    public Position(int row, char column){
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
            return false;
        }
        
        if(p.getColumn() < 'A' || p.getColumn() > 'H'){
            return false;
        }

        return true;
    }
}
