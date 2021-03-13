package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch gameBatch;
	SpriteBatch hudBatch;
	private BottomHud bottomHud;
	private GameScreen gameScreen;

	
	@Override
	public void create () {
		SpriteBatch gameBatch = new SpriteBatch();
		SpriteBatch hudBatch = new SpriteBatch();;
		bottomHud = new BottomHud();
		gameScreen = new GameScreen(new Texture("splashScreen.jpg"), 0, 0);
	}

	@Override
	public void render () {
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
