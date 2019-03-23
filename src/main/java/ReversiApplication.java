package main.java;

import main.java.constant.FileConstant;
import main.java.constant.InfoConstant;
import main.java.entity.Checker;
import main.java.entity.Computer;
import main.java.entity.Human;
import main.java.entity.Player;
import main.java.util.FileUtil;

import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReversiApplication {
    private static int dimen;
    private static Human human;
    private static Computer computer;
    private static Player[] players;
    private static long startTime;
    public static void main(String[] args) {
        initialize();
        Checker checker = new Checker(dimen);
        checker.initialize();
        checker.print();
        playGame(checker);
        endGame();
        writeBlog();
    }

    //初始化游戏信息，包括棋盘大小、人类玩家及电脑玩家棋子
    public static void initialize() {
        startTime = System.currentTimeMillis();
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print(InfoConstant.INPUT_DIMEN);
            if(input.hasNextInt()) {
                dimen = input.nextInt();
                if(dimen >= 4 && dimen <= 10 && dimen % 2 == 0)
                    break;
            }
        }
        boolean color;
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print(InfoConstant.INPUT_COMPUTER);
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

    //游戏过程
    public static void playGame(Checker checker) {
        boolean isOver = false;
        int num = 0;
        while(!isOver) {
            for(int i = 0; i < players.length; i++) {
                if(players[num].hasValidMove(checker)){
                    playerMove(players[num], checker);
                    checker.print();
                    num = ++num % 2;
                } else if(players[(num + 1) % 2].hasValidMove(checker)) {
                    System.out.print(MessageFormat.format(InfoConstant.NO_VALID_MOVE, players[num].getChess()));
                    playerMove(players[(num + 1) % 2], checker);
                    checker.print();
                } else {
                    System.out.println(InfoConstant.NO_VALID_MOVES);
                    isOver = true;
                    break;
                }
            }
        }
    }

    //玩家下子
    public static void playerMove(Player player, Checker checker) {
        if(player instanceof Human) {
            humanMov(checker);
        }else
            computerMov(checker);
    }

    //人类玩家下子
    public static void humanMov(Checker checker) {
        int[] move = getPlayerMove();
        if(!checker.checkHumanMove(move[0], move[1], human.getColor())) {
            invalidMove();
        }
        human.move(move[0], move[1], checker);
        human.changeChess(move, checker, computer);
    }

    //电脑玩家自动下棋，并打印电脑下子位置
    public static void computerMov(Checker checker) {
        int[] move = computer.getMove(checker);
        computer.move(move[0], move[1], checker);
        System.out.println(MessageFormat.format(InfoConstant.COMPUTER_MOVE, computer.getChess(), (char)(move[0] + 'a') + "" + (char)(move[1] + 'a')));
        computer.changeChess(move, checker, human);
    }

    //得到人类玩家的下子位置
    public static int[] getPlayerMove() {
        String move;
        while(true){
            System.out.print(MessageFormat.format(InfoConstant.HUMAN_MOVE, human.getChess()));
            Scanner input = new Scanner(System.in);
            move = input.next();
            if(move.length() >= 2)
                break;
        }
        return new int[]{move.charAt(0) - 'a', move.charAt(1) - 'a'};
    }

    //处理人类玩家的不合理下子位置
    public static void invalidMove() {
        System.out.println(InfoConstant.INVALID_MOVE);
        System.out.println(InfoConstant.GAME_OVER);
        System.out.println(MessageFormat.format(InfoConstant.WINNER, computer.getChess()));
        human.setGiveUp(true);
        writeBlog();
        System.exit(0);
    }

    //将对局信息写入文件
    public static void writeBlog() {
        long endTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String[] message = new String[]{simpleDateFormat.format(new Date()),
                "" + (endTime - startTime) / 1000,
                dimen + " * " + dimen,
                (computer.getColor()? InfoConstant.HUMAN : InfoConstant.COMPUTER),
                (!computer.getColor()? InfoConstant.HUMAN : InfoConstant.COMPUTER),
                human.isGiveUp()? InfoConstant.GIVE_UP : MessageFormat.format(InfoConstant.PLAYER_TO_PLAYER, players[0], players[1])};
        FileUtil.write(message, FileConstant.LOG_CSV);
    }

    //游戏结束，打印游戏结果
    public static void endGame() {
        System.out.println(InfoConstant.GAME_OVER);
        System.out.println(MessageFormat.format(InfoConstant.NUMBER_COM, players[0].getChessNum(), players[1].getChessNum()));
        if(players[0].getChessNum() == players[1].getChessNum())
            System.out.println(InfoConstant.DRAW);
        else
            System.out.println(MessageFormat.format(InfoConstant.WINNER, (players[0].getChessNum() > players[1].getChessNum()? "X" : "O")));
    }
}
