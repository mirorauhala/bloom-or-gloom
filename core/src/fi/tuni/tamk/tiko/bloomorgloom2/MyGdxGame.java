package fi.tuni.tamk.tiko.bloomorgloom2;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fi.tuni.tamk.tiko.bloomorgloom2.Screens.SplashScreen;
import fi.tuni.tamk.tiko.bloomorgloom2.Shop.ShopMain;

/**
 * Main class of the game.
 */
public class MyGdxGame extends Game {
	// DEBUG ->
	public static final boolean DEBUG = false;
	// <- DEBUG

	public SpriteBatch gameBatch;
	public SpriteBatch hudBatch;
	public fi.tuni.tamk.tiko.bloomorgloom2.Navigation navigation;
	public fi.tuni.tamk.tiko.bloomorgloom2.ScoreCounter score;
	public fi.tuni.tamk.tiko.bloomorgloom2.Inventory inventory;


	public final static float SCREEN_WIDTH = 1080;
	public final static float SCREEN_HEIGHT = 1920;

	public OrthographicCamera camera;
	public Settings settings;
	public fi.tuni.tamk.tiko.bloomorgloom2.Items items;
	public fi.tuni.tamk.tiko.bloomorgloom2.Sounds sounds;
	public ShopMain shop;
	public float soundVolume = 10f;
	public float musicVolume = 7f;

	/**
	 * Initializes objects upon starting the game.
	 */
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
		navigation = new fi.tuni.tamk.tiko.bloomorgloom2.Navigation(this);

		sounds.ambientPlay();
		sounds.musicPlay();

		if(DEBUG) {
			navigation.setActive(fi.tuni.tamk.tiko.bloomorgloom2.Navigation.Screen.GAME);
			navigation.setScreen(Navigation.Screen.GAME);
		} else {
			setScreen(new SplashScreen(this));
		}
	}

	/**
	 * The main render loop of the game.
	 */
	@Override
	public void render () {
		hudBatch.setProjectionMatrix(camera.combined);
		// Uses the render method from current screen.
		super.render();

		hudBatch.begin();
		navigation.draw(hudBatch);
		hudBatch.end();
	}

	/**
	 * Calls dispose methods.
	 */
	@Override
	public void dispose () {
		gameBatch.dispose();
		hudBatch.dispose();
	}
}
