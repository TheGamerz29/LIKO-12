package com.github.rami_sabbagh.liko12.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.rami_sabbagh.liko12.LIKO12;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "LIKO-12";
		config.width = 192*5;
		config.height = 128*5;
		config.addIcon("icon-16x16.png", Files.FileType.Internal);
		new LwjglApplication(new LIKO12(), config);
	}
}
