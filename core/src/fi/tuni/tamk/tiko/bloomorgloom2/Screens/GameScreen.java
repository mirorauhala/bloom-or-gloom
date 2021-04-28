package fi.tuni.tamk.tiko.bloomorgloom2.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fi.tuni.tamk.tiko.bloomorgloom2.Items;
import fi.tuni.tamk.tiko.bloomorgloom2.DailyBonus;
import fi.tuni.tamk.tiko.bloomorgloom2.GameCharacter;
import fi.tuni.tamk.tiko.bloomorgloom2.GameClock;
import fi.tuni.tamk.tiko.bloomorgloom2.MyGdxGame;
import fi.tuni.tamk.tiko.bloomorgloom2.ScoreMeter;
import fi.tuni.tamk.tiko.bloomorgloom2.Sky.Sky;
import fi.tuni.tamk.tiko.bloomorgloom2.TapParticle;
import fi.tuni.tamk.tiko.bloomorgloom2.Thoughts.ThoughtBubble;

/**
 * GameScreen has everything that gets drawn in the main games screen
 *
 */

public class GameScreen implements Screen {
    private final Stage stage;
    private final Items items;
    private final fi.tuni.tamk.tiko.bloomorgloom2.GameCharacter character;
    public Rectangle gameScreenRectangle;
    public fi.tuni.tamk.tiko.bloomorgloom2.ScoreMeter scoreMeter;
    private fi.tuni.tamk.tiko.bloomorgloom2.TapParticle particle = new TapParticle();
    private fi.tuni.tamk.tiko.bloomorgloom2.Thoughts.ThoughtBubble bubble = new fi.tuni.tamk.tiko.bloomorgloom2.Thoughts.ThoughtBubble();
    private fi.tuni.tamk.tiko.bloomorgloom2.GameClock clock;
    private fi.tuni.tamk.tiko.bloomorgloom2.Sky.Sky sky = new Sky();

    fi.tuni.tamk.tiko.bloomorgloom2.MyGdxGame game;
    SpriteBatch batch;

    public fi.tuni.tamk.tiko.bloomorgloom2.DailyBonus dailyBonus;

    private OrthographicCamera camera;
    private int wallIndex;
    private int floorIndex;
    private int bedIndex;
    private int itemsIndex;
    private int chairIndex;
    private int deskIndex;
    private int trashcanIndex;
    private int paintingIndex;
    private int weightsIndex;
    private int mugIndex;
    private int plantIndex;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        batch = game.gameBatch;
        camera = game.camera;
        scoreMeter = new ScoreMeter(game);
        gameScreenRectangle = new Rectangle(0, 202, 1080, 1920-202);
        stage = new Stage(new ScreenViewport());
        items = game.items;
        dailyBonus = new DailyBonus(game);
        clock = new GameClock(game);
        character = new GameCharacter(game);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0.8f, 0.8f, 1, 1);

        checkForTap();
        clock.timer(); // updates clock
        // Checks if a thought bubble should be created, and creates one if more
        // than one minute has passed.
        if (clock.thoughtBubbleTimer(false)) {
            bubble.createThought(character.getHappinessLevelOffset(game.score.getHappinessLevel()));
        }

        batch.begin();
        // Sky layer:
        sky.draw(batch, clock);

        batch.setColor(clock.getDayOpacity()+0.25f,clock.getDayOpacity()+0.25f,clock.getDayOpacity()+0.25f,1); // lighting effect

        // Background layer:
        float x = 0;
        float y = 0;
        batch.draw(items.getItem("wall", wallIndex), x, y);
        batch.draw(items.getItem("floor", floorIndex), x, y);
        batch.draw(items.getItem("chair", chairIndex), x, y);
        batch.draw(items.getItem("bed", bedIndex), x, y);
        batch.draw(items.getItem("desk", deskIndex), x, character.getStandingOffset());
        
        // Character layer:
        character.draw(batch);

        // Furniture layer:
        batch.draw(items.getItem("trashcan", trashcanIndex), x, y);
        batch.draw(items.getItem("painting", paintingIndex), x, y);
        batch.draw(items.getItem("weights", weightsIndex), x, y);
        batch.draw(items.getItem("mug", mugIndex), x, character.getStandingOffset());

        if (bedIndex >= 12) {
            batch.draw(items.getItem("plant", plantIndex), x, y);
        } else {
            batch.draw(items.getItem("plant", plantIndex), x, -200);
        }

        // Top layer:
        particle.renderParticles(batch, delta);
        bubble.render(batch);

        batch.setColor(1,1,1,1); // reset batch color

        // Hud layer:
        scoreMeter.draw(batch, clock);
        game.score.setTempMultiplier(clock.amountOfBuffs());

        // Daily Bonus
        dailyBonus.drawWindow(batch);
        dailyBonus.checkForTap(camera);
        dailyBonus.drawIndicator(batch);

        batch.end();

    }

    public void checkForTap() {
        Vector2 headPos = new Vector2(character.getHeadPosition(game.score.getHappinessLevel()));
        headPos.x += character.head.getWidth() / 2f;
        headPos.y += character.head.getHeight() / 2f;

        if(Gdx.input.justTouched()) {

            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(gameScreenRectangle.contains(touch.x, touch.y)) {
                if (bubble.getNegThoughtsAmount() < 1 && !dailyBonus.isBonusWindowOnScreen) {
                    game.sounds.clicksoundPlay();
                    game.score.click();
                    particle.createParticle(headPos);

                    characterDebug(false); // character debug
                }
                if (bubble.checkForClear(touch.x, touch.y) == ThoughtBubble.Emotion.POSITIVE) {
                    clock.addBuff(30);
                }
            }
        }
    }

    /**
     * Used to test the functionality of the character.
     *
     * @param debug Debug boolean to toggle the usage of the method.
     */
    public void characterDebug(boolean debug) {
        if (debug) {
            int rand = (int)(Math.random()*5);
            /*
            switch (rand) {
                case 0: character.setItem(GameCharacter.heldItem.EMPTY);
                    break;
                case 1: character.setItem(GameCharacter.heldItem.PHONE);
                    break;
                case 2: character.setItem(GameCharacter.heldItem.BOOK);
                    break;
                case 3: character.setItem(GameCharacter.heldItem.LAPTOP);
                    break;
            }
             */
            character.setShirt((int)(Math.random()*16));
            character.setSleeveIndex((int)(Math.random()*16));
            character.setHat((int)(Math.random()*10));

            Gdx.app.debug("Character", "Happiness level: " + game.score.getHappinessLevel());
            Gdx.app.debug("Character", "Item: " + character.getItem());
            Gdx.app.debug("Character", "Shirt: " + character.getShirt());
            Gdx.app.debug("Character", "Sleeve: " + character.getSleeveIndex());
            Gdx.app.debug("Character", "Hat: " + character.getHat());
        }
    }
    
    @Override
    public void show() {
        // setup input processor (gets clicks and stuff)
        Gdx.input.setInputProcessor(stage);

        applyCurrentShopIndexes();
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
     * Fetches the current indexes for all textures from the Inventory.
     */
    private void applyCurrentShopIndexes() {
        wallIndex = game.inventory.get("wall");
        floorIndex = game.inventory.get("floor");
        bedIndex = game.inventory.get("bed");
        chairIndex = game.inventory.get("chair");
        deskIndex = game.inventory.get("desk");
        character.setShirt(game.inventory.get("shirt"));
        character.setHat(game.inventory.get("hat"));
        character.setItem(game.inventory.get("hands"));
        trashcanIndex = game.inventory.get("trashcan");
        paintingIndex = game.inventory.get("painting");
        weightsIndex = game.inventory.get("weights");
        mugIndex = game.inventory.get("mug");
        plantIndex = game.inventory.get("plant");
    }
}
