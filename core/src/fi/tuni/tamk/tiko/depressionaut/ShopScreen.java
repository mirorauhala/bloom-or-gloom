package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class ShopScreen implements Screen {
    private final Rectangle rect;
    MyGdxGame game;
    SpriteBatch batch;
    private OrthographicCamera camera;
    public Texture rectText;

    public ShopScreen(MyGdxGame host) {
        this.game = host;
        batch = game.gameBatch;
        camera = game.camera;

        rectText = new Texture("shop/rect.png");
        rect = new Rectangle(100, 400, rectText.getWidth(),  rectText.getHeight());
    }

    @Override
    public void render(float delta) {

        // set background to white
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // rectangle
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(rectText, rect.getX(), rect.getY());
        batch.end();

        if(Gdx.input.justTouched()){
            Gdx.app.setLogLevel(Application.LOG_DEBUG);

            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(rect.contains(touch.x, touch.y)){
                Gdx.app.debug("YES", "X touched: " + touch.x + " Y touched: " + touch.y);
                game.setGameScreen();
            }else{
                Gdx.app.debug("NO", "X touched: " + touch.x + " Y touched: " + touch.y);
            }
        }
    }

    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
