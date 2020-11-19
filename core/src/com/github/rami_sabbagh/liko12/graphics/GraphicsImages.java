package com.github.rami_sabbagh.liko12.graphics;

/**
 * Allows to draw images and edit them.
 */
public interface GraphicsImages {

    /**
     * Creates a new ImageData with a specific dimensions, and zero-fills it.
     *
     * @param width  The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @return The created new ImageData object.
     */
    ImageData newImageData(int width, int height);

    //TODO: Add quads.
    //TODO: Add SpriteBatches.
}
