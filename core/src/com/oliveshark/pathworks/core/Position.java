package com.oliveshark.pathworks.core;

public class Position<T extends Number> {
    public T x;
    public T y;

    public Position(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position) o;
            return p.x.equals(x) && p.y.equals(y);
        }
        return false;
    }

    @Override
    public String toString() {
        return x + "x" + y;
    }
}
