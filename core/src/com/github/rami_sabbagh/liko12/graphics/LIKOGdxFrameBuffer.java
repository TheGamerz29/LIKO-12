package com.github.rami_sabbagh.liko12.graphics;

import com.badlogic.gdx.Gdx;
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

    /**
     * The OpenGL framebuffer of the LIKO-12 screen.
     */
    public final FrameBuffer frameBuffer;
    /**
     * The orthographic camera applied on the LIKO-12 drawing operations.
     */
    public final OrthographicCamera camera;

    /**
     * A 1x1 #FFFFFFFF texture.
     */
    public final Texture drawerTexture;
    /**
     * A Polygon sprite batch for the shapes and images drawing operations.
     */
    public final PolygonSpriteBatch batch;

    /**
     * The shapes drawer for LIKO-12 drawing operations.
     */
    public final ShapeDrawer drawer;

    /**
     * Creates a new framebuffer of the desired dimensions.
     * @param width The width of the framebuffer in pixels.
     * @param height The height of the framebuffer in pixels.
     */
    public LIKOGdxFrameBuffer(int width, int height) {
        frameBuffer = new FrameBuffer(RGB888, width, height, false);
        frameBuffer.getColorBufferTexture().setFilter(Nearest, Nearest); //Set the scaling filter.

        camera = new OrthographicCamera();
        camera.setToOrtho(true, frameBuffer.getWidth(), frameBuffer.getHeight());

        drawerTexture = createWhitePixelTexture();

        batch = new PolygonSpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        drawer = new ShapeDrawer(batch, new TextureRegion(drawerTexture));
        drawer.setDefaultSnap(true);
    }

    /**
     * Binds the LIKO-12's framebuffer and allows drawing operations to be made afterwards.
     */
    public void begin() {
        frameBuffer.begin();
        batch.begin();
    }

    /**
     * Unbinds the LIKO-12's framebuffer and drawing operations won't be allowed afterwards.
     */
    public void end() {
        batch.end();
        frameBuffer.end();
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
