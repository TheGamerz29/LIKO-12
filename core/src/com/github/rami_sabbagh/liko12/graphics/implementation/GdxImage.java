package com.github.rami_sabbagh.liko12.graphics.implementation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.github.rami_sabbagh.liko12.graphics.interfaces.Image;
import com.github.rami_sabbagh.liko12.graphics.interfaces.ImageData;

public class GdxImage implements Image, Disposable {

    private final int width;
    private final int height;
    private final GdxImageData imageData;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final GdxFrameBuffer frameBuffer;

    public GdxImage(GdxImageData imageData, GdxFrameBuffer frameBuffer) {
        this.imageData = imageData;
        this.texture = new Texture(this.imageData.pixmap);
        this.textureRegion = new TextureRegion(this.texture);

        this.width = this.imageData.getWidth();
        this.height = this.imageData.getHeight();

        this.frameBuffer = frameBuffer;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(float x, float y, Float rotation, Float scaleX, Float scaleY, Integer srcX, Integer srcY, Integer srcWidth, Integer srcHeight) {
        frameBuffer.batch.draw(textureRegion,
                x, y,
                0, 0,
                width, height,
                scaleX, scaleY,
                rotation);
    }

    @Override
    public void refresh() {
        texture.draw(imageData.pixmap, 0, 0);
    }

    @Override
    public ImageData getImageData() {
        return imageData;
    }

    @Override
    public void dispose() {
        this.texture.dispose();
        this.imageData.dispose();
    }
}
