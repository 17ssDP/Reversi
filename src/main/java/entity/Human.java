package main.java.entity;

public class Human extends Player {
    private boolean giveUp = false;
    public Human(boolean color) {
        super(color);
    }

    public boolean isGiveUp() {
        return giveUp;
    }

    public void setGiveUp(boolean giveUp) {
        this.giveUp = giveUp;
    }
}
