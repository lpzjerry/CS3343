package ems;

import java.util.ArrayList;

public class Map {

    private int horizontal;
    private int vertical;
    private ArrayList<Point>

    public int distance(Point a, Point b) {
        int xdelta = Math.abs(a.getX() - b.getX());
        int ydelta = Math.abs(a.getY() - b.getY());
        return xdelta + ydelta;
    }

    public Map(int horizontal, int vertical) {

    }


    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
