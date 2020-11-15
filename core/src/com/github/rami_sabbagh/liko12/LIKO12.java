package com.github.rami_sabbagh.liko12;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LIKO12 extends ApplicationAdapter {
    OrthographicCamera camera;
    Viewport viewport;
    FrameBuffer fbo;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    Vector2 inputVec;

    @Override
    public void create() {
        fbo = new FrameBuffer(Format.RGBA8888, 192, 128, false);
        fbo.getColorBufferTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 192, 128);

        viewport = new FitViewport(192, 128);

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        inputVec = new Vector2();

        fbo.begin();

        Gdx.gl.glClearColor(1, 0.7f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fbo.end();
    }

    void renderBuffer() {
        fbo.begin();


        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(5.5f, 5.5f, 3f, 3f);
//        shapeRenderer.rect(50.5f, 50.5f, 20f, 20f);
//        shapeRenderer.circle(192 >> 1, 128 >> 1, 5);

        shapeRenderer.point(0.5f, 0.5f, 0);
        shapeRenderer.point(0.5f, 127.5f, 0);
        shapeRenderer.point(191.5f, 0.5f, 0);
        shapeRenderer.point(191.5f, 127.5f, 0);

        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int pointer = 0; pointer < Gdx.input.getMaxPointers(); pointer++) {
            if (Gdx.input.isTouched(pointer)){ // || Gdx.app.getType() == Application.ApplicationType.Desktop) {
                inputVec.set(Gdx.input.getX(pointer), Gdx.input.getY(pointer));
                viewport.unproject(inputVec);
                inputVec.y = 127 - inputVec.y;

                inputVec.x = (float) Math.floor(inputVec.x);
                inputVec.y = (float) Math.floor(inputVec.y);

                shapeRenderer.circle(inputVec.x, inputVec.y, 3);
            }
        }

        shapeRenderer.end();

        fbo.end();
    }

    @Override
    public void render() {
        renderBuffer();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(fbo.getColorBufferTexture(), 0, 0, 192, 128, 0, 0, 1, 1);
        batch.end();
    }

    @Override
    public void dispose() {
        fbo.dispose();
        shapeRenderer.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
