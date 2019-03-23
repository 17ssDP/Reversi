package main.java.entity;

public abstract class Player {
    private boolean color;
    private int chessNum;
    private boolean isWin;
    private char chess;
    public Player() {

    }

    public Player(boolean color) {
        this.color = color;
        this.chessNum = 2;
        this.chess = color? 'O' : 'X';
    }

    //Check if there exist a valid place to move
    public boolean hasValidMove(Checker checker) {
        int score = 0;
        for(int i = 0; i < checker.getSize(); i++) {
            for(int j = 0; j < checker.getSize(); j++) {
                if(checker.getChessboard()[i][j] == null) {
                    score = checker.computeScore(i, j, this.color);
                    if(score > 0)
                        return true;
                }
            }
        }
        return false;
    }

    //Chess
    public void move(int row, int col, Checker checker) {
        Chess chess = new Chess(this.getColor(), (char)(row + 'a'), (char)(col + 'a'));
        checker.getChessboard()[row][col] = chess;
    }

    //Change the number of chess
    public void changeChess(int[] move, Checker checker, Player reducer) {
        int add = checker.changeColor(move[0], move[1], this.getColor());
        this.setChessNum(this.getChessNum() + add + 1);
        reducer.setChessNum(reducer.getChessNum() - add);
    }

    public boolean getColor() {
        return color;
    }

    public int getChessNum() {
        return chessNum;
    }

    public char getChess() {
        return this.chess;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public void setChessNum(int chessNum) {
        this.chessNum = chessNum;
    }
}
