package com.oliveshark.pathworks.framework.grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Cell {

    private final Texture texture;
    private boolean occupied = false;

    public Cell(Texture texture) {
        this.texture = texture;
    }

    void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    void toggleOccupied() {
        occupied = !occupied;
    }

    boolean isOccupied() {
        return occupied;
    }

    void draw(Batch batch, int x, int y) {
        if (occupied) {
            batch.draw(texture, x, y, 32, 0, 32, 32);
        } else {
            batch.draw(texture, x, y, 0, 0, 32, 32);
        }
    }
}
