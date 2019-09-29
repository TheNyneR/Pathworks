package com.oliveshark.pathworks.framework.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.oliveshark.pathworks.core.Position;
import com.oliveshark.pathworks.framework.entities.Agent;

import java.util.*;

public class Grid extends Actor implements InputProcessor {

    private static int GRID_WIDTH = 32;
    private static int GRID_HEIGHT = 24;

    private Position<Integer> mousePos = new Position<>(0, 0);
    private Cell[][] cells;
    private Texture tileTexture;
    private Collection<Agent> agents;
    private ShapeRenderer agentRenderer;

    private boolean mouseLeftButtonDown = false;
    private boolean mouseLeftButtonDownToggle = false;

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private boolean shapeRendererMatrixSet = false;

    public Grid() {
        tileTexture = new Texture(Gdx.files.internal("tiles.png"));
        cells = new Cell[GRID_WIDTH][GRID_HEIGHT];
        for (int i = 0; i < GRID_WIDTH; ++i) {
            for (int j = 0; j < GRID_HEIGHT; ++j) cells[i][j] = new Cell(tileTexture);
        }
        Gdx.input.setInputProcessor(this);

        // Get random positions based on grid dimensions
        agents = Collections.singletonList(new Agent(
                new Position<>(new Random().nextInt(GRID_WIDTH) * 32,
                        new Random().nextInt(GRID_HEIGHT) * 32), new Position<>(0, 0)));
        agentRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < GRID_WIDTH; ++i) {
            for (int j = 0; j < GRID_HEIGHT; ++j) {
                cells[i][j].draw(batch, i * 32, j * 32);
            }
        }

        // Render agents
        batch.end();
        agentRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Agent agent : agents) {
            agent.draw(agentRenderer);
        }
        agentRenderer.end();
        batch.begin();

        // Draw the mouse grid indicator
        if (mousePos.x != 0 || mousePos.y != 0) {
            batch.end();

            Gdx.gl.glEnable(GL30.GL_BLEND);
            Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
            if (!shapeRendererMatrixSet) {
                shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
                shapeRendererMatrixSet = true;
            }
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            shapeRenderer.rect(mousePos.x * 32, mousePos.y * 32, 32, 32);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL30.GL_BLEND);

            batch.begin();
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

    private void reverseCells() {
        for (Cell[] col : cells) {
            for (Cell cell : col) {
                cell.toggleOccupied();
            }
        }
    }

    @Override
    public boolean keyTyped(char character) {
        if (character == 'r') reverseCells();
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
        int cellX = (x - (x % 32)) / 32;
        int cellY = (y - (y % 32)) / 32;

        // Mouse pos originates from the top left corner (like SDL)
        // libgdx originates coordinates from bottom left so we have to reverse it here
        cellY = GRID_HEIGHT - cellY - 1;

        if (cellX < 0) cellX = 0;
        else if (cellX >= GRID_WIDTH) cellX = GRID_WIDTH - 1;
        if (cellY < 0) cellY = 0;
        else if (cellY >= GRID_HEIGHT) cellY = GRID_HEIGHT - 1;
        return new Position<>(cellX, cellY);
    }

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
        updateMousePos(screenX, screenY);
        return false;
    }

    private void updateMousePos(int screenX, int screenY) {
        mousePos = getGridPositionFromScreenPosition(screenX, screenY);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        updateMousePos(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
