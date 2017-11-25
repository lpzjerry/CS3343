package ems;

import java.util.Random;

public class Position {
    // Encapsulate all cordinates into Position objects
    private int x;
    private int y;

    private static final int X_limit = 20;
    private static final int Y_limit = 20;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        Random random = new Random();
        x = random.nextInt() % X_limit;
        y = random.nextInt() % Y_limit;
    }

    public void changePosition(int newx, int newy) {
        x = newx;
        y = newy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int distance(Position p1, Position p2) {
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
    }

    public int distance(Position p) {
        return Position.distance(this, p);
    }

    @Override
    public String toString() {
        return String.format("Address: (%d, %d).", this.getX(), this.getY());
    }

}