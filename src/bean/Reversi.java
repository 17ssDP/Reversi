package bean;
import java.util.Scanner;

public class Reversi {
    private static int dimen;
    private static char computer;
    public static void main(String[] args) {
        initialize();
        Checker checker = new Checker(dimen);
        checker.print();
    }
    public static void initialize() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the board dimension: ");
        dimen = input.nextInt();
        System.out.print("Computer plays (X/O): ");
        computer = input.next().charAt(0);
    }
}
