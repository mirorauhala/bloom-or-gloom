package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.concurrent.TimeUnit;

public class MyGdxGame extends Game {
	SpriteBatch gameBatch;
	SpriteBatch hudBatch;
	private BottomHud bottomHud;
	private GameScreen gameScreen;
	public final float SCREEN_WIDTH = 1080;
	public final float SCREEN_HEIGHT = 1920;
	private long splashTimer = System.nanoTime();

	public OrthographicCamera camera;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		hudBatch = new SpriteBatch();;
		bottomHud = new BottomHud();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

		// Start game with splash screen.
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		// Uses render method from current screen.
		super.render();

		// Move to GameScreen when screen is touched or when 7.5s has passed.
		if (Gdx.input.justTouched() || TimeUnit.MILLISECONDS.convert(System.nanoTime() - splashTimer, TimeUnit.NANOSECONDS) > 7500) {
			setGameScreen();
		}

		/*hudBatch.begin();
		bottomHud.draw(hudBatch);
		hudBatch.end();*/
	}

	// Sets GameScreen as the current screen.
	public void setGameScreen () {
		setScreen(new GameScreen(this));
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		hudBatch.dispose();

	}
}
