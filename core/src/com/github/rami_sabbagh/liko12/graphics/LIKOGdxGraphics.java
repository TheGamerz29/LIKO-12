package com.github.rami_sabbagh.liko12.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.github.rami_sabbagh.liko12.graphics.exceptions.InvalidColorException;
import com.github.rami_sabbagh.liko12.graphics.interfaces.Graphics;
import com.github.rami_sabbagh.liko12.graphics.interfaces.Image;
import com.github.rami_sabbagh.liko12.graphics.interfaces.ImageData;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class LIKOGdxGraphics implements Graphics {

    private final static int MAX_COLORS = 16;

    private final FrameBuffer frameBuffer;
    private final ShapeDrawer drawer;
    private final Color[] colorPalette;
    private final Color[] defaultColorsPalette;
    private int activeColor = 0;

    public LIKOGdxGraphics(LIKOGdxFrameBuffer likoGdxFrameBuffer) {
        frameBuffer = likoGdxFrameBuffer.frameBuffer;
        drawer = likoGdxFrameBuffer.drawer;

        defaultColorsPalette = loadColorsPaletteFromImage(Gdx.files.internal("palette.png"));
        colorPalette = new Color[defaultColorsPalette.length];
        for (int colorId = 0; colorId < colorPalette.length; colorId++)
            colorPalette[colorId] = new Color(defaultColorsPalette[colorId]);
    }

    /**
     * Loads a color palette from an image file.
     *
     * @param file The {@code FileHandle}.
     * @return An array containing the colors extracted from the image.
     */
    private static Color[] loadColorsPaletteFromImage(FileHandle file) {
        Pixmap pixmap = new Pixmap(file);
        Color[] colors = new Color[pixmap.getWidth() * pixmap.getHeight()];

        for (int colorId = 0; colorId < colors.length; colorId++) {
            int colorRGBA = pixmap.getPixel(colorId % pixmap.getWidth(),
                    colorId / pixmap.getWidth());
            colors[colorId] = new Color(colorRGBA);
        }

        pixmap.dispose();
        return colors;
    }

    private static void validateColor(int color) {
        if (color < 0 || color >= MAX_COLORS)
            throw new InvalidColorException(color, MAX_COLORS);
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        //TODO: Implement me.
    }

    @Override
    public void setDrawingPattern(Image pattern) {
        //TODO: Implement me.
    }

    @Override
    public void translate(int x, int y) {
        //TODO: Implement me.
    }

    @Override
    public void scale(int scaleX, int scaleY) {
        //TODO: Implement me.
    }

    @Override
    public void rotate(double angle) {
        //TODO: Implement me.
    }

    @Override
    public void shear(int x, int y) {
        //TODO: Implement me.
    }

    @Override
    public void pushMatrix() {
        //TODO: Implement me.
    }

    @Override
    public void popMatrix() {
        //TODO: Implement me.
    }

    @Override
    public void resetMatrix() {
        //TODO: Implement me.
    }

    @Override
    public void setPaletteColor(int color, int r, int g, int b) {
        //TODO: Implement me.
    }

    @Override
    public PaletteColor getPaletteColor(int color) {
        return null; //TODO: Implement me.
    }

    @Override
    public void resetPaletteColor(int color) {
        //TODO: Implement me.
    }

    @Override
    public void remapColor(int from, int to) {
        //TODO: Implement me.
    }

    @Override
    public void makeColorTransparent(int color) {
        //TODO: Implement me.
    }

    @Override
    public void makeColorOpaque(int color) {
        //TODO: Implement me.
    }

    @Override
    public ImageData newImageData(int width, int height) {
        return null; //TODO: Implement me.
    }

    @Override
    public int getColor() {
        return activeColor;
    }

    @Override
    public void setColor(int color) {
        validateColor(color);
        activeColor = color;
    }

    @Override
    public void clear(Integer color) {
        Color gdxColor = getGdxColor(color);
        Gdx.gl.glClearColor(gdxColor.r, gdxColor.g, gdxColor.b, gdxColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void point(float x, float y, Integer color) {
        drawer.rectangle(x+0.5f, y+0.5f, 0, 0, getGdxColor(color));
    }

    @Override
    public void line(float x1, float y1, float x2, float y2, Integer color) {
        drawer.line(x1, y1, x2, y2, getGdxColor(color));
    }

    @Override
    public void triangle(float x1, float y1, float x2, float y2, float x3, float y3, Boolean filled, Integer color) {
        x1 += 0.5f; y1 += 0.5f; x2 += 0.5f; y2 += 0.5f; x3 += 0.5f; y3 += 0.5f;
        if (filled) {
            drawer.filledTriangle(x1, y1, x2, y2, x3, y3, getGdxColor(color));
        } else {
            drawer.triangle(x1, y1, x2, y2, x3, y3, getGdxColor(color));
        }
    }

    @Override
    public void rectangle(float x, float y, float width, float height, Boolean filled, Integer color) {
        x += 0.5f; y += 0.5f;
        if (filled) {
            drawer.filledRectangle(x, y, width-1, height-1, getGdxColor(color));
        } else {
            drawer.rectangle(x, y, width-1, height-1, getGdxColor(color));
        }
    }

    @Override
    public void polygon(float x1, float y1, float x2, float y2, float x3, float y3, Integer... verticesAndColor) {
        //TODO: Implement me.
    }

    @Override
    public void circle(float x, float y, float radius, Boolean filled, Integer color) {
        if (filled) {
            drawer.filledCircle(x, y, radius, getGdxColor(color));
        } else {
            drawer.setColor(getGdxColor(color));
            drawer.circle(x, y, radius);
        }
    }

    @Override
    public void ellipse(float x, float y, float radiusX, float radiusY, Boolean filled, Integer color) {
        x += 0.5f; y += 0.5f;
        drawer.setColor(getGdxColor(color));
        if (filled) {
            drawer.filledEllipse(x, y, radiusX, radiusY);
        } else {
            drawer.ellipse(x, y, radiusX, radiusY);
        }
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }

    @Override
    public ImageData screenshot() {
        return null; //TODO: Implement me.
    }

    @Override
    public void flip() {
        //TODO: Implement me.
    }

    /**
     * Gets a specific color.
     * The color is validated internally.
     *
     * @param color The color id. Defaults to the active color.
     * @return The specified color from the palette.
     */
    private Color getGdxColor(Integer color) {
        if (color == null) color = activeColor;
        else validateColor(color);

        return colorPalette[color];
    }
}
