package com.oliveshark.pathworks.framework.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.oliveshark.pathworks.core.Position;

public class Grid extends Actor implements InputProcessor {

    private static int GRID_WIDTH = 32;
    private static int GRID_HEIGHT = 24;

    private Cell[][] cells;
    private Texture tileTexture;

    public Grid() {
        tileTexture = new Texture(Gdx.files.internal("tiles.png"));
        cells = new Cell[GRID_WIDTH][GRID_HEIGHT];
        for (int i = 0; i < GRID_WIDTH; ++i) {
            for (int j = 0; j < GRID_HEIGHT; ++j) cells[i][j] = new Cell(tileTexture);
        }
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < GRID_WIDTH; ++i) {
            for (int j = 0; j < GRID_HEIGHT; ++j) {
                cells[i][j].draw(batch, i*32, j*32);
            }
        }
    }

    @Override
    public boolean remove() {
        boolean success = super.remove();
        tileTexture.dispose();
        return success;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT)
            return true;

        System.out.println("Click at " + screenX + "x" + screenY);
        Position<Integer> cellPos = getGridPositionFromScreenPosition(screenX, screenY);

        Cell cell = cells[cellPos.x][cellPos.y];
        cell.toggleOccupied();
        mouseLeftButtonDown = true;
        mouseLeftButtonDownToggle = cell.isOccupied();
        return false;
    }

    private Position<Integer> getGridPositionFromScreenPosition(int x, int y) {
        int cellX = (x - (x % 32))/32;
        int cellY = (y - (y % 32))/32;

        // Mouse pos originates from the top left corner (like SDL)
        // libgdx originates coordinates from bottom left so we have to reverse it here
        cellY = GRID_HEIGHT - cellY - 1;

        return new Position<>(cellX, cellY);
    }

    private boolean mouseLeftButtonDown = false;
    private boolean mouseLeftButtonDownToggle = false;

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT)
            return true;
        mouseLeftButtonDown = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!mouseLeftButtonDown)
            return true;

        Position<Integer> cellPos = getGridPositionFromScreenPosition(screenX, screenY);
        cells[cellPos.x][cellPos.y].setOccupied(mouseLeftButtonDownToggle);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
