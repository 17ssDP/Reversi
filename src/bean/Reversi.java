package bean;
import constant.FileGetter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Reversi {
    private static int dimen;
    private static Human human;
    private static Computer computer;
    private static boolean isOver = false;
    public static void main(String[] args) {
        initialize();
        Checker checker = new Checker(dimen);
        checker.addChess(new Chess(true, (char)('a' + dimen / 2 - 1), (char)('a' + dimen / 2 - 1)));
        checker.addChess(new Chess(false, (char)('a' + dimen / 2), (char)('a' + dimen / 2 - 1)));
        checker.addChess(new Chess(false, (char)('a' + dimen / 2 - 1), (char)('a' + dimen / 2)));
        checker.addChess(new Chess(true, (char)('a' + dimen / 2), (char)('a' + dimen / 2)));
        checker.print();
//        while(!isOver) {
//            if(!computer.getColor()){
//                if(!computerMov(checker)) {
//                    if(playerMov(checker)) {
//
//                    }
//                }
//                computerMov(checker);
//                checker.print();
//                playerMov(checker);
//                checker.print();
//            }else {
//                playerMov(checker);
//                checker.print();
//                computerMov(checker);
//                checker.print();
//            }
//        }
        computerMov(checker);
        checker.print();
        playerMov(checker);
        checker.print();
    }
    public static void initialize() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the board dimension: ");
        dimen = input.nextInt();
        System.out.print("Computer plays (X/O): ");
        boolean color = input.next().charAt(0) == 'O';
        computer = new Computer(color);
        human = new Human(!color);
    }

    public static boolean playerMov(Checker checker) {
        if(!human.hasValidMove(checker)){
            System.out.println((human.getColor()? 'O' : 'X') + " human has no valid move.");
            return false;
        }
        int[] move = getPlayerMove();
        if(!checker.checkHumanMove(move[0], move[1], human.getColor())) {
            System.out.println("Invalid move.");
            System.out.println("Game Over.");
            System.out.println((computer.getColor()? 'O' : 'X') + " human wins.");
            System.exit(0);
        }
        human.move(move[0], move[1], checker);
        human.addChess(move[0], move[1], checker);
        return true;
    }

    public static boolean computerMov(Checker checker) {
        char[] move = computer.move(checker);
        if(move == null) {
            System.out.print((computer.getColor()? 'O' : 'X') + "human has no valid move.");
            return true;
        }else {
            System.out.println("\nComputer places " + (computer.getColor()? 'O' : 'X') + " at " + move[0] + move[1]);
            computer.addChess(move[0] - 'a', move[1] - 'a', checker);
            return false;
        }
    }

    public static void writeBlog() {
        FileGetter fileGetter = new FileGetter();
        File blogFile = fileGetter.readFileFromClassPath();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(blogFile, true));
            bufferedWriter.write("日期" + "," + "玩家");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] getPlayerMove() {
        System.out.print("\nEnter move for " + (human.getColor()? 'O' : 'X') + "(RowCol): ");
        Scanner input = new Scanner(System.in);
        String move = input.next();
        return new int[]{move.charAt(0) - 'a', move.charAt(1) - 'a'};
    }
}
