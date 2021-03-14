package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private Texture wall;

    private float x = 0;
    private float y = 0;
    final private float width = 480f;
    final private float height = 800f;


    public int wallTier;
    public int floorTier;
    public int bedTier;

    final private Texture wallTier1 = new Texture("walls/tier1.png");
    final private Texture floorTier1 = new Texture("floors/tier1.png");
    final private Texture bedTier1 = new Texture("furniture/bed/tier1.png");
    //final private Texture wallTier2 = new Texture("walls/tier2.png");
    //final private Texture floorTier2 = new Texture("floors/tier2.png");

    MyGdxGame host;
    SpriteBatch batch;

    private OrthographicCamera camera;

    public GameScreen(MyGdxGame host) {
        this.host = host;
        batch = host.gameBatch;
        camera = host.camera;



    }

    /*
    Tiering system

    Tier on each object in the gamescreen can be set with gamescreen.setObjecttier


     */
    public void setWallTier(int wallTier) {
        this.wallTier = wallTier;
    }
    public void setFloorTier(int floorTier) {
        this.floorTier = floorTier;
    }
    public void setBedTier(int bedTier) {
        this.bedTier = bedTier;
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 0);

        setFloorTier(2);
        setWallTier(2);
        setBedTier(2);

        batch.begin();
        batch.draw(getWallTexture(), getX(), getY());
        batch.draw(getFloorTexture(), getX(), getY());
        batch.draw(getBedTexture(), getX(), getY());
        batch.end();
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getWallTexture() {
        switch(wallTier) {
            case 1:
                return wallTier1;
            case 2:
                return wallTier1;
            default:
                return wallTier1;
        }
    }
    public Texture getFloorTexture() {
        switch (floorTier) {
            case 1:
                return floorTier1;
            case 2:
                return floorTier1;
            default:
                return floorTier1;
        }
    }
    public Texture getBedTexture() {
        switch (bedTier) {
            case 1:
                return bedTier1;
            case 2:
                return bedTier1;
            default:
                return bedTier1;
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
