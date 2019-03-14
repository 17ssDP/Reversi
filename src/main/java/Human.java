package main.java;

public class Human extends Player{
    boolean giveUp = false;
    public Human(boolean color) {
        super(color);
    }
    @Override
    public void move(int row, int col, Checker checker) {
        Chess chess = new Chess(this.getColor(), (char)(row + 'a'), (char)(col + 'a'));
        checker.getChessboard()[row][col] = chess;
    }
    public boolean isGiveUp() {
        return giveUp;
    }

    public void setGiveUp(boolean giveUp) {
        this.giveUp = giveUp;
    }
}
