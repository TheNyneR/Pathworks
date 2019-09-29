package com.oliveshark.pathworks.framework.grid;

import com.oliveshark.pathworks.core.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.oliveshark.pathworks.config.Config.*;
import static com.oliveshark.pathworks.framework.grid.util.PositionUtil.getGridPositionFromScreenPosition;
import static com.oliveshark.pathworks.framework.grid.util.PositionUtil.getPositionFromGridPosition;

public class TestPositionUtil {

    @Test
    public void testGetGridPositionFromScreenPosition() {
        Position beyondTopRight = getGridPositionFromScreenPosition(GRID_WIDTH*TILE_DIMENSION + 10, -100);
        Position beyondBottomLeft = getGridPositionFromScreenPosition(-100, GRID_HEIGHT*TILE_DIMENSION + 10);
        Position withinGrid = getGridPositionFromScreenPosition(320, 320);

        Assertions.assertTrue(new Position(GRID_WIDTH - 1, GRID_HEIGHT - 1).equals(beyondTopRight));
        Assertions.assertTrue(new Position(0, 0).equals(beyondBottomLeft));
        Assertions.assertTrue(new Position(10, 13).equals(withinGrid));
    }

    @Test
    public void testGetPositionFromGridPosition() {
        Position pos = getPositionFromGridPosition(10, 10);
        Assertions.assertTrue(new Position(320, 320).equals(pos));
    }
}
