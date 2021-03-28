package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.tuni.tamk.tiko.depressionaut.GameCharacter;
import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.ScoreCounter;
import fi.tuni.tamk.tiko.depressionaut.ScoreMeter;
import fi.tuni.tamk.tiko.depressionaut.TapParticle;

/**
 * GameScreen has everything that gets drawn in the main games screen
 *
 */

public class GameScreen implements Screen {
    private TapParticle particle = new TapParticle();
    private GameCharacter character = new GameCharacter();
    final private float x = 0;
    final private float y = 0;

    public ScoreMeter scoreMeter;

    public int wallTier = 0;
    public int floorTier = 0;
    public int bedTier = 0;
    public int chairTier = 0;
    public int deskTier = 0;
    // Character's lowest tier is 1, not 0.
    public int characterTier = 1;
    public int smileTier = 0;

    /*
    Lists for all tiers of textures
     */
    public List<Texture> walls = Arrays.asList(
            new Texture("walls/tier1.png"),
            new Texture("walls/tier1.png")
            //Will have many tiers
    );
    public List<Texture> floors = Arrays.asList(
            new Texture("floors/tier1.png"),
            new Texture("floors/tier1.png")
    );
    public List<Texture> beds = Arrays.asList(
            new Texture("furniture/bed/tier1.png"),
            new Texture("furniture/bed/tier1.png")
    );
    public List<Texture> chairs = Arrays.asList(
            new Texture("furniture/chair/tier1.png"),
            new Texture("furniture/chair/tier1.png")
    );
    public List<Texture> desks = Arrays.asList(
            new Texture("furniture/desk/tier1.png"),
            new Texture("furniture/desk/tier1.png")
    );

    MyGdxGame game;
    SpriteBatch batch;

    private OrthographicCamera camera;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        batch = game.gameBatch;
        camera = game.camera;
        scoreMeter = new ScoreMeter(game);
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
    public void setSmileTier(int smileTier) {
        this.smileTier = smileTier;
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0.8f, 0.8f, 1, 1);

        createParticle();

        batch.begin();
        // Background layer:
        batch.draw(walls.get(wallTier), x, y);
        batch.draw(floors.get(wallTier), x, y);

        // Furniture layer:
        batch.draw(beds.get(wallTier), x, y);
        batch.draw(chairs.get(wallTier), x, y);
        batch.draw(desks.get(wallTier), x, y);

        // Character layer:
        character.draw(batch);

        // Top layer:
        particle.renderParticles(batch, delta);

        scoreMeter.draw(batch);

        batch.end();


    }

    public void createParticle() {
        Vector2 headPos = new Vector2(character.getHeadPosition(characterTier));
        headPos.x += character.head.getWidth() / 2f;
        headPos.y += character.head.getHeight() / 2f;

        if (Gdx.input.justTouched()) {
            particle.createParticle(headPos);
            game.score.click();
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
