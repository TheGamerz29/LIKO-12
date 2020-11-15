package com.github.rami_sabbagh.liko12.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.rami_sabbagh.liko12.LIKO12;

public class DesktopLauncher {
	private static final int LIKO_SCALE = 5;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "LIKO-12";
		config.x = 250;
		config.y = 70;
		config.width = 192 * LIKO_SCALE;
		config.height = 128 * LIKO_SCALE;
		config.resizable = true;
		config.addIcon("icon-16x16.png", Files.FileType.Internal);
		new LwjglApplication(new LIKO12(), config);
	}
}
