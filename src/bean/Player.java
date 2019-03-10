package bean;

public class Player {
    private boolean color;
    private int chessNum;
    private boolean isWin;

    public Player() {

    }

    public Player(boolean color) {
        this.color = color;
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
        Chess chess = new Chess(this.color, (char)(row + 'a'), (char)(col + 'a'));
        checker.getChessboard()[row][col] = chess;
    }

    public void addChess(int row, int col, Checker checker) {
        int add = checker.changeColor(row, col, this.getColor());
        this.setChessNum(this.getChessNum() + add);
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
