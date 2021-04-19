package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Arrays;
import java.util.List;

import fi.tuni.tamk.tiko.depressionaut.GameCharacter;
import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.ScoreCounter;
import fi.tuni.tamk.tiko.depressionaut.ScoreMeter;
import fi.tuni.tamk.tiko.depressionaut.Sounds;
import fi.tuni.tamk.tiko.depressionaut.TapParticle;
import fi.tuni.tamk.tiko.depressionaut.Thoughts.ThoughtBubble;

/**
 * GameScreen has everything that gets drawn in the main games screen
 *
 */

public class GameScreen implements Screen {
    private GameCharacter character = new GameCharacter();
    final private float x = 0;
    final private float y = 0;

    public Texture daySky = new Texture("sky/daySky.png");
    public Texture nightSky = new Texture("sky/nightSky.png");


    public Rectangle gameScreenRectangle;

    public Sounds sounds = new Sounds();

    public ScoreMeter scoreMeter;
    public ScoreCounter scoreCounter;
    private TapParticle particle = new TapParticle();
    private ThoughtBubble bubble = new ThoughtBubble();

    public int wallTier = 0;
    public int floorTier = 0;
    public int bedTier = 0;
    public int stuffTier = 0;
    public int chairTier = 0;
    public int deskTier = 0;
    public int smileTier = 0;

    /*
    Lists for all tiers of textures
     */
    public List<Texture> walls = Arrays.asList(
            new Texture("walls/t1v1.png"),
            new Texture("walls/t1v2.png"),
            new Texture("walls/t1v3.png"),
            new Texture("walls/t2v4.png"),
            new Texture("walls/t2v5.png"),
            new Texture("walls/t2v6.png"),
            new Texture("walls/t3v7.png"),
            new Texture("walls/t3v8.png"),
            new Texture("walls/t3v9.png"),
            new Texture("walls/t4v10.png"),
            new Texture("walls/t4v11.png"),
            new Texture("walls/t4v12.png"),
            new Texture("walls/t5v13.png"),
            new Texture("walls/t5v14.png"),
            new Texture("walls/t5v15.png")
    );
    public List<Texture> floors = Arrays.asList(
            new Texture("floors/t1v1.png"),
            new Texture("floors/t1v2.png"),
            new Texture("floors/t1v3.png"),
            new Texture("floors/t2v4.png"),
            new Texture("floors/t2v5.png"),
            new Texture("floors/t2v6.png"),
            new Texture("floors/t3v7.png"),
            new Texture("floors/t3v8.png"),
            new Texture("floors/t3v9.png"),
            new Texture("floors/t4v10.png"),
            new Texture("floors/t4v11.png"),
            new Texture("floors/t4v12.png"),
            new Texture("floors/t5v13.png"),
            new Texture("floors/t5v14.png"),
            new Texture("floors/t5v15.png")
    );
    public List<Texture> beds = Arrays.asList(
            new Texture("furniture/bed/t1v1.png"),
            new Texture("furniture/bed/t1v2.png"),
            new Texture("furniture/bed/t1v3.png"),
            new Texture("furniture/bed/t2v4.png"),
            new Texture("furniture/bed/t2v5.png"),
            new Texture("furniture/bed/t2v6.png"),
            new Texture("furniture/bed/t3v7.png"),
            new Texture("furniture/bed/t3v8.png"),
            new Texture("furniture/bed/t3v9.png"),
            new Texture("furniture/bed/t4v10.png"),
            new Texture("furniture/bed/t4v11.png"),
            new Texture("furniture/bed/t4v12.png"),
            new Texture("furniture/bed/t5v13.png"),
            new Texture("furniture/bed/t5v14.png"),
            new Texture("furniture/bed/t5v15.png")
    );
    public List<Texture> stuff = Arrays.asList(
            //new Texture("barbells.png"),
            //new Texture("plant.png"),
            //new Texture("moreStuff.png")
    );
    public List<Texture> chairs = Arrays.asList(
            new Texture("furniture/chair/t1v1.png"),
            new Texture("furniture/chair/t1v2.png"),
            new Texture("furniture/chair/t1v3.png"),
            new Texture("furniture/chair/t2v4.png"),
            new Texture("furniture/chair/t2v5.png"),
            new Texture("furniture/chair/t2v6.png"),
            new Texture("furniture/chair/t3v7.png"),
            new Texture("furniture/chair/t3v8.png"),
            new Texture("furniture/chair/t3v9.png"),
            new Texture("furniture/chair/t4v10.png"),
            new Texture("furniture/chair/t4v11.png"),
            new Texture("furniture/chair/t4v12.png")
    );
    public List<Texture> desks = Arrays.asList(
            new Texture("furniture/desk/t1v1.png"),
            new Texture("furniture/desk/t1v2.png"),
            new Texture("furniture/desk/t1v3.png"),
            new Texture("furniture/desk/t2v4.png"),
            new Texture("furniture/desk/t2v5.png"),
            new Texture("furniture/desk/t2v6.png"),
            new Texture("furniture/desk/t3v7.png"),
            new Texture("furniture/desk/t3v8.png"),
            new Texture("furniture/desk/t3v9.png"),
            new Texture("furniture/desk/t4v10.png"),
            new Texture("furniture/desk/t4v11.png"),
            new Texture("furniture/desk/t4v12.png"),
            new Texture("furniture/desk/t5v13.png"),
            new Texture("furniture/desk/t5v14.png"),
            new Texture("furniture/desk/t5v15.png")
    );

    MyGdxGame game;
    SpriteBatch batch;

    private OrthographicCamera camera;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        batch = game.gameBatch;
        camera = game.camera;
        scoreMeter = new ScoreMeter(game);
        scoreCounter = new ScoreCounter();
        gameScreenRectangle = new Rectangle(0, 202, 1080, 1920-202);
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
    public void setStuffTier(int stuffTier) {
        this.stuffTier = stuffTier;
    }
    public void setChairTier(int chairTier) {
        this.chairTier = chairTier;
    }
    public void setDeskTier(int deskTier) {
        this.deskTier = deskTier;
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
        batch.draw(nightSky, 0, 0);
        // Background layer:
        batch.draw(walls.get(wallTier), x, y);
        batch.draw(floors.get(wallTier), x, y);

        // Furniture layer:
        batch.draw(beds.get(wallTier), x, y);
        for (int i = 0; i <= stuffTier; i++) {
            batch.draw(stuff.get(i), x, y);
        }
        batch.draw(chairs.get(wallTier), x, y);
        batch.draw(desks.get(wallTier), x, character.getStandingOffset());
        
        // Character layer:
        character.draw(batch);

        // Top layer:
        particle.renderParticles(batch, delta);
        bubble.render(batch);

        scoreMeter.draw(batch);

        batch.end();


    }

    //Täytyy vaihtaa käyttämään rectanglee
    public void createParticle() {
        Vector2 headPos = new Vector2(character.getHeadPosition(character.getTier()));
        headPos.x += character.head.getWidth() / 2f;
        headPos.y += character.head.getHeight() / 2f;

        if(Gdx.input.justTouched()) {

            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);


            if(gameScreenRectangle.contains(touch.x, touch.y)) {
                sounds.clicksoundPlay();
                game.score.click();
                particle.createParticle(headPos);
                bubble.checkForClear(touch.x, touch.y);

                // temp
                bubble.createThought(character.getTierOffset(character.getTier()));
            }

        }

    }


    @Override
    public void show() {
        applyCurrentTierTextures();
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


    /**
     * Fetches the current tiers for all textures from the Inventory.
     */
    private void applyCurrentTierTextures() {
        setWallTier(game.inventory.get("wall"));
        setFloorTier(game.inventory.get("floor"));
        setBedTier(game.inventory.get("bed"));

        // TODO: Add to inventory system:
        //setStuffTier(game.inventory.get("stuff"));

        setChairTier(game.inventory.get("chair"));
        setDeskTier(game.inventory.get("desk"));
        setSmileTier(game.inventory.get("smile"));
        character.setTier(game.inventory.get("character"));
    }
}
