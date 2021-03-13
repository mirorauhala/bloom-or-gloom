package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private Texture wall;
    private float x;
    private float y;

    MyGdxGame host;
    SpriteBatch batch;

    private OrthographicCamera camera;

    public GameScreen(MyGdxGame host) {
        this.host = host;
        batch = host.gameBatch;
        camera = host.camera;

        setWall(new Texture("walls/tier1.jpg"));
        setX(0);
        setY(0);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 0);

        batch.begin();
        batch.draw(getWall(), getX(), getY());
        batch.end();
    }

    public void setX(float xPos) {
        x = xPos;
    }

    public void setY(float yPos) {
        y = yPos;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setWall(Texture wallTexture) {
        wall = wallTexture;
    }

    public Texture getWall() {
        return wall;
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
