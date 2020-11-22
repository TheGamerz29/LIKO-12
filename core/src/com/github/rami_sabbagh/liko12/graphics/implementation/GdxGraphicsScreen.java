package com.github.rami_sabbagh.liko12.graphics.implementation;

import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.github.rami_sabbagh.liko12.graphics.interfaces.GraphicsScreen;
import com.github.rami_sabbagh.liko12.graphics.interfaces.ImageData;

public interface GdxGraphicsScreen extends GraphicsScreen {
    
    FrameBuffer frameBuffer = null;

    @Override
    int getWidth();

    @Override
    int getHeight();

    @Override
    ImageData screenshot();

    @Override
    void flip();
}
