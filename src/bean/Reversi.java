package bean;
import java.util.Scanner;

public class Reversi {
    private static int dimen;
    private static boolean computer;
    public static void main(String[] args) {
        initialize();
        Checker checker = new Checker(dimen);
        checker.addChess(new Chess(true, (char)('a' + dimen / 2 - 1), (char)('a' + dimen / 2 - 1)));
        checker.addChess(new Chess(false, (char)('a' + dimen / 2), (char)('a' + dimen / 2 - 1)));
        checker.addChess(new Chess(false, (char)('a' + dimen / 2 - 1), (char)('a' + dimen / 2)));
        checker.addChess(new Chess(true, (char)('a' + dimen / 2), (char)('a' + dimen / 2)));
        checker.print();
        boolean isOver = false;
        while(!isOver){
            if(!computer) {
                compuMov(checker);

            }
        }
    }
    public static void initialize() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the board dimension: ");
        dimen = input.nextInt();
        System.out.print("Computer plays (X/O): ");
        computer = input.next().charAt(0) == 'O';
    }
    public static void compuMov(Checker checker){
        int maxScore = 0;
        char row = 'a';
        char col = 'a';
        for(int i = 0; i < checker.getSize(); i++) {
            for(int j = 0; j < checker.getSize(); j++) {
                if(checker.getChessboard()[i][j] == null) {
                    int score = checker.computScore(i, j, computer);
                    if(score > maxScore) {
                        maxScore = score;
                        row = (char)('a' + i);
                        col = (char)('a' + j);
                    }
                }
            }
        }
        if(maxScore > 0) {
            checker.changeColor(row - 'a', col - 'a', computer);
            Chess chess = new Chess(computer, row, col);
            checker.addChess(chess);
            System.out.println("\nComputer places " + (computer? 'O' : 'X') + " at " + row + col);
            checker.print();
        }else {
            System.out.print((computer? 'O' : 'X') + "player has no valid move. Enter move for " + (!computer? 'O' : 'X') + "(RowCol): ");
        }
    }
    public static void playerMov(Checker checker) {
        System.out.print("Enter move for " + (!computer? 'O' : 'X') + "(RowCol): ");
        Scanner input = new Scanner(System.in);
        String move = input.next();
        if(!checker.changeColor(move.charAt(0), move.charAt(1), !computer)) {
            System.out.println("Invalid move.");
            System.out.println("Game Over.");
            System.out.println((computer? 'O' : 'X') + " player wins.");
            System.exit(0);
        }
    }
    public static void hasValidMove() {

    }
}
