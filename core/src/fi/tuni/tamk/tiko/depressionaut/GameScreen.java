package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameScreen implements Screen {
    private Texture wall;

    final private float x = 0;
    final private float y = 0;

    public int wallTier = 0;
    public int floorTier = 0;
    public int bedTier = 0;
    public int chairTier = 0;
    public int deskTier = 0;
    public int characterTier = 0;

    public List<Texture> walls = Arrays.asList(
            new Texture("walls/tier1.png"),
            new Texture("floors/tier1.png")
    );
    public List<Texture> floors = Arrays.asList(
            new Texture("floors/tier1.png"),
            new Texture("floors/tier1.png")
    );
    public List<Texture> beds = Arrays.asList(
            new Texture("furniture/bed/tier1.png"),
            new Texture("floors/tier1.png")
    );
    public List<Texture> chairs = Arrays.asList(
            new Texture("furniture/chair/tier1.png"),
            new Texture("floors/tier1.png")
    );
    public List<Texture> desks = Arrays.asList(
            new Texture("furniture/desk/tier1.png"),
            new Texture("floors/tier1.png")
    );
    public List<Texture> characters = Arrays.asList(
            new Texture("character/tier1.png"),
            new Texture("floors/tier1.png")
    );


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
        this.wallTier = wallTier - 1;
    }
    public void setFloorTier(int floorTier) {
        this.floorTier = floorTier - 1;
    }
    public void setBedTier(int bedTier) {
        this.bedTier = bedTier - 1;
    }
    public void setChairTier(int chairTier) {
        this.chairTier = chairTier - 1;
    }
    public void setDeskTier(int deskTier) {
        this.deskTier = deskTier - 1;
    }
    public void setCharacterTier(int characterTier) {
        this.characterTier = characterTier - 1;
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 0);

        batch.begin();
        batch.draw(walls.get(wallTier), x, y);
        batch.draw(floors.get(wallTier), x, y);
        batch.draw(beds.get(wallTier), x, y);
        batch.draw(chairs.get(wallTier), x, y);
        batch.draw(desks.get(wallTier), x, y);
        batch.draw(characters.get(wallTier), x, y);
        batch.end();
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
