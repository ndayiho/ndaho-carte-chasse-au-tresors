package com.ndaho.domain.models;

public class Position implements Comparable {
    private int x;
    private int y;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position " +
                "x=" + x +
                ", y=" + y;
    }

    public boolean isGreaterThan(Position position) {
        return compareTo(position) > 0;
    }

    public boolean isLesserThan(Position position) {
        return compareTo(position) < 0;
    }

    @Override
    public int compareTo(Object object) {
        if (object instanceof Position) {
            if (this.x > ((Position) object).getX()
                    || this.y > ((Position) object).getY()) {
                return 1;
            } else if (this.x == ((Position) object).getX()
                    && this.y == ((Position) object).getY()) {
                return 0;
            }
        }
        return -1;
    }

    //Depends only on  x and y
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    //Compare only on x and y
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
