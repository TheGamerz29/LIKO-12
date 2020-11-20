package com.github.rami_sabbagh.liko12.graphics.interfaces;

/**
 * Allows to draw shapes on the screen.
 */
public interface GraphicsPainter {

    /**
     * Gets the active color.
     *
     * @return The active color.
     */
    int getColor();

    /**
     * Sets the active color.
     *
     * @param color The active color.
     */
    void setColor(int color);

    /**
     * Clears the screen and fills it with a specific color.
     *
     * @param color The color to fill the screen with. Defaults to the active color.
     */
    void clear(Integer color);

    /**
     * Draws a point into the screen.
     *
     * @param x     The X coordinates of the point.
     * @param y     The Y coordinates of the point.
     * @param color The color of the point. Defaults to the active color.
     */
    void point(int x, int y, Integer color);

    /**
     * Draws a line into the screen.
     *
     * @param x1    The X coordinates of the line's start.
     * @param y1    The Y coordinates of the line's start.
     * @param x2    The X coordinates of the line's end.
     * @param y2    The Y coordinates of the line's end.
     * @param color The color of the line. Defaults to the active color.
     */
    void line(int x1, int y1, int x2, int y2, Integer color);

    /**
     * Draws a triangle into the screen.
     *
     * @param x1     The X coordinates of the triangle's first vertex.
     * @param y1     The Y coordinates of the triangle's first vertex
     * @param x2     The X coordinates of the triangle's second vertex.
     * @param y2     The Y coordinates of the triangle's second vertex.
     * @param x3     The X coordinates of the triangle's third vertex.
     * @param y3     The Y coordinates of the triangle's third vertex.
     * @param filled Whether to fill the triangle or only outline it. Defaults to false (outlined).
     * @param color  The color of the triangle. Defaults to the active color.
     */
    void triangle(int x1, int y1, int x2, int y2, int x3, int y3, Boolean filled, Integer color);

    /**
     * Draws a rectangle into the screen.
     *
     * @param x      The X coordinates of the rectangle's top-left corner.
     * @param y      The Y coordinates of the rectangle's top-left corner.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @param filled Whether to fill the rectangle or only outline it. Defaults to false (outlined).
     * @param color  The color of the rectangle. Defaults to the active color.
     */
    void rectangle(int x, int y, int width, int height, Boolean filled, Integer color);

    /**
     * Draws a filled polygon into the screen.
     *
     * @param x1               The X coordinates of the polygon's first vertex.
     * @param y1               The Y coordinates of the polygon's first vertex.
     * @param x2               The X coordinates of the polygon's second vertex.
     * @param y2               The Y coordinates of the polygon's second vertex.
     * @param x3               The X coordinates of the polygon's third vertex.
     * @param y3               The Y coordinates of the polygon's third vertex.
     * @param verticesAndColor Any other vertices of the polygon
     *                         and the color of the polygon (Defaults to the active color).
     */
    void polygon(int x1, int y1, int x2, int y2, int x3, int y3, Integer... verticesAndColor);


    /**
     * Draws a circle on the screen.
     *
     * @param x        The X coordinates of the circle's center.
     * @param y        The Y coordinates of the circle's center.
     * @param radius   The radius of the circle.
     * @param filled   Whether to fill the circle or only outline it. Defaults to false (outlined).
     * @param color    The color of the circle. Defaults to the active color.
     * @param segments The number of segments used for drawing the circle.
     */
    void circle(int x, int y, int radius, Boolean filled, Integer color, Integer segments);

    /**
     * Draws an ellipse on the screen.
     *
     * @param x        The X coordinates of the ellipse's center.
     * @param y        The Y coordinates of the ellipse's center.
     * @param radiusX  The radius of the ellipse along the X-axis (half the ellipse's width).
     * @param radiusY  The radius of the ellipse along the Y-axis (half the ellipse's height).
     * @param filled   Whether to fill the ellipse or only outline it. Defaults to false (outlined).
     * @param color    The color of the ellipse. Defaults to the active color.
     * @param segments The number of segments used for drawing the ellipse.
     */
    void ellipse(int x, int y, int radiusX, int radiusY, Boolean filled, Integer color, Integer segments);
}
