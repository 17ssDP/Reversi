package main.java.entity;

public class Checker {
    private int size;
    private Chess[][] chessboard;
    public Checker() {

    }
    public Checker(int size) {
        this.size = size;
        this.chessboard = new Chess[size][size];

    }

    //玩家下棋之后，向棋盘中增加棋子
    public void addChess(Chess chess) {
        chessboard[chess.getRow() - 'a'][chess.getCol() - 'a'] = chess;
    }

    //检查棋盘上无子处能否下棋
    public int computeScore(int row, int col, boolean color) {
        if(getChess(row, col) != null)
            return 0;
        int score = 0;
        for(int i = 1; i >= -1; i--) {
            for(int j = 1; j >= -1; j--) {
                int out = 1;
                while(checkColor(row - out * i, col - out * j, !color))
                    out++;
                if(checkColor(row - out * i, col - out * j, color))
                    score += out - 1;
            }
        }
        return score;
    }

     private boolean checkColor(int row, int col, boolean color) {
        return getChess(row, col) != null && chessboard[row][col].getColor() == color;
    }

    //玩家下棋之后，翻转相应的棋子
    public int changeColor(int row, int col, boolean color) {
        int num = 0;
        for(int i = 1; i >= -1; i--) {
            for(int j = 1; j >= -1; j--) {
                int out = 1;
                while(checkColor(row - out * i, col - out * j, !color))
                    out++;
                if(checkColor(row - out * i, col - out * j, color)) {
                    num += out - 1;
                    while(out > 0){
                        chessboard[row - out * i][col - out * j].setColor(color);
                        out--;
                    }
                }
            }
        }
        return num;
    }

    //检查输入是否合理
    public boolean inRange(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    //得到想要位置的棋子
    public Chess getChess(int row, int col) {
        if(inRange(row, col))
            return chessboard[row][col];
        return null;
    }

    //打印棋盘
    public void print() {
 //       Font font = new Font("Courier New", Font.PLAIN, 14);
        System.out.print(" ");
        for(int i = 0; i < size; i++) {
            System.out.print(" " + (char)('a' + i));
        }
        for(int i = 0; i < size; i++) {
            System.out.print("\n" + (char)('a' + i));
            for(int j = 0; j < size; j++) {
                if(chessboard[i][j] == null) {
                    System.out.print(" .");
                }else if(chessboard[i][j].getColor()) {
                    System.out.print(" O");
                }else {
                    System.out.print(" X");
                }

            }
        }
        System.out.println();
    }

    //检查人类玩家的下棋位置是否合理
    public boolean checkHumanMove(int row, int col, boolean color) {
        if(!inRange(row, col))
            return false;
        return this.computeScore(row, col, color) > 0;
    }

    //初始化棋盘
    public void initialize() {
        this.addChess(new Chess(true, (char)('a' + size / 2 - 1), (char)('a' + size / 2 - 1)));
        this.addChess(new Chess(false, (char)('a' + size / 2), (char)('a' + size / 2 - 1)));
        this.addChess(new Chess(false, (char)('a' + size / 2 - 1), (char)('a' + size / 2)));
        this.addChess(new Chess(true, (char)('a' + size / 2), (char)('a' + size / 2)));
    }

    public int getSize() {
        return size;
    }

    public Chess[][] getChessboard() {
        return chessboard;
    }
 }
