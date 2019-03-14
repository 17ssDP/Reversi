package main.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Reversi {
    private static int dimen;
    private static Human human;
    private static Computer computer;
    private static Player[] players;
    private static boolean isOver = false;
    private static long startTime;
    public static void main(String[] args) {
        initialize();
        Checker checker = new Checker(dimen);
        checker.initialize();
        checker.print();
        while(!isOver) {
            for(int i = 0; i < players.length; i++) {
                int num = i % 2;
                if(players[num].hasValidMove(checker)){
                    playerMove(players[num], checker);
                    checker.print();
                }else if(players[(num + 1) % 2].hasValidMove(checker)) {
                    System.out.print((players[num].getColor()? 'O' : 'X') + " player has no valid move.");
                    playerMove(players[++num % 2], checker);
                    checker.print();
                }else {
                    System.out.println("Both players have no valid move.");
                    isOver = true;
                    break;
                }
            }
        }
        endGame();
        writeBlog();
    }
    public static void initialize() {
        startTime = System.currentTimeMillis();
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the board dimension: ");
            if(input.hasNextInt()) {
                dimen = input.nextInt();
                break;
            }
        }
        boolean color;
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Computer plays (X/O): ");
            if(input.hasNext()) {
                color = input.next().charAt(0) == 'O';
                break;
            }
        }
        computer = new Computer(color);
        human = new Human(!color);
        players = new Player[2];
        if(!color) {
            players[0] = computer;
            players[1] = human;
        }else {
            players[1] = computer;
            players[0] = human;
        }
    }

    public static void playerMove(Player player, Checker checker) {
        if(player instanceof Human) {
            humanMov(checker);
        }else
            computerMov(checker);
    }

    public static void humanMov(Checker checker) {
        int[] move = getPlayerMove();
        if(!checker.checkHumanMove(move[0], move[1], human.getColor())) {
            System.out.println("Invalid move.");
            System.out.println("Game Over.");
            System.out.println((computer.getColor()? 'O' : 'X') + " player wins.");
            human.setGiveUp(true);
            writeBlog();
            System.exit(0);
        }
        human.move(move[0], move[1], checker);
        human.changeChess(move, checker, computer);
    }

    public static void computerMov(Checker checker) {
        int[] move = computer.move(checker);
        System.out.println("Computer places " + (computer.getColor()? 'O' : 'X') + " at " + (char)(move[0] + 'a') + (char)(move[1] + 'a') + ".");
        computer.changeChess(move, checker, human);
    }

    public static void writeBlog() {
        long endTime = System.currentTimeMillis();
        FileGetter fileGetter = new FileGetter();
        File blogFile = fileGetter.readFileFromClassPath();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(blogFile, true));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            bufferedWriter.write(simpleDateFormat.format(new Date()) + ",");
            bufferedWriter.write((endTime - startTime) / 1000 + ",");
            bufferedWriter.write(dimen + " * " + dimen + ",");
            bufferedWriter.write((computer.getColor()? "human" : "computer") + ",");
            bufferedWriter.write((!computer.getColor()? "human" : "computer") + ',');
            if(human.isGiveUp()) {
                bufferedWriter.write("Human give up");
            }else {
                bufferedWriter.write(players[0].getChessNum() + " to " + players[1].getChessNum());
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] getPlayerMove() {
        String move;
        while(true){
            System.out.print("Enter move for " + (human.getColor()? 'O' : 'X') + "(RowCol): ");
            Scanner input = new Scanner(System.in);
            move = input.next();
            if(move.length() >= 2)
                break;
        }
        return new int[]{move.charAt(0) - 'a', move.charAt(1) - 'a'};
    }

    public static void endGame() {
        System.out.println("Game Over.");
        System.out.println("X : O = " + players[0].getChessNum() + " : " + players[1].getChessNum());
        System.out.println((players[0].getChessNum() > players[1].getChessNum()? "X" : "O") + " player wins.");
    }
}
