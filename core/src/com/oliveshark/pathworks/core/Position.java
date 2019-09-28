package com.oliveshark.pathworks.core;

public class Position<T extends Number> {
    public T x;
    public T y;

    public Position(T x, T y) {
        this.x = x;
        this.y = y;
    }
}
