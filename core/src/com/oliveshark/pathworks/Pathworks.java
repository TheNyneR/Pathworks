package com.oliveshark.pathworks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oliveshark.pathworks.framework.grid.Grid;

public class Pathworks extends ApplicationAdapter {
	SpriteBatch batch;
	Grid grid;

	@Override
	public void create () {
		batch = new SpriteBatch();
		grid = new Grid();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		grid.draw(batch, 1);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
