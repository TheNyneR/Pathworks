package com.oliveshark.pathworks.framework.grid;

import com.oliveshark.pathworks.core.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.oliveshark.pathworks.config.Config.*;
import static com.oliveshark.pathworks.framework.grid.util.PositionUtil.getGridPositionFromScreenPosition;
import static com.oliveshark.pathworks.framework.grid.util.PositionUtil.getPositionFromGridPosition;

class TestPositionUtil {

    @Test
    void testGetGridPositionFromScreenPosition() {
        Position beyondTopRight = getGridPositionFromScreenPosition(GRID_WIDTH*TILE_DIMENSION + 10, -100);
        Position beyondBottomLeft = getGridPositionFromScreenPosition(-100, GRID_HEIGHT*TILE_DIMENSION + 10);
        Position withinGrid = getGridPositionFromScreenPosition(320, 320);

        Assertions.assertEquals(new Position<Integer>(GRID_WIDTH - 1, GRID_HEIGHT - 1), beyondTopRight);
        Assertions.assertEquals(new Position<Integer>(0, 0), beyondBottomLeft);
        Assertions.assertEquals(new Position<Integer>(10, 13), withinGrid);
    }

    @Test
    void testGetPositionFromGridPosition() {
        Position pos = getPositionFromGridPosition(10, 10);
        Assertions.assertEquals(new Position<Integer>(320, 320), pos);
    }
}
