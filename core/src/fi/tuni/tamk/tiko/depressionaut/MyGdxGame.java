package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	SpriteBatch gameBatch;
	SpriteBatch hudBatch;
	private BottomHud bottomHud;
	private GameScreen gameScreen;
	private ShopScreen shopScreen;
	public final float SCREEN_WIDTH = 1080;
	public final float SCREEN_HEIGHT = 1920;
	public long splashTimer = System.nanoTime();
	public int pitest;

	public OrthographicCamera camera;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		bottomHud = new BottomHud();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		pitest = 0;

		setScreen(new SplashScreen(this));

	}

	@Override
	public void render () {
		hudBatch.setProjectionMatrix(camera.combined);
		// Uses the render method from current screen.
		super.render();

		hudBatch.begin();

		bottomHud.draw(hudBatch);
		hudBatch.end();

		ScoreCounter.checkForClick();



		pitest++;
		if(pitest > 60) {
			pitest = 0;
			ScoreCounter.drawScore();
		}


	}

	public void setComicScreen () {
		setScreen(new Comic(this));
	}

	public void setGameScreen () {
		setScreen(new GameScreen(this));
	}

	/*public void setShopScreen () {
		setScreen(new ShopScreen(this));
	}*/
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		hudBatch.dispose();

	}
}
