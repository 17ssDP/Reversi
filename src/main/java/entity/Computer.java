package main.java.entity;

public class Computer extends Player {

    public Computer(boolean color) {
        super(color);
    }

    public int[] getMove(Checker checker){
        int maxScore = 0;
        int row = 0;
        int col = 0;
        for(int i = 0; i < checker.getSize(); i++) {
            for(int j = 0; j < checker.getSize(); j++) {
                if(checker.getChessboard()[i][j] == null) {
                    int score = checker.computeScore(i, j, this.getColor());
                    if(score > maxScore) {
                        maxScore = score;
                        row = i;
                        col = j;
                    }
                }
            }
        }
        if(maxScore > 0)
            return new int[]{row, col};
        else
            return  null;
    }
}
