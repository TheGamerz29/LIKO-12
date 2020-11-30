package com.github.rami_sabbagh.liko12.graphics.implementation;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.rami_sabbagh.liko12.graphics.interfaces.Image;

public class GdxImage implements Image {

    private final int width;
    private final int height;
    private final Pixmap pixmap;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final GdxFrameBuffer frameBuffer;

    public GdxImage(Pixmap pixmap, GdxFrameBuffer frameBuffer) {
        this.pixmap = pixmap;
        this.texture = new Texture(this.pixmap);
        this.textureRegion = new TextureRegion(this.texture);

        this.width = this.pixmap.getWidth();
        this.height = this.pixmap.getHeight();

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
        if (rotation == null) rotation = 0.0f;
        if (scaleX == null) scaleX = 1.0f;
        if (scaleY == null) scaleY = 1.0f;
        if (srcX == null) srcX = 0;
        if (srcY == null) srcY = 0;
        if (srcWidth == null) srcWidth = width;
        if (srcHeight == null) srcHeight = height;

        //TODO: Fix the region.

        frameBuffer.batch.draw(textureRegion,
                x + frameBuffer.offsets.imageX, y + frameBuffer.offsets.imageY,
                0, 0,
                width, height,
                scaleX, scaleY,
                rotation);
    }

    @Override
    public void refresh() {
        if (pixmap.isDisposed()) return;
        texture.draw(pixmap, 0, 0);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
