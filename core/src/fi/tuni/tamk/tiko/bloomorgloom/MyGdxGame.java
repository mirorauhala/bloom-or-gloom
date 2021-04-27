package fi.tuni.tamk.tiko.bloomorgloom;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fi.tuni.tamk.tiko.bloomorgloom.Screens.SplashScreen;
import fi.tuni.tamk.tiko.bloomorgloom.Shop.ShopMain;

public class MyGdxGame extends Game {
	// DEBUG ->
	public static final boolean DEBUG = true;
	// <- DEBUG

	public SpriteBatch gameBatch;
	public SpriteBatch hudBatch;
	public Navigation navigation;
	public ScoreCounter score;
	public Inventory inventory;


	public final static float SCREEN_WIDTH = 1080;
	public final static float SCREEN_HEIGHT = 1920;

	public OrthographicCamera camera;
	public Settings settings;
	public Items items;
	public Sounds sounds;
	public ShopMain shop;
	public float soundVolume = 10f;
	public float musicVolume = 7f;

	@Override
	public void create () {
		if(DEBUG) {
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
		}

		gameBatch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

		settings = new Settings();
		inventory = new Inventory();
		items = new Items();
		items.preloadTextures(inventory);
		score = new ScoreCounter(this);
		sounds = new Sounds(this);
		shop = new ShopMain(this);
		navigation = new Navigation(this);

		sounds.ambientPlay();
		sounds.musicPlay();



		if(DEBUG) {
			navigation.setActive(Navigation.Screen.GAME);
			navigation.setScreen(Navigation.Screen.GAME);
		} else {
			setScreen(new SplashScreen(this));
		}
	}

	@Override
	public void render () {
		hudBatch.setProjectionMatrix(camera.combined);
		// Uses the render method from current screen.
		super.render();

		hudBatch.begin();
		navigation.draw(hudBatch);
		hudBatch.end();
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		hudBatch.dispose();
	}
}
