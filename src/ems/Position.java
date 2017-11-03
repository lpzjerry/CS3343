package ems;

public class Position {
    // Pengze Liu 2017-Nov-2
    // Encapsulate all cordinates into Position objects
    // Essential methods are provided
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        x = 0;
        y = 0;
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
}