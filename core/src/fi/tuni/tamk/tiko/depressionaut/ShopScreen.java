package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShopScreen implements Screen {

    MyGdxGame host;
    SpriteBatch batch;
    private OrthographicCamera camera;

    public ShopScreen(MyGdxGame host) {
        this.host = host;
        batch = host.gameBatch;
        camera = host.camera;
    }

    @Override
    public void render(float delta) {

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
