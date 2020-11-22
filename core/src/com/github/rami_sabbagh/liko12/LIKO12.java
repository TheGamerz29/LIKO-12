package com.github.rami_sabbagh.liko12;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.rami_sabbagh.liko12.graphics.LIKOGdxFrameBuffer;
import com.github.rami_sabbagh.liko12.graphics.LIKOGdxGraphics;

public class LIKO12 extends ApplicationAdapter {
    Viewport viewport;

    LIKOGdxFrameBuffer likoGdxFrameBuffer;
    LIKOGdxGraphics graphics;
    SpriteBatch batch;

    Vector2 inputVec;

    @Override
    public void create() {
        viewport = new FitViewport(192, 128);
        likoGdxFrameBuffer = new LIKOGdxFrameBuffer(192, 128);
        graphics = new LIKOGdxGraphics(likoGdxFrameBuffer);

        batch = new SpriteBatch();

        inputVec = new Vector2();
    }

    void renderBuffer() {
        likoGdxFrameBuffer.begin();

        graphics.clear(0);
        graphics.setColor(7);

        for (int x = 0; x < graphics.getWidth(); x += 2)
            graphics.line(x, 0, x, graphics.getHeight() - 1, 1);
        for (int y = 0; y < graphics.getHeight(); y += 2)
            graphics.line(0, y, graphics.getWidth() - 1, y, 1);

        graphics.point(0, 0, null);
        graphics.point(191, 0, null);
        graphics.point(0, 127, null);
        graphics.point(191, 127, null);

        graphics.rectangle(2,2, 5*16+2, 5+2, true, 0);
        for (int i = 0; i < 16; i++) graphics.rectangle(3 + i * 5, 3, 5, 5, false, i);

        inputVec.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY() - 1);
        viewport.unproject(inputVec);
        inputVec.x = (float) Math.floor(inputVec.x);
        inputVec.y = (float) Math.floor(inputVec.y);
        //graphics.point(inputVec.x, inputVec.y, 15);
        graphics.circle(inputVec.x, inputVec.y, 8, true, 15);

        likoGdxFrameBuffer.end();
    }

    @Override
    public void render() {
        renderBuffer();
        viewport.apply();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(likoGdxFrameBuffer.frameBuffer.getColorBufferTexture(), 0, 0, 192, 128, 0, 0, 1, 1);
        batch.end();
    }

    @Override
    public void dispose() {
        likoGdxFrameBuffer.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
