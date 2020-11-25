package com.github.rami_sabbagh.liko12.graphics.implementation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix3;
import com.github.rami_sabbagh.liko12.graphics.exceptions.InvalidColorException;
import com.github.rami_sabbagh.liko12.graphics.exceptions.InvalidPaletteColorException;
import com.github.rami_sabbagh.liko12.graphics.interfaces.Graphics;
import com.github.rami_sabbagh.liko12.graphics.interfaces.Image;
import com.github.rami_sabbagh.liko12.graphics.interfaces.ImageData;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.nio.ByteBuffer;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGB888;
import static com.badlogic.gdx.math.Matrix3.*;

public class GdxGraphics implements Graphics {

    private final static int MAX_COLORS = 16;

    private final GdxFrameBuffer gdxFrameBuffer;
    private final FrameBuffer frameBuffer;
    private final ShapeDrawer drawer;
    private final PolygonSpriteBatch batch;
    private final Matrix3 transformationMatrix;
    private final Color[] defaultColorsPalette;

    /**
     * The palette-independent colors used for drawing into the internal buffer.
     */
    private final Color[] colors;
    /**
     * A reusable color object, used to pass color values to some functions.
     */
    private final Color reusableColor;
    /**
     * The current active color for drawing operations.
     */
    private int activeColor;

    public GdxGraphics(GdxFrameBuffer gdxFrameBuffer) {
        this.gdxFrameBuffer = gdxFrameBuffer;
        frameBuffer = this.gdxFrameBuffer.frameBuffer;
        drawer = this.gdxFrameBuffer.drawer;
        batch = this.gdxFrameBuffer.batch;
        transformationMatrix = this.gdxFrameBuffer.transformationMatrix;
        reusableColor = new Color();

        colors = new Color[MAX_COLORS];
        for (int colorId = 0; colorId < MAX_COLORS; colorId++)
            colors[colorId] = new Color(colorId / (MAX_COLORS - 1.0f), 0, 0, 1);

        defaultColorsPalette = new Color[MAX_COLORS];
        for (int colorId = 0; colorId < MAX_COLORS; colorId++)
            defaultColorsPalette[colorId] = this.gdxFrameBuffer.getColor(colorId);
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
    public float[] getMatrix() {
        float[] values = new float[9];
        float[] matrixValues = transformationMatrix.getValues();
        values[0] = matrixValues[M00];
        values[1] = matrixValues[M01];
        values[2] = matrixValues[M02];
        values[3] = matrixValues[M10];
        values[4] = matrixValues[M11];
        values[5] = matrixValues[M12];
        values[6] = matrixValues[M20];
        values[7] = matrixValues[M21];
        values[8] = matrixValues[M22];
        return values;
    }

    @Override
    public void setMatrix(float[] matrix) {
        if (matrix.length != 9)
            throw new IllegalArgumentException("The matrix float[] array must be 9 floats length, provided: " + matrix.length);
        float[] matrixValues = transformationMatrix.getValues();

        matrixValues[M00] = matrix[0];
        matrixValues[M01] = matrix[1];
        matrixValues[M02] = matrix[2];
        matrixValues[M10] = matrix[3];
        matrixValues[M11] = matrix[4];
        matrixValues[M12] = matrix[5];
        matrixValues[M20] = matrix[6];
        matrixValues[M21] = matrix[7];
        matrixValues[M22] = matrix[8];

        batch.end();
        gdxFrameBuffer.updateTransformationMatrix();
        batch.begin();
    }

    @Override
    public void setPaletteColor(int color, int r, int g, int b) {
        validateColor(color);

        if (r < 0 || r >= 256) throw new InvalidPaletteColorException(r);
        if (g < 0 || g >= 256) throw new InvalidPaletteColorException(g);
        if (b < 0 || b >= 256) throw new InvalidPaletteColorException(b);

        reusableColor.set((r << 24) | (g << 16) | (b << 8) | 0xFF);
        gdxFrameBuffer.setColor(color, reusableColor);
    }

    @Override
    public Color getPaletteColor(int color) {
        validateColor(color);
        return gdxFrameBuffer.getColor(color);
    }

    @Override
    public void resetPaletteColor(int color) {
        validateColor(color);
        gdxFrameBuffer.setColor(color, defaultColorsPalette[color]);
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
        return new GdxImageData(this.gdxFrameBuffer, width, height);
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
        drawer.rectangle(x + 0.5f, y + 0.5f, 0, 0, getGdxColor(color));
    }

    @Override
    public void line(float x1, float y1, float x2, float y2, Integer color) {
        drawer.line(x1, y1, x2, y2, getGdxColor(color));
    }

    @Override
    public void triangle(float x1, float y1, float x2, float y2, float x3, float y3, Boolean filled, Integer color) {
        x1 += 0.5f;
        y1 += 0.5f;
        x2 += 0.5f;
        y2 += 0.5f;
        x3 += 0.5f;
        y3 += 0.5f;
        if (filled) {
            drawer.filledTriangle(x1, y1, x2, y2, x3, y3, getGdxColor(color));
        } else {
            drawer.triangle(x1, y1, x2, y2, x3, y3, getGdxColor(color));
        }
    }

    @Override
    public void rectangle(float x, float y, float width, float height, Boolean filled, Integer color) {
        x += 0.5f;
        y += 0.5f;
        if (filled) {
            drawer.filledRectangle(x, y, width - 1, height - 1, getGdxColor(color));
        } else {
            drawer.rectangle(x, y, width - 1, height - 1, getGdxColor(color));
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
        x += 0.5f;
        y += 0.5f;
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
        Pixmap pixmap = new Pixmap(getWidth(), getHeight(), RGB888);
        ByteBuffer pixels = pixmap.getPixels();
        pixels.clear();
        Gdx.gl.glPixelStorei(GL20.GL_PACK_ALIGNMENT, 1);
        Gdx.gl.glReadPixels(0, 0, getWidth(), getHeight(), GL20.GL_RGB, GL20.GL_UNSIGNED_BYTE, pixels);
        pixels.position(0);

        return new GdxImageData(gdxFrameBuffer, pixmap);
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

        return colors[color];
    }
}
