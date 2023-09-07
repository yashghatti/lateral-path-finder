package com.yashghatti.model;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int x;
    private int y;
    private int xLimit, yLimit;

    public Node(int x, int y, int xLimit, int yLimit) {
        this.x = x;
        this.y = y;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXLimit() {
        return xLimit;
    }

    public int getYLimit() {
        return yLimit;
    }

    public List<Node> getAdjacentNodes() {
        List<Node> adjacent = new ArrayList<>();
        if(x+1<=xLimit)
            adjacent.add(new Node(x+1, y, xLimit, yLimit));
        if(x-1>=1)
            adjacent.add(new Node(x-1, y, xLimit, yLimit));
        if(y+1<=yLimit)
            adjacent.add(new Node(x, y+1, xLimit, yLimit));
        if(y-1>=1)
            adjacent.add(new Node(x, y-1, xLimit, yLimit));
        return adjacent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;

        if (x != node.x) return false;
        return y == node.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
