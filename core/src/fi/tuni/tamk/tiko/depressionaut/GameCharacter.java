package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Class for the drawing and functioning of the character.
 */
public class GameCharacter {
    private final MyGdxGame game;
    public Texture head = new Texture("character/head/head.png"); // head texture
    private Shirts shirts = new Shirts(); // shirt textures
    private int shirtIndex;
    private int sleeveIndex;
    private int hatIndex;

    public HashMap<heldItem, Texture> hands = new HashMap<heldItem, Texture>(); // textures for held items

    // body textures
    private ArrayList<Texture> bodies = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/body/tier1/tier1.png"),
                    new Texture("character/body/tier2/tier2.png"),
                    new Texture("character/body/tier3/tier3.png"),
                    new Texture("character/body/tier4/tier4.png"),
                    new Texture("character/body/tier5/tier5.png")
            )
    );

    // hat textures
    private List<Texture> hats = Arrays.asList(
            new Texture("character/hats/t1v0.png"),
            new Texture("character/hats/t3v1.png"),
            new Texture("character/hats/t3v2.png"),
            new Texture("character/hats/t3v3.png"),
            new Texture("character/hats/t4v4.png"),
            new Texture("character/hats/t4v5.png"),
            new Texture("character/hats/t4v6.png"),
            new Texture("character/hats/t5v7.png"),
            new Texture("character/hats/t5v8.png"),
            new Texture("character/hats/t5v9.png")
    );

    // mouth textures
    private ArrayList<Texture> smiles = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/smile/tier1.png"),
                    new Texture("character/smile/tier2.png"),
                    new Texture("character/smile/tier3.png"),
                    new Texture("character/smile/tier4.png"),
                    new Texture("character/smile/tier5.png")
            )
    );

    // eye textures
    private ArrayList<Texture> eyes = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/eyes/open.png"),
                    new Texture("character/eyes/closed.png")
            )
    );

    public enum heldItem{
        EMPTY,
        PHONE,
        BOOK,
        LAPTOP,
        COMPUTER
    }

    private heldItem currentItem = heldItem.EMPTY;

    // timer for random blinks
    private int blinkTimer = 0;

    /**
     * Constructor puts the textures for held items in a hashMap for easier access.
     */
    public GameCharacter(MyGdxGame game) {
        this.game = game;
        hands.put(heldItem.EMPTY, new Texture("character/hands/empty.png"));
        hands.put(heldItem.PHONE, new Texture("character/hands/phone.png"));
        hands.put(heldItem.BOOK, new Texture("character/hands/book.png"));
        hands.put(heldItem.LAPTOP, new Texture("character/hands/laptop.png"));
    }

    /**
     * Draws all textures related to the character.
     *
     * @param batch SpriteBatch needed for the draw() method.
     */
    public void draw(SpriteBatch batch) {
        // draw body
        batch.draw(bodies.get(game.score.getHappinessLevel()),
                0,
                0);
        batch.draw(shirts.shirts.get(game.score.getHappinessLevel()).get(shirtIndex),
                0,
                0);

        // draw head
        batch.draw(head,
                getHeadPosition(game.score.getHappinessLevel()).x,
                getHeadPosition(game.score.getHappinessLevel()).y);

        // draw mouth:
        batch.draw(smiles.get(game.score.getHappinessLevel()),
                0,
                0);

        // draw eyes
        blink(batch);

        // draw hands
        batch.draw(hands.get(currentItem),
                0,
                getTierOffset(game.score.getHappinessLevel()));
        batch.draw(shirts.sleeves.get(getSleeveIndex()).get(shirtIndex),
                0,
                getTierOffset(game.score.getHappinessLevel()));

        // draw hats
        batch.draw(hats.get(hatIndex),
                0,
                getTierOffset(game.score.getHappinessLevel()));
    }

    /**
     * Makes the character blink by periodically changing the texture of the eyes.
     *
     * @param batch SpriteBatch needed for the draw() method.
     */
    public void blink(SpriteBatch batch) {
        blinkTimer++;
        if(blinkTimer > 400) {
            blinkTimer = 0;
        }
        if(blinkTimer > 390) {
            batch.draw(eyes.get(1), 0, getTierOffset(game.score.getHappinessLevel()));
        } else {
            batch.draw(eyes.get(0), 0, getTierOffset(game.score.getHappinessLevel()));
        }

    }

    /**
     * Sets held item.
     *
     * @param item enum heldItem
     */
    public void setItem(heldItem item) {
        currentItem = item;
    }

    /**
     * Gets current item
     *
     * @return enum heldItem
     */
    public heldItem getItem() {
        return currentItem;
    }

    /**
     * Sets shirt index.
     *
     * @param index Index
     */
    public void setShirt(int index) {
        shirtIndex = index;
    }

    /**
     * Gets shirt index.
     *
     * @return Return the shirt's index.
     */
    public int getShirt() {
        return shirtIndex;
    }

    /**
     * Sets hat index.
     *
     * @param index int hat's index
     */
    public void setHat(int index) {
        hatIndex = index;
    }

    /**
     * Gets hat index.
     *
     * @return Return the hat's index.
     */
    public int getHat() {
        return hatIndex;
    }

    /**
     * Sets sleeve index needed for different poses.
     *
     * @param index Sleeve index.
     */
    public void setSleeveIndex(int index) {
        sleeveIndex = index;
    }

    /**
     * Gets sleeve index needed for different poses.
     *
     * @return Return the sleeve's index.
     */
    public int getSleeveIndex() {
        switch (currentItem) {
            case EMPTY: return 0;
            case PHONE: return 1;
            case BOOK:
            case LAPTOP:
            case COMPUTER: return 2;
        }
        return -1;
    }

    /**
     * Returns the character's head's position relative to it's starting
     * position in Happiness Level 1.
     *
     * @param tier Current tier
     * @return offset
     */
    public int getTierOffset(int tier) {
        return (int) (getHeadPosition(tier).y - getHeadPosition(0).y);
    }

    /**
     * Returns standing offset in tier 5 needed for textures that move up
     * with the character when it stands up.
     *
     * @return offset
     */
    public float getStandingOffset() {
        if (game.score.getHappinessLevel() >= 4) {
            return 100;
        } else {
            return 0;
        }
    }

    /**
     * Returns the position of the head depending on the character's tier.
     *
     * @param tier Current tier
     * @return Head's position
     */
    public Vector2 getHeadPosition(int tier) {
        switch (tier) {
            case 0: return new Vector2(514,1920 - 1336);
            case 1: return new Vector2(514,1920 - 1285);
            case 2:
            case 3:
                return new Vector2(514,1920 - 1271);
            case 4: return new Vector2(514,1920 - 1196);
        }
        return new Vector2();
    }

    /**
     * Get's the position of the center of the character's head.
     *
     * @param tier Current tier
     * @return Position of the center of the head.
     */
    public Vector2 getHeadCenter(int tier) {
        return new Vector2(
                getHeadPosition(tier).x + (head.getWidth() / 2f),
                getHeadPosition(tier).y + head.getHeight() / 2f
        );
    }

}
