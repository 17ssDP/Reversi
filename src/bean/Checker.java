package bean;

import java.awt.*;

public class Checker {
    private int size;

    public Checker() {

    }
    public Checker(int size) {
        this.size = size;
    }
    public void print() {
 //       Font font = new Font("Courier New", Font.PLAIN, 14);
        for(int i = 0; i < size; i++) {
            System.out.print(" " + (char)('a' + i));
        }
        for(int i = 0; i < size; i++) {
            System.out.print("\n" + (char)('a' + i));
            for(int j = 0; j < size; j++) {
                System.out.print(" X");
            }
        }
    }
}
