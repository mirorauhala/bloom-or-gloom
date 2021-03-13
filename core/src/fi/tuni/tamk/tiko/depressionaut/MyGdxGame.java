package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch gameBatch;
	SpriteBatch hudBatch;
	private BottomHud bottomHud;
	private GameScreen gameScreen;
	public final float SCREEN_WIDTH = 1080;
	public final float SCREEN_HEIGHT = 1920;

	private OrthographicCamera camera;
	
	@Override
	public void create () {
		gameBatch = new SpriteBatch();
		hudBatch = new SpriteBatch();;
		bottomHud = new BottomHud();
		gameScreen = new GameScreen(new Texture("splashScreen.jpg"), 0, 0);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	@Override
	public void render () {
		gameBatch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(0, 0, 0, 0);

		gameBatch.begin();
		gameScreen.draw(gameBatch);
		gameBatch.end();

		/*hudBatch.begin();
		bottomHud.draw(hudBatch);
		hudBatch.end();*/
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		hudBatch.dispose();

	}
}
