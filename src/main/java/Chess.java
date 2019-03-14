package main.java;

public class Chess {
    private boolean color; //True for White, False for black
    private char row;
    private char col;
    private int score;
    public Chess() {

    }

    public Chess(boolean color, char row, char col) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.score = -1;
    }

    public boolean getColor() {
        return color;
    }

    public char getRow() {
        return row;
    }

    public char getCol() {
        return col;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public void setCol(char col) {
        this.col = col;
    }
}
