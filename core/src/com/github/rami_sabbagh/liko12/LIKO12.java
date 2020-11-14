package com.github.rami_sabbagh.liko12;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LIKO12 extends ApplicationAdapter {
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false);

		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		img = new Texture("icon-square.png");

		font = new BitmapFont();

		Gdx.app.log("Lifecycle", "create");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		font.draw(batch, "Hello World", 5, 20);
		batch.end();


		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1,1,1,1);

		for (int pointer=0; pointer<Gdx.input.getMaxPointers(); pointer++) {
			if (Gdx.input.isTouched(pointer) || Gdx.app.getType() == Application.ApplicationType.Desktop) {
				shapeRenderer.circle(
						Gdx.input.getX(pointer),
						Gdx.graphics.getHeight() - Gdx.input.getY(pointer),
						getDensity()*10);
			}
		}
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		img.dispose();
	}

	public float getDensity() {
		if (Gdx.app.getType() == Application.ApplicationType.Desktop)
			return 1.0f;
		else
			return Gdx.graphics.getDensity();
	}
}
