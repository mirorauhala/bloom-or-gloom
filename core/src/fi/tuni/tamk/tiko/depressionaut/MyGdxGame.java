package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	// DEBUG ->

	private final boolean SKIP_TO_GAME = true;

	// <- DEBUG


	SpriteBatch gameBatch;
	SpriteBatch hudBatch;
	private Navigation navigation;
	private GameScreen gameScreen;
	private ShopScreen shopScreen;
	public final static float SCREEN_WIDTH = 1080;
	public final static float SCREEN_HEIGHT = 1920;
	public long splashTimer = System.nanoTime();
	public int pitest;

	public OrthographicCamera camera;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		navigation = new Navigation();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		pitest = 0;

		if (!SKIP_TO_GAME) {
			setScreen(new SplashScreen(this));
		} else {
			setShopScreen();
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

		ScoreCounter.checkForClick();

		pitest++;
		if(pitest > 60) {
			pitest = 0;
			ScoreCounter.drawScore();
		}

	}

	public void setComicScreen () {
		navigation.setHudSelection(Navigation.Screen.NONE);
		setScreen(new Comic(this));
	}

	public void setGameScreen () {
		navigation.setHudSelection(Navigation.Screen.GAME);
		setScreen(new GameScreen(this));
	}

	public void setShopScreen () {
		navigation.setHudSelection(Navigation.Screen.SHOP);
		setScreen(new ShopScreen(this));
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		hudBatch.dispose();
	}
}
