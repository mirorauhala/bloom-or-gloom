package fi.tuni.tamk.tiko.bloomorgloom2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.tuni.tamk.tiko.bloomorgloom2.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 800;
		config.width = 480;
		config.title = "Bloom or Gloom";
		new LwjglApplication(new MyGdxGame(), config);
	}
}
