package com.github.rami_sabbagh.liko12.graphics;

/**
 * Allows to perform some graphics effects.
 */
public interface GraphicsEffects {

    //TODO: Search if it's possible to pass around the transformation matrix's values.

    /**
     * Sets the clipping region of the drawing operations.
     *
     * @param x      The X coordinates of the top-left corner of the clipping area.
     * @param y      The Y coordinates of the top-left corner of the clipping area.
     * @param width  The width of the clipping area.
     * @param height The height of the clipping area.
     */
    void setClip(int x, int y, int width, int height);

    /**
     * Sets the drawing operations pattern, or clears it.
     * The image would be repeated over the screen, and treated as a mask.
     * Any non-zero colored pixels would be drawn on, and any zero colored pixels would remain untouched.
     *
     * @param pattern The drawing pattern image. Defaults for no pattern.
     */
    void setDrawingPattern(Image pattern);

    /**
     * Shifts all drawing operations.
     *
     * @param x The translation relative to the X-axis.
     * @param y The translation relative to the Y-axis.
     */
    void translate(int x, int y);

    /**
     * Scales the coordinates system and the drawing operations.
     *
     * @param scaleX The scaling in the direction of the X-axis.
     * @param scaleY The scaling in the direction of the Y-axis.
     */
    void scale(int scaleX, int scaleY);

    /**
     * Rotates the coordinate system.
     *
     * @param angle The amount to rotate the coordinate system in <b>radians</b>.
     */
    void rotate(double angle);

    /**
     * Shears the coordinate system.
     *
     * @param x The shear factor on the X-axis.
     * @param y The shear factor on the Y-axis.
     */
    void shear(int x, int y);

    //TODO: Figure out if there's any way better than the matrix stack, or allow more control over it.

    /**
     * Copies and pushes the current coordinate transformations to the transformations stack.
     */
    void pushMatrix();

    /**
     * Pops the last coordinate transformations from the transformations stack.
     */
    void popMatrix();

    /**
     * Resets the current coordinate system transformations.
     */
    void resetMatrix();

    /**
     * Sets the RGB values of a palette color.
     *
     * @param color The palette's color to set.
     * @param r     The red channel value [0-255].
     * @param g     The green channel value [0-255].
     * @param b     The blue channel value [0-255].
     */
    void setPaletteColor(int color, int r, int g, int b);

    /**
     * Gets the RGB values of a palette color.
     *
     * @param color The palette's color to get.
     * @return The RGB values of the requested color.
     */
    PaletteColor getPaletteColor(int color);

    /**
     * Resets the palette color to it's initial RGB values.
     *
     * @param color The palette's color to reset.
     */
    void resetPaletteColor(int color);

    /**
     * Sets the color remapping when drawing images.
     *
     * @param from The color to replace.
     * @param to   The color which will replace 'from'.
     */
    void remapColor(int from, int to);

    /**
     * Makes a specific color transparent (invisible) when drawing an image.
     *
     * @param color The color to make transparent.
     */
    void makeColorTransparent(int color);

    /**
     * Makes a specific color opaque (visible) when drawing an image.
     *
     * @param color The color to make opaque.
     */
    void makeColorOpaque(int color);

    //TODO: Figure a way to unpack this in the Lua wrapper, avoiding the whole creation of the Lua table holding the 3 values.
    class PaletteColor {
        int r;
        int g;
        int b;
    }

}
