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
	public Navigation navigation;

	public final static float SCREEN_WIDTH = 1080;
	public final static float SCREEN_HEIGHT = 1920;
	public long splashTimer = System.nanoTime();
	public int pitest;

	public OrthographicCamera camera;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		navigation = new Navigation(this);

		pitest = 0;

		if (!SKIP_TO_GAME) {
			setScreen(new SplashScreen(this));
		} else {
			navigation.setActive(Navigation.Screen.GAME);
			setScreen(new GameScreen(this));
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
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		hudBatch.dispose();
	}
}
