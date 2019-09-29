package com.oliveshark.pathworks.core;

import java.util.Objects;

public class Position<T extends Number> {
    public T x;
    public T y;

    public Position(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position<?> position = (Position<?>) o;
        return x.equals(position.x) &&
                y.equals(position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + "x" + y;
    }
}
