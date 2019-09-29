package com.oliveshark.pathworks.framework.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.oliveshark.pathworks.core.Position;

import java.util.Random;

public class Agent {

    private Position<Integer> position;
    private Position<Integer> destination;
//    private float velocity = 0.0f;
    private final Color color;

    public Agent(Position<Integer> position, Position<Integer> destination) {
        color = generateRandomColor();
        this.position = position;
        this.destination = destination;
    }

    private Color generateRandomColor() {
        Random random = new Random(System.nanoTime());
        final int colorUpperBound = 256;
        float red = random.nextInt(colorUpperBound) / 255.0f;
        float green = random.nextInt(colorUpperBound) / 255.0f;
        float blue = random.nextInt(colorUpperBound) / 255.0f;
        return new Color(red, green, blue, 1.0f);
    }

    public void draw(ShapeRenderer renderer) {
        renderer.setColor(color);
        // Agent
        renderer.circle(position.x + 16, position.y + 16, 16);
        // Destination
        renderer.triangle(destination.x + 16, destination.y + 32, destination.x,
                destination.y + 4, destination.x + 32, destination.y + 4);
    }
}
