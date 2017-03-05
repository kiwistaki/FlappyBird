package com.akkunsoft.flappypenguin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.akkunsoft.flappypenguin.FlappyPenguin;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyPenguin.WIDTH;
		config.height = FlappyPenguin.HEIGHT;
		config.title = FlappyPenguin.TITLE;
		new LwjglApplication(new FlappyPenguin(), config);
	}
}
