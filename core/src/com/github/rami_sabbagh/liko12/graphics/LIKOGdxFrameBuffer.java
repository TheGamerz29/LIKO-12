package com.github.rami_sabbagh.liko12.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Disposable;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGB888;
import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.badlogic.gdx.graphics.Texture.TextureFilter.Nearest;

public class LIKOGdxFrameBuffer implements Disposable {

    private final FrameBuffer frameBuffer;
    private final OrthographicCamera camera;

    private final Texture drawerTexture;
    private final PolygonSpriteBatch batch;

    private final ShapeDrawer drawer;

    public LIKOGdxFrameBuffer(int width, int height) {
        frameBuffer = new FrameBuffer(RGB888, width, height, false);
        frameBuffer.getColorBufferTexture().setFilter(Nearest, Nearest); //Set the scaling filter.

        camera = new OrthographicCamera();
        camera.setToOrtho(true, frameBuffer.getWidth(), frameBuffer.getHeight());

        drawerTexture = createWhitePixelTexture();

        batch = new PolygonSpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        drawer = new ShapeDrawer(batch, new TextureRegion(drawerTexture));
    }

    /**
     * Creates a 1x1 #FFFFFFFF texture.
     *
     * @return The created texture.
     */
    private static Texture createWhitePixelTexture() {
        Pixmap pixmap = new Pixmap(1, 1, RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return texture;
    }

    //TODO: Maybe add an update method, check the ShapeDrawer wiki.

    @Override
    public void dispose() {
        frameBuffer.dispose();
        drawerTexture.dispose();
        batch.dispose();
    }
}
