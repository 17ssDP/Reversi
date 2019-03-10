package bean;

public class Player {
    private boolean color;
    private int chessNum;
    private boolean isWin;

    public Player() {

    }

    public Player(boolean color) {
        this.color = color;
        this.chessNum = 2;
    }

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

    public void move(int row, int col, Checker checker) {

    }

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

    public void setColor(boolean color) {
        this.color = color;
    }

    public void setChessNum(int chessNum) {
        this.chessNum = chessNum;
    }
}
